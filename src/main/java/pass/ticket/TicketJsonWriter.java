package pass.ticket;

import java.io.FileWriter;
import java.io.IOException;

public class TicketJsonWriter {
    private transient FileWriter fileWriter;
    private Ticket ticket;

    public TicketJsonWriter(Ticket ticket, String filePath) throws IOException {
        this.ticket = ticket;
        fileWriter = new FileWriter(filePath);
    }

    public void writeToJson() {
        try {
            fileWriter.write(ticket.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String quote(Object o) {
        if (o == null) {
            return "\"null\"";
        }
        return "\"" + o.toString() + "\"";
    }

}
