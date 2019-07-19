package tickets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import enums.Status;

import javax.persistence.*;
import java.io.*;

@Entity
@Table(name = "epassdetails")
public class EPassDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Expose(serialize = false)
    private long id;
    private String kind;
    private String type;
    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "traveler_id", unique = true)
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

    EPassDetails(String filepath, Status status, ValidityPeriod validityPeriod) throws FileNotFoundException {
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

    private void readFromJSON(String externalDataFilePath) throws FileNotFoundException {
        JsonObject travelerJson;

        BufferedReader reader = new BufferedReader(new FileReader(new File(externalDataFilePath)));

        Gson gson = new Gson();
        travelerJson = gson.fromJson(reader, JsonObject.class);

        this.kind = travelerJson.get("passKind").getAsString();
        this.type = travelerJson.get("passType").getAsString();

        this.traveler = new Traveler(travelerJson);
    }

    public long getId() {
        return id;
    }
}