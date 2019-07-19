package json;

import enums.Status;
import enums.ValidityState;
import tickets.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Utils {
    private int number = 0;
    private String state;
    private String status;
    private String resultPath;
    private Scanner scan = new Scanner(System.in);
    private Ticket ticket;

    public void runTerminal() {
        System.out.println("Hello, enter command, depending on whether you want to generate new ticket or load one from" +
                " database : (GENERATE/LOAD)");

        String command = scan.nextLine();
        if (command.equals("GENERATE")) {
            generateTicket();
        } else if (command.equals("LOAD")) {
            loadTicket();
        } else {
            runTerminal();
        }
    }

    private void getState() {
        System.out.println("Enter expected ticket state");
        state = scan.nextLine();
        number = 1;
        try {
            ValidityState.valueOf(state);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid state, try again");
            number = 0;
        }
        generateTicket();
    }

    private void getStatus() {
        System.out.println("Enter expected pass status");
        status = scan.nextLine();
        number = 2;
        try {
            Status.valueOf(status);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status, try again");
            number = 1;
        }
        generateTicket();
    }

    private void getTicket() {
        number = 3;
        try {
            ticket = new Ticket(state, status);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            number = 0;
        }
        generateTicket();
    }

    private void setResultPath() {
        System.out.println("Enter filepath for result json file: ");
        resultPath = scan.nextLine();
        if (!resultPath.contains(".json")) {
            resultPath += ".json";
        }
        try {
            ticket.setFilePath(resultPath);
        } catch (IOException e) {
            System.out.println("Invalid output file path");
            e.printStackTrace();
            setResultPath();
        }
    }

    private void setDataPath() {
        System.out.println("Do you want enter filepath to json file with traveler data? (enter NO or wanted filepath)");
        String dataPath = scan.nextLine();

        if (dataPath.length() > 8) {
            try {
                ticket.setePassDetailsJSON(dataPath);
            } catch (FileNotFoundException e) {
                System.out.println("Invalid traveler data path");
                setDataPath();
            }
        } else {
            ticket.setePassDetailsRandom();
        }
    }

    private void generateTicket() {
        switch (number) {
            case 0:
                getState();
                break;
            case 1:
                getStatus();
                break;
            case 2:
                getTicket();
                break;
            case 3:
                setResultPath();
                setDataPath();
                saveTicketTo();
                break;
        }
        endOrAgain();
    }

    private void saveTicketTo() {
        System.out.println("Do you want to save ticket to json or/and to print it on console? (JSON | CONSOLE | DATABASE | ALL )");
        String save = scan.nextLine();
        switch (save) {
            case "JSON":
                ticket.writeToJson();
                System.out.println("Ticket is in: " + resultPath);
                break;
            case "CONSOLE":
                System.out.println(ticket);
                break;
            case "DATABASE":
                addToDatabase();
                break;
            case "ALL":
                ticket.writeToJson();
                System.out.println("Ticket is in: " + resultPath);
                System.out.println(ticket);
                addToDatabase();
                break;
        }
        endOrAgain();
    }

    private void addToDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(ticket);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) entityTransaction.rollback();
            e.printStackTrace();
        } finally {
            if (entityManager != null) entityManager.close();
        }
        System.out.println("Ticket id: " + ticket.getId());
    }

    private void loadTicket() {
        System.out.println("Enter ticket id");
        Long id = scan.nextLong();
        scan.nextLine();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            ticket = entityManager.find(Ticket.class, id);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) entityTransaction.rollback();
            e.printStackTrace();
        } finally {
            if (entityManager != null) entityManager.close();
        }
        saveTicketFromDatabase();
    }

    private void saveTicketFromDatabase() {
        System.out.println("Do you want to save ticket to json or/and to print it on console? (JSON | CONSOLE | BOTH )");
        String save = scan.nextLine();
        switch (save) {
            case "JSON":
                setResultPath();
                ticket.writeToJson();
                if (resultPath.contains("C:\\")) {
                    System.out.println("Ticket is in: " + resultPath);
                } else {
                    System.out.println("File is in main program package");
                }
                break;
            case "CONSOLE":
                System.out.println(ticket);
                break;
            case "BOTH":
                setResultPath();
                ticket.writeToJson();
                System.out.println("Ticket is in: " + resultPath);
                System.out.println(ticket);
                break;
        }
        endOrAgain();
    }

    private void endOrAgain() {
        System.out.println("Do you want make something else or end? (END | DO)");
        String command = scan.nextLine();
        if (command.equals("DO")) {
            ticket = null;
            number = 0;
            runTerminal();
        } else {
            System.exit(0);
        }
    }

}



