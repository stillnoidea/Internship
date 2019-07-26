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
    private Scanner scanner = new Scanner(System.in);

    public void runTerminal() {
        getCommand();
        loadOrGenerate(args);
    }

    private void getCommand() {
        System.out.println("Enter command:");
        String command = scanner.nextLine();
        args = command.split(" ");
    }

    private void loadOrGenerate(String[] args) {
        this.args = args;
        String command = args[0];
        if (command.equals("LOAD")) {
            loadCommand();
        } else {
            generateCommand();
        }
    }

    private void loadCommand() {
        tryToLoadFromDatabase();
        processTicketFromDatabase();
    }

    private void tryToLoadFromDatabase() {
        try {
            loadTicket(Long.parseLong(args[1]));
        } catch (NumberFormatException e) {
            System.out.println("Invalid ticket id");
        }
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

    private void getTicketFromDatabase(Pair<EntityManager, EntityTransaction> entity, long id) {
        entity.getValue().begin();
        ticket = entity.getKey().find(Ticket.class, id);
        entity.getValue().commit();
    }

    private void processTicketFromDatabase() {
        if (isTicketInDatabase()) {
            whereSaveTicketFromDatabase();
        } else {
            System.out.println("Invalid ticket id");
        }
    }

    private boolean isTicketInDatabase() {
        return ticket != null;
    }

    private void whereSaveTicketFromDatabase() {
        if (args.length > 2) {
            saveTicketToJson(args[2]);
            printFilePath(args[2]);
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

    private void printFilePath(String path) {
        System.out.println(path);
    }

    private void printTicketOnConsole() {
        System.out.println(ticket.toString());
    }

    private void generateCommand() {
        if (args.length == 4 || args.length == 5) {
            if (isDataTicketValid()) {
                initializeTicket();
                whereSaveTicketGenerated(args[3]);
            }
        } else System.out.println("Invalid command");
    }

    private boolean isDataTicketValid() {
        try {
            checkState(args[1]);
            checkStatus(args[2]);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid state or status value");
            return false;
        }
        return true;
    }

    private void checkState(String state) throws IllegalArgumentException {
        ValidityState.valueOf(state);
    }

    private void checkStatus(String status) throws IllegalArgumentException {
        Status.valueOf(status);
    }

    private void initializeTicket() {
        generateTicket(args[1], args[2]);
        if (args.length == 5) {
            setTravelerData();
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

    private void setTravelerData() {
        try {
            ticket.setEPassDetailsFromJSON(args[4]);
        } catch (FileNotFoundException e) {
            System.out.println("File with traveler data not found");
            e.printStackTrace();
        }
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

    public void endOrContinue() {
        String command;
        do {
            System.out.println("Enter EXIT to end program or next command");
            command = scanner.nextLine();
            if (!command.equals("EXIT")) {
                loadOrGenerate(command.split(" "));
            }
        } while (!command.equals("EXIT"));
    }
}