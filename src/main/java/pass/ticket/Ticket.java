package pass.ticket;

import com.google.gson.annotations.Expose;
import enums.Status;
import enums.ValidityState;
import org.assertj.core.util.VisibleForTesting;
import pass.EPassDetails;
import pass.ValidityPeriod;

import javax.persistence.*;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static pass.ticket.TicketJsonWriter.quote;


@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Expose(serialize = false)
    private long id;
    @Enumerated(EnumType.STRING)
    private ValidityState validityState;
    private LocalDateTime activationDate;
    private LocalDate validityDate;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "e_pass_id")
    private EPassDetails ePassDetails;
    private transient Status status;
    private transient ValidityPeriod validityPeriod;

//    public Ticket(String state, String status, String filePathResult, String filePathTravelerJSON) {
//        this.filePath = filePathResult;
//        this.validityState = ValidityState.valueOf(state);
//        this.status = Status.valueOf(status);
//        isStatusAndStateCorrect();
//        initializeDates();
//        ePassDetails = new EPassDetails(filePathTravelerJSON, this.status, validityPeriod);
//
//    }

    public Ticket(String state, String status) {
        this.validityState = ValidityState.valueOf(state);
        this.status = Status.valueOf(status);
        initializeData();
    }

    public Ticket() {
    }

    private void initializeData() {
        DataInitializer dataInitializer = new DataInitializer(status, validityState);
        activationDate = dataInitializer.getActivationDate();
        validityDate = dataInitializer.getValidityDate();
        validityPeriod = dataInitializer.getValidityPeriod();
        ePassDetails = dataInitializer.getRandomEPassDetails();
    }

    public void setEPassDetailsFromJSON(String filePathTravelerJSON) throws FileNotFoundException {
        ePassDetails = new EPassDetails(filePathTravelerJSON, this.status, validityPeriod);
    }

//    public void setEPassDetailsRandomly() {
//        ePassDetails = new EPassDetails(random.getValidityKind(), random.getValidityPassType(), random.getName(), random.getSurname(), random.getPassportNumber(), this.status, validityPeriod);
//    }

    public String toString() {
        String connector = ": ";
        String endLine = ",\n";
        String newObject = "{\n";
        String endObject = "}";
        String newLine = "\n";
        StringBuilder re = new StringBuilder();
        re.append(newObject).append(
                "  \"validityState\"").append(connector).append(quote(validityState)).append(endLine).append(
                "  \"activationDate\"").append(connector).append(quote(activationDate)).append(endLine).append(
                "  \"validityDate\"").append(connector).append(quote(validityDate)).append(endLine).append(
                "  \"epassDetails\"").append(connector).append(newObject).append(
                "    \"kind\"").append(connector).append(quote(ePassDetails.getKind())).append(endLine).append(
                "    \"type\"").append(connector).append(quote(ePassDetails.getType())).append(endLine).append(
                "    \"traveler\"").append(connector).append(newObject).append(
                "      \"fullName\"").append(connector).append(quote(ePassDetails.getTraveler().getSecuredFullName())).append(endLine).append(
                "      \"passportNumber\"").append(connector).append(quote(ePassDetails.getTraveler().getSecuredPassport())).append(newLine).append(
                "    ").append(endObject).append(endLine).append(
                "    \"status\"").append(connector).append(quote(status)).append(endLine).append(
                "    \"validityPeriod\"").append(connector).append(newObject).append(
                "      \"startDate\"").append(connector);

        if (validityPeriod == null) {
            re.append(quote(null)).append(endLine).append(
                    "      \"endDate\"").append(connector).append(quote(null));
        } else {
            re.append(quote(validityPeriod.getStartDate())).append(endLine).append(
                    "      \"endDate\"").append(connector).append(quote(validityPeriod.getEndDate()));
        }
        re.append(newLine).append(
                "    ").append(endObject).append(newLine).append(
                "  ").append(endObject).append(newLine).append(
                endObject);
        return re.toString();
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }

    public long getId() {
        return id;
    }

    public EPassDetails getEPassDetails() {
        return ePassDetails;
    }

    public ValidityState getValidityState() {
        return validityState;
    }

    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public Status getStatus() {
        return status;
    }

}