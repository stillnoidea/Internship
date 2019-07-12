package json;

import tickets.Ticket;

public class Main {

    public static void main(String[] args) {
        Ticket js = new Ticket("NOT_VALID", "BLOCKED", "C:\\Users\\urszula.mazur\\IdeaProjects\\DataGenerator\\result.json", "C:\\Users\\urszula.mazur\\IdeaProjects\\DataGenerator\\traveler.json");
        js.writeToJson();
        System.out.println(js.toString());
    }
}
