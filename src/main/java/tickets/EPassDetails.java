package tickets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import enums.Status;

import javax.persistence.*;
import java.io.*;
import java.util.stream.Stream;

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

    String getKind() {
        return kind;
    }

    String getType() {
        return type;
    }

    Traveler getTraveler() {
        return traveler;
    }

    private void readFromJSON(String externalDataFilePath) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(externalDataFilePath)));

        Stream<String> ss = reader.lines();

        Object[] list = ss.toArray();
        String[] l = list[1].toString().split("\"");
        String[] l1 = list[2].toString().split("\"");
        String[] l2 = list[3].toString().split("\"");
        this.traveler = new Traveler(l[3], l1[3], l2[3]);
        String[] l3 = list[4].toString().split("\"");
        this.kind = l3[3];
        String[] l4 = list[5].toString().split("\"");
        this.type = l4[3];
//        JsonObject travelerJson;
//
//        BufferedReader reader = new BufferedReader(new FileReader(new File(externalDataFilePath)));
//
//        Gson gson = new Gson();
//        travelerJson = gson.fromJson(reader, JsonObject.class);
//
//        this.kind = travelerJson.get("passKind").getAsString();
//        this.type = travelerJson.get("passType").getAsString();
//
//        this.traveler = new Traveler(travelerJson);
    }

    public long getId() {
        return id;
    }
}