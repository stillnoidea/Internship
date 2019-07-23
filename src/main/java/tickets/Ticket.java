package tickets;

import com.google.gson.annotations.Expose;
import enums.Status;
import enums.ValidityState;
import javafx.util.Pair;
import org.assertj.core.util.VisibleForTesting;
import randomizer.DataGenerator;

import javax.persistence.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private transient Status status;
    private transient ValidityPeriod validityPeriod;
    private transient FileWriter fileWriter;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "e_pass_id")
    private EPassDetails ePassDetails;
    private transient DataGenerator random = new DataGenerator();
    private transient String filePath;

    public Ticket(String state, String status, String filePathResult) {
        this.filePath = filePathResult;
        this.validityState = ValidityState.valueOf(state);
        this.status = Status.valueOf(status);
        checkInput();
        initializeDates();
        ePassDetails = new EPassDetails(random.getValidityKind(), random.getValidityPassType(), random.getName(), random.getSurname(), random.getPassportNumber(), this.status, validityPeriod);
    }

//    public Ticket(String state, String status, String filePathResult, String filePathTravelerJSON) {
//        this.filePath = filePathResult;
//        this.validityState = ValidityState.valueOf(state);
//        this.status = Status.valueOf(status);
//        checkInput();
//        initializeDates();
//        ePassDetails = new EPassDetails(filePathTravelerJSON, this.status, validityPeriod);
//
//    }

    public Ticket(String state, String status) {
        this.validityState = ValidityState.valueOf(state);
        this.status = Status.valueOf(status);
        checkInput();
        initializeDates();
    }

    public Ticket() {
    }

    public void setePassDetailsJSON(String filePathTravelerJSON) throws FileNotFoundException {
        ePassDetails = new EPassDetails(filePathTravelerJSON, this.status, validityPeriod);
    }

    public void setePassDetailsRandom() {
        ePassDetails = new EPassDetails(random.getValidityKind(), random.getValidityPassType(), random.getName(), random.getSurname(), random.getPassportNumber(), this.status, validityPeriod);
    }

    public void setFilePath(String filePath) throws IOException {
        this.filePath = filePath;
        fileWriter = new FileWriter(filePath);
    }

    public String toString() {
        String connector = ": ";
        String endLine = ",\n";
        String newObject = "{\n";
        String endObject = "}";
        String newLine = "\n";
        StringBuilder re = new StringBuilder();
        re.append(newObject).append(
                "  \"validityState\"").append(connector).append(quote(this.validityState)).append(endLine).append(
                "  \"activationDate\"").append(connector).append(quote(this.activationDate)).append(endLine).append(
                "  \"validityDate\"").append(connector).append(quote(this.validityDate)).append(endLine).append(
                "  \"epassDetails\"").append(connector).append(newObject).append(
                "    \"kind\"").append(connector).append(quote(this.ePassDetails.getKind())).append(endLine).append(
                "    \"type\"").append(connector).append(quote(this.ePassDetails.getType())).append(endLine).append(
                "    \"traveler\"").append(connector).append(newObject).append(
                "      \"fullName\"").append(connector).append(quote(this.ePassDetails.getTraveler().getSecuredFullName())).append(endLine).append(
                "      \"passportNumber\"").append(connector).append(quote(this.ePassDetails.getTraveler().getSecuredPassport())).append(newLine).append(
                "    ").append(endObject).append(endLine).append(
                "    \"status\"").append(connector).append(quote(this.status)).append(endLine).append(
                "    \"validityPeriod\"").append(connector).append(newObject).append(
                "      \"startDate\"").append(connector);

        if (this.validityPeriod == null) {
            re.append(quote(null)).append(endLine).append(
                    "      \"endDate\"").append(connector).append(quote(null));
        } else {
            re.append(quote(this.validityPeriod.getStartDate())).append(endLine).append(
                    "      \"endDate\"").append(connector).append(quote(this.validityPeriod.getEndDate()));
        }
        re.append(newLine).append(
                "    ").append(endObject).append(newLine).append(
                "  ").append(endObject).append(newLine).append(
                endObject);
        return re.toString();
    }

    public void writeToJson() {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(this.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String quote(Object o) {
        if (o == null) {
            return "\"null\"";
        }
        return "\"" + o.toString() + "\"";
    }

    private void initializeDates() {
        initializePeriod();
        if (validityDate != null) {
            activationDate = random.getActivationDate(validityDate);
        } else {
            activationDate = null;
        }
    }

    private void initializePeriod() {
        switch (status) {
            case VALID:
                initializeValidDates();
                break;
            case EXPIRED:
                initializeExpiredDates();
                break;
            case NOT_STARTED:
                initializeNotStartedDates();
                break;
            default:
                initializeBlockedOrRefundedDates();
        }
    }

    private void initializeValidDates() {
        validityPeriod = new ValidityPeriod(random.getValidPeriod());
        setDateValidityPass();
    }

    private void initializeNotStartedDates() {
        Pair<LocalDate, LocalDate> period = random.getNotStartedPeriod();
        if (period != null) {                                                               //status with date
            validityPeriod = new ValidityPeriod(period);
            if (validityState.name().equals("NOT_STARTED")) {                                  //not started must have date
                validityDate = random.getDateBetween(validityPeriod.obtainPeriod());
            } else {
                validityDate = random.getNotValidDate(validityPeriod.obtainPeriod());            //not valid with or without date
            }
        } else {
            validityPeriod = new ValidityPeriod();
            validityDate = null;                                                            //status without date, state without date
        }
    }

    private void initializeExpiredDates() {
        if (!validityState.name().equals("VALID_YESTERDAY")) {
            validityPeriod = new ValidityPeriod(random.getExpiredPeriod());              //expired
            validityDate = random.getNotValidDate(validityPeriod.obtainPeriod());           // not_valid
        } else {
            validityPeriod = new ValidityPeriod(random.getValidYesterdayPeriod());      //expired
            validityDate = random.getValidYesterdayDate();                                //val yesterday
        }
    }

    private void initializeBlockedOrRefundedDates() {
        Pair<LocalDate, LocalDate> period1 = random.getPeriod();
        if (period1 != null) {
            validityPeriod = new ValidityPeriod(period1);                              //blocked/refunded with date
            validityDate = random.getNotValidDate(validityPeriod.obtainPeriod());      //not valid with or without date
        } else {
            validityPeriod = new ValidityPeriod();
            validityDate = null;
        }
    }

    private void setDateValidityPass() {
        switch (validityState) {
            case NOT_VALID:
                validityDate = random.getNotValidDate(validityPeriod.obtainPeriod());
                break;
            case VALID_TODAY:
                validityDate = random.getValidTodayDate();
                break;
            case VALID_YESTERDAY:
                validityDate = random.getValidYesterdayDate();
                break;
            case NOT_STARTED:
                if (validityPeriod.getStartDate().isEqual(random.getValidYesterdayDate())) {
                    validityDate = validityPeriod.getStartDate();
                } else {
                    validityDate = random.getDateBetween(new Pair<>(LocalDate.now().plusDays(1), validityPeriod.getEndDate())); //sypie się tu
                }
                break;
        }
    }

    @VisibleForTesting
    protected void checkInput() {
        if (status.name().equals("EXPIRED") && (!(validityState.name().equals("NOT_VALID") || validityState.name().equals("VALID_YESTERDAY")))) {
            throw new IllegalArgumentException("Given combination of state and status is incorrect");
        } else if (status.name().equals("NOT_STARTED") && (!(validityState.name().equals("NOT_VALID") || validityState.name().equals("NOT_STARTED")))) {
            throw new IllegalArgumentException("Given combination of state and status is incorrect");
        } else if ((status.name().equals("BLOCKED") || status.name().equals("REFUNDED")) && (!(validityState.name().equals("NOT_VALID")))) {
            throw new IllegalArgumentException("Given combination of state and status is incorrect");
        }
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

    public EPassDetails getePassDetails() {
        return ePassDetails;
    }


}