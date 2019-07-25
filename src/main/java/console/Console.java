package console;

import enums.Status;
import enums.ValidityState;
import javafx.util.Pair;
import pass.ticket.Ticket;
import pass.ticket.TicketJsonWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Console {
    private Ticket ticket;
    private String[] args;

    public void runTerminal() {
        getCommand();
        loadOrGenerate(args);
    }

    private void getCommand() {
        System.out.println("Enter command:");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        args = command.split(" ");
    }

    private void loadOrGenerate(String[] args) {
        this.args = args;
        String command = args[0];
        if (command.equals("LOAD")) {
            load();
        } else {
            generate();
        }
    }

    private void load() {
        loadTicket(Long.parseLong(args[1]));
        whereSaveTicketFromDatabase();
    }

    private void loadTicket(Long id) {
        Pair<EntityManager, EntityTransaction> entity = prepareEntity();
        try {
            getTicketFromDatabase(entity, id);
        } catch (Exception e) {
            if (entity.getValue() != null) entity.getValue().rollback();
            e.printStackTrace();
        } finally {
            if (entity.getKey() != null) entity.getKey().close();
        }
    }

    private Pair<EntityManager, EntityTransaction> prepareEntity() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        return new Pair<>(entityManager, entityTransaction);
    }

    private void getTicketFromDatabase(Pair<EntityManager, EntityTransaction> entity, long id) throws Exception {
        entity.getValue().begin();
        ticket = entity.getKey().find(Ticket.class, id);
        entity.getValue().commit();
    }

    private void whereSaveTicketFromDatabase() {
        if (args.length > 2) {
            saveTicketToJson(args[2]);
        } else {
            printTicketOnConsole();
        }
    }

    private void saveTicketToJson(String path) {
        try {
            TicketJsonWriter tjw = new TicketJsonWriter(ticket, path);
            tjw.writeToJson();
        } catch (IOException e) {
            System.out.println("Invalid file path for result file");
            e.printStackTrace();
        }
    }

    private void printTicketOnConsole() {
        System.out.println(ticket.toString());
    }

    private void generate() {
        if (args.length == 4 || args.length == 5) {
            generateTicket();
            whereSaveTicketGenerated(args[3]);
        } else System.out.println("Invalid command");
    }

    private void generateTicket() {
        checkState(args[2]);
        checkStatus(args[3]);
        generateTicket(args[2], args[3]);
        if (args.length == 5) {
            setTravelerData();
        }
    }

    private void checkState(String state) {
        try {
            ValidityState.valueOf(state);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid state");
        }
    }

    private void checkStatus(String status) {
        try {
            Status.valueOf(status);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status, try again");
        }
    }

    private void generateTicket(String state, String status) {
        if (isStatusAndStateCombinationCorrect(state, status)) {
            ticket = new Ticket(state, status);
        } else System.out.println("Improprate combination of status and state");
    }

    private boolean isStatusAndStateCombinationCorrect(String validityState, String status) {
        boolean result = true;
        if (status.equals("EXPIRED") && (!(validityState.equals("NOT_VALID") || validityState.equals("VALID_YESTERDAY")))) {
            result = false;
        } else if (status.equals("NOT_STARTED") && (!(validityState.equals("NOT_VALID") || validityState.equals("NOT_STARTED")))) {
            result = false;
        } else if ((status.equals("BLOCKED") || status.equals("REFUNDED")) && (!(validityState.equals("NOT_VALID")))) {
            result = false;
        }
        return result;
    }

    private void whereSaveTicketGenerated(String command) {
        if (command.equals("DATABASE")) {
            saveTicketToDatabase();
        } else if (command.equals("CONSOLE")) {
            printTicketOnConsole();
        } else {
            saveTicketToJson(command);
        }
    }

    private void saveTicketToDatabase() {
        Pair<EntityManager, EntityTransaction> entity = prepareEntity();
        try {
            saveTicket(entity);
        } catch (Exception e) {
            if (entity.getValue() != null) entity.getValue().rollback();
            e.printStackTrace();
        } finally {
            if (entity.getKey() != null) entity.getKey().close();
        }
        printTicketId();
    }

    private void saveTicket(Pair<EntityManager, EntityTransaction> entity) {
        entity.getValue().begin();
        entity.getKey().persist(ticket);
        entity.getValue().commit();
    }

    private void printTicketId() {
        System.out.println("Ticket id: " + ticket.getId());
    }


    private void setTravelerData() {
        try {
            ticket.setEPassDetailsFromJSON(args[4]);
        } catch (FileNotFoundException e) {
            System.out.println("File with traveler data not found");
            e.printStackTrace();
        }
    }
}