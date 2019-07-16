package json;

import enums.Status;
import enums.ValidityState;
import tickets.Ticket;

import java.util.Scanner;

public class Main {
    private int number = 0;
    private String state;
    private String status;
    private String resultPath;
    private String dataPath;
    private Scanner scan = new Scanner(System.in);
    private Ticket js;

    private void getState() {
        System.out.println("Enter expected ticket state");
        state = scan.nextLine();
        number=1;
        try {
            ValidityState.valueOf(state);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid state, try again");
            number = 0;
        }
        runTerminal();
    }

    private void getStatus() {
        System.out.println("Enter expected pass status");
        status = scan.nextLine();
        number=2;
        try {
            Status.valueOf(status);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status, try again");
            number = 1;
        }
        runTerminal();
    }

    private void getTicket() {
        number=3;
        try {
            js = new Ticket(state, status);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            number = 0;
        }
        runTerminal();
    }


    private void getPaths() {
        System.out.println("Enter filepath for result json file");
        resultPath = scan.nextLine();
        System.out.println("Do you want enter filepath to json file with traveler data? (enter no or filepath");
        dataPath = scan.nextLine();
        js.setFilePath(resultPath);
        if (dataPath.length() > 8) {
            js.setePassDetailsJSON(dataPath);
        } else {
            js.setePassDetailsRandom();
        }
    }

    private void getResult() {
        js.writeToJson();
        System.out.println(js.toString());
    }


    private void runTerminal() {
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
                getResult();
                break;
        }
    }

    public static void main(String[] args) {
        new Main().runTerminal();
    }
}
