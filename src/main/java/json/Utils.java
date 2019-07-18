package json;

import enums.Status;
import enums.ValidityState;
import org.hibernate.Session;
import org.hibernate.Transaction;
import presistence.HibernateUtil;
import tickets.Ticket;

import java.util.Scanner;

public class Utils {
    private int number = 0;
    private String state;
    private String status;
    private String resultPath;
    private String dataPath;
    private Scanner scan = new Scanner(System.in);
    private Ticket ticket;
    private String command;

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

    private void getTicketId(){
        //TODO soutprint ticket id after settings paths and before saving it to json and so on
    }

    private void getPaths() {
        System.out.println("Enter filepath for result json file");
        resultPath = scan.nextLine();
        System.out.println("Do you want enter filepath to json file with traveler data? (enter no or filepath");
        dataPath = scan.nextLine();
        ticket.setFilePath(resultPath);
        if (dataPath.length() > 8) {
            ticket.setePassDetailsJSON(dataPath);
        } else {
            ticket.setePassDetailsRandom();
        }
    }

    private void addToDatabase() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            session.save(ticket.getePassDetails().getTraveler());
            session.save(ticket.getePassDetails());
            session.save(ticket);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
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
                getPaths();
                afterLoad();
                addToDatabase();
                break;
        }
        endOrAgain();
    }

    private void loadTicket() {
        System.out.println("Enter ticket id");
        Long id = scan.nextLong();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            ticket = session.load(Ticket.class, id);
            System.out.println(ticket);
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        afterLoad();
    }

    private void afterLoad() {
        System.out.println("Do you want to save ticket to json or/and to print it on console? (JSON | CONSOLE | BOTH");
        String save = scan.nextLine();
        switch (save) {
            case "JSON":
                ticket.writeToJson();
            case "CONSOLE":
                System.out.println(ticket);
            case "BOTH":
                ticket.writeToJson();
                System.out.println(ticket);
        }
        endOrAgain();
    }

    private void runTerminal() {
        System.out.println("Hello, Enter command: (GENERATE/LOAD");
        command = scan.nextLine();
        if (command.equals("GENERATE")) {
            generateTicket();
        } else if (command.equals("LOAD")) {
            loadTicket();
        }
    }

    private void endOrAgain() {
        System.out.println("Do you want make something else or end? (END | DO");
        String command = scan.nextLine();
        if (command.equals("DO")) {
            runTerminal();
        }
    }

    public static void main(String[] args) {
        new Utils().runTerminal();
    }
}



