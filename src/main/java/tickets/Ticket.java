package tickets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.tools.javac.util.Pair;
import enums.Status;
import enums.ValidityState;
import json.LocalDateAdapter;
import json.LocalDateTimeAdapter;
import org.assertj.core.util.VisibleForTesting;
import randomizer.DataGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ticket {
    private ValidityState validityState;
    private LocalDateTime activationDate;
    private LocalDate validityDate;
    private transient Status status;
    private transient ValidityPeriod validityPeriod;
    private EPassDetails ePassDetails;
    private transient DataGenerator random = new DataGenerator();
    private transient String filePath;

    public Ticket(String state, String status, String filePathResult) {
        this.filePath = filePathResult;
        this.validityState = ValidityState.valueOf(state);
        this.status = Status.valueOf(status);
//        checkInput();
        initializeDates();
        ePassDetails = new EPassDetails(random.getValidityKind(), random.getValidityPassType(), random.getName(), random.getSurname(), random.getPassportNumber(), this.status, validityPeriod);
    }

//    public Ticket(String state, String status,  String filePathResult, String name, String surname, String passportNo, String passKind, String passType) {
//        this.filePath=filePathResult;
//        this.validityState = ValidityState.valueOf(state);
//        this.status = Status.valueOf(status);
//        checkInput();
//        initializeDates();
//        ePassDetails = new EPassDetails(passKind, passType, name, surname, passportNo, this.status, validityPeriod);
//
//    }

    public Ticket(String state, String status, String filePathResult, String filePathTravelerJSON) {
        this.filePath = filePathResult;
        this.validityState = ValidityState.valueOf(state);
        this.status = Status.valueOf(status);
        checkInput();
        initializeDates();
        ePassDetails = new EPassDetails(filePathTravelerJSON, this.status, validityPeriod);

    }

    private Gson getGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .serializeNulls()
                .create();
    }

    public String toString() {
        return getGson().toJson(this);
    }

    public void writeToJson() {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            getGson().toJson(this, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @VisibleForTesting
    protected void initializeValidDates() {
        validityPeriod = new ValidityPeriod(random.getValidPeriod());
        setDateValidityPass();
    }

    @VisibleForTesting
    protected void initializeNotStartedDates() {
        Pair<LocalDate, LocalDate> period = random.getNotStartedPeriod();
        if (period != null) {                                                               //status with date
            validityPeriod = new ValidityPeriod(period);
            if (validityState.name().equals("NOT_STARTED")) {                                  //not started must have date
                validityDate = random.getDateBetween(validityPeriod.getPeriod());
            } else {
                validityDate = random.getNotValidDate(validityPeriod.getPeriod());            //not valid with or without date
            }
        } else {
            validityPeriod = new ValidityPeriod();
            validityDate = null;                                                            //status without date, state without date
        }
    }

    @VisibleForTesting
    protected void initializeExpiredDates() {
        if (!validityState.name().equals("VALID_YESTERDAY")) {
            validityPeriod = new ValidityPeriod(random.getExpiredPeriod());              //expired
            validityDate = random.getNotValidDate(validityPeriod.getPeriod());           // not_valid
        } else {
            validityPeriod = new ValidityPeriod(random.getValidYesterdayPeriod());      //expired
            validityDate = random.getValidYesterdayDate();                                //val yesterday
        }
    }

    @VisibleForTesting
    protected void initializeBlockedOrRefundedDates() {
        Pair<LocalDate, LocalDate> period1 = random.getPeriod();
        if (period1 != null) {
            validityPeriod = new ValidityPeriod(period1);                              //blocked/refunded with date
            validityDate = random.getNotValidDate(validityPeriod.getPeriod());      //not valid with or without date
        } else {
            validityPeriod = new ValidityPeriod();
            validityDate = null;
        }
    }

    private void setDateValidityPass() {
        switch (validityState) {
            case NOT_VALID:
                validityDate = random.getNotValidDate(validityPeriod.getPeriod());
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
                    validityDate = random.getDateBetween(new Pair<>(LocalDate.now().plusDays(1), validityPeriod.getEndDate()));
                }
                break;
        }
    }

    @VisibleForTesting
    protected  void checkInput() {
        if (status.name().equals("EXPIRED") && (!(validityState.name().equals("NOT_VALID") || validityState.name().equals("VALID_YESTERDAY")))) {
            throw new IllegalArgumentException("");
        } else if (status.name().equals("NOT_STARTED") && (!(validityState.name().equals("NOT_VALID") || validityState.name().equals("NOT_STARTED")))) {
            throw new IllegalArgumentException("");
        } else if ((status.name().equals("BLOCKED") || status.name().equals("REFUNDED")) && (!(validityState.name().equals("NOT_VALID")))) {
            throw new IllegalArgumentException("");
        }
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }


}