package tickets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import enums.Status;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Entity
@Table(name = "epassdetails")
public class EPassDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Expose(serialize = false)
    private long id;
    private String kind;
    private String type;
    @OneToOne
    @JoinColumn(name="traveler_id", unique = true)
    private Traveler traveler;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Embedded
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

    public EPassDetails() {
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

    public long getId() {
        return id;
    }
}