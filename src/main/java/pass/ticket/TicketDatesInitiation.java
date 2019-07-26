package pass.ticket;

import enums.Status;
import enums.ValidityState;
import javafx.util.Pair;
import pass.ValidityPeriod;
import java.time.LocalDate;
import java.time.LocalDateTime;

class TicketDatesInitiation {
    private transient Status status;
    private ValidityState validityState;
    private transient ValidityPeriod validityPeriod;
    private LocalDateTime activationDate;
    private LocalDate validityDate;

    TicketDatesInitiation(Status status, ValidityState validityState) {
        this.status = status;
        this.validityState = validityState;
        initializeDates();
    }

    private void initializeDates() {
        initializePeriod();
        if (validityDate != null) {
            activationDate = DataInitializer.random.getActivationDate(validityDate);
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
        validityPeriod = new ValidityPeriod(DataInitializer.random.getValidPeriod());
        setValidityDateForValidStatus();
    }

    private void initializeNotStartedDates() {
        Pair<LocalDate, LocalDate> period = DataInitializer.random.getNotStartedPeriod();
        if (period != null) {                                                               //status with date
            validityPeriod = new ValidityPeriod(period);
            if (validityState.name().equals("NOT_STARTED")) {                                  //not started must have date
                validityDate = DataInitializer.random.getDateBetween(validityPeriod.getPeriod());
            } else {
                validityDate = DataInitializer.random.getNotValidDate(validityPeriod.getPeriod());            //not valid with or without date
            }
        } else {
            validityPeriod = new ValidityPeriod();
            validityDate = null;                                                            //status without date, state without date
        }
    }

    private void initializeExpiredDates() {
        if (!validityState.name().equals("VALID_YESTERDAY")) {
            validityPeriod = new ValidityPeriod(DataInitializer.random.getExpiredPeriod());              //expired
            validityDate = DataInitializer.random.getNotValidDate(validityPeriod.getPeriod());           // not_valid
        } else {
            validityPeriod = new ValidityPeriod(DataInitializer.random.getValidYesterdayPeriod());      //expired
            validityDate = DataInitializer.random.getValidYesterdayDate();                                //val yesterday
        }
    }

    private void initializeBlockedOrRefundedDates() {
        Pair<LocalDate, LocalDate> period1 = DataInitializer.random.getPeriod();
        if (period1 != null) {
            validityPeriod = new ValidityPeriod(period1);                              //blocked/refunded with date
            validityDate = DataInitializer.random.getNotValidDate(validityPeriod.getPeriod());      //not valid with or without date
        } else {
            validityPeriod = new ValidityPeriod();
            validityDate = null;
        }
    }

    private void setValidityDateForValidStatus() {
        switch (validityState) {
            case NOT_VALID:
                validityDate = DataInitializer.random.getNotValidDate(validityPeriod.getPeriod());
                break;
            case VALID_TODAY:
                validityDate = DataInitializer.random.getValidTodayDate();
                break;
            case VALID_YESTERDAY:
                validityDate = DataInitializer.random.getValidYesterdayDate();
                break;
            case NOT_STARTED:
                if (validityPeriod.getStartDate().isEqual(DataInitializer.random.getValidYesterdayDate())) {
                    validityDate = validityPeriod.getStartDate();
                } else {
                    validityDate = DataInitializer.random.getDateBetween(new Pair<>(LocalDate.now().plusDays(1), validityPeriod.getEndDate())); //sypie się tu
                }
                break;
        }
    }

    ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }

    LocalDateTime getActivationDate() {
        return activationDate;
    }

    LocalDate getValidityDate() {
        return validityDate;
    }
}
