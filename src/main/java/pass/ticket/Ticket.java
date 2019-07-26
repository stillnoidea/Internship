package pass.ticket;

import com.google.gson.annotations.Expose;
import enums.Status;
import enums.ValidityState;
import pass.EPassDetails;
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

    public Ticket(String state, String status) {
        this.validityState = ValidityState.valueOf(state);
        initializeData(Status.valueOf(status));
    }

    public Ticket() {
    }

    private void initializeData(Status status) {
        DataInitializer dataInitializer = new DataInitializer(status, validityState);
        activationDate = dataInitializer.getActivationDate();
        validityDate = dataInitializer.getValidityDate();
        ePassDetails = dataInitializer.getRandomEPassDetails();
    }

    public void setEPassDetailsFromJSON(String filePathTravelerJSON) throws FileNotFoundException {
        ePassDetails.setTravelerDataFromJson(filePathTravelerJSON);
    }

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
                "    \"status\"").append(connector).append(quote(ePassDetails.getStatus())).append(endLine).append(
                "    \"validityPeriod\"").append(connector).append(newObject).append(
                "      \"startDate\"").append(connector);

        if (ePassDetails.getValidityPeriod() == null) {
            re.append(quote(null)).append(endLine).append(
                    "      \"endDate\"").append(connector).append(quote(null));
        } else {
            re.append(quote(ePassDetails.getValidityPeriod().getStartDate())).append(endLine).append(
                    "      \"endDate\"").append(connector).append(quote(ePassDetails.getValidityPeriod().getEndDate()));
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

    public long getId() {
        return id;
    }

}