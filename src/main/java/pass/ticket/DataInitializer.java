package pass.ticket;

import enums.Status;
import enums.ValidityState;
import randomizer.DataGenerator;
import pass.EPassDetails;
import pass.ValidityPeriod;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DataInitializer {
    private TicketDatesInitiation datesInitializer;
    static DataGenerator random = new DataGenerator();
    private transient Status status;

    DataInitializer(Status status, ValidityState validityState) {
        datesInitializer = new TicketDatesInitiation(status, validityState);
        this.status = status;
    }


    public EPassDetails getRandomEPassDetails() {
        return new EPassDetails(random.getValidityKind(), random.getValidityPassType(), random.getName(), random.getSurname(), random.getPassportNumber(), this.status, datesInitializer.getValidityPeriod());
    }

    ValidityPeriod getValidityPeriod() {
        return datesInitializer.getValidityPeriod();
    }

    LocalDateTime getActivationDate() {
        return datesInitializer.getActivationDate();
    }

    LocalDate getValidityDate() {
        return datesInitializer.getValidityDate();
    }
}