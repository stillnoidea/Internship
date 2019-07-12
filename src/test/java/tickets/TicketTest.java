package tickets;

import com.google.gson.*;
import enums.Status;
import enums.ValidityState;
import json.LocalDateAdapter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class TicketTest {

    @Test
    void dosmthTest() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        Ticket t = new Ticket(ValidityState.valueOf("NOT_VALID"), Status.valueOf("VALID"));

        System.out.println(gson.toJson(t));
    }

}
