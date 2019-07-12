package tickets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import enums.Status;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EPassDetails {
    private String kind;
    private String type;
    private Traveler traveler;
    private Status status;
    private ValidityPeriod validityPeriod;

    EPassDetails(String kind, String passType, String name, String surname, String passportNo, Status status, ValidityPeriod validityPeriod) {
        traveler = new Traveler(name, surname, passportNo);
        this.kind = kind;
        this.type = passType;
        this.status = status;
        this.validityPeriod = validityPeriod;
    }

    EPassDetails(String filepath, Status status, ValidityPeriod validityPeriod) {
        readFromJSON(filepath);
        this.status = status;
        this.validityPeriod = validityPeriod;
    }

    public String getKind() {
        return kind;
    }

    public String getType() {
        return type;
    }

    public Traveler getTraveler() {
        return traveler;
    }


    private void readFromJSON(String externalDataFilePath) {
        JsonObject travelerJson;

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(externalDataFilePath)))) {

            Gson gson = new Gson();
            travelerJson = gson.fromJson(reader, JsonObject.class);

            this.kind = travelerJson.get("passKind").getAsString();
            this.type = travelerJson.get("passType").getAsString();

            this.traveler = new Traveler(travelerJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}