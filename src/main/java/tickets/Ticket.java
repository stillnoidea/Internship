package tickets;

import enums.Kind;
import enums.PassType;
import enums.Status;
import enums.ValidityState;
import randomizer.DataGenerator;

import com.sun.tools.javac.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ticket {
    private ValidityState validityState;
    private LocalDateTime activationDate;
    private LocalDate validityDate;
    private transient Status status;
    private transient ValidityPeriod validityPeriod;
    private EPassDetails epassDetails;
    private transient DataGenerator random = new DataGenerator();

    public Ticket(ValidityState state, Status status) {
        this.validityState = state;
        this.status = status;
        initializeDates();
        epassDetails = new EPassDetails(random.getValidityKind(), random.getValidityPassType(), random.getName(), random.getSurname(), random.getPassportNumber(), status, validityPeriod);


    }

    public Ticket(ValidityState state, Status status, String name, String surname, String passportNo, String passKind, String passType) {
        this.validityState = state;
        this.status = status;
        initializeDates();
        epassDetails = new EPassDetails(passKind, passType, name, surname, passportNo, status, validityPeriod);

    }

    public void dosmth() {
    }

    private void initializeDates() {
        initializePeriod();
        activationDate = random.getActivationDate(validityDate);
    }

    private void initializePeriod() {
        switch (status) {
            case VALID:
                validityPeriod = new ValidityPeriod(random.getValidPeriod());
                setDateValidityPass();
                break;
            case EXPIRED:
                if (!validityState.name().equals("VALID_YESTERDAY")) {
                    validityPeriod = new ValidityPeriod(random.getExpiredPeriod());              //expired
                    validityDate = random.getNotValidDate(validityPeriod.getPeriod());           // not_valid
                } else {
                    validityPeriod = new ValidityPeriod(random.getValidYesterdayPeriod());      //expired
                    validityDate = random.getValidYesterdayDate();                                //val yesterday
                }
                break;
            case NOT_STARTED:
                validityPeriod = new ValidityPeriod(random.getNotStartedPeriod());              //not started
                if (validityState.name().equals("NOT_STARTED")) {                                //not started
                    validityDate = random.getDateBetween(validityPeriod.getPeriod());
                } else {
                    validityDate = random.getNotValidDate(validityPeriod.getPeriod());            //not valid with or without date
                }
                break;
            default:
                validityPeriod = new ValidityPeriod(random.getPeriod());            //blocked/refunded
                validityDate = random.getNotValidDate(validityPeriod.getPeriod());    //not valid with or without date
        }
    }

    private void setDateValidityPass() {
        switch (validityState) {
            case NOT_VALID:
                validityDate = random.getNotValidDate(validityPeriod.getPeriod());
            case VALID_TODAY:
                validityDate = random.getValidTodayDate();
            case VALID_YESTERDAY:
                validityDate = random.getValidYesterdayDate();
            case NOT_STARTED:
                validityDate = random.getDateBetween(new Pair<>(validityPeriod.getStartDate(), random.getValidYesterdayDate()));
        }
    }



}