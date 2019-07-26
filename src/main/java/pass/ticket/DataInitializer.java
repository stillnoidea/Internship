package pass.ticket;

import enums.Status;
import enums.ValidityState;
import pass.EPassDetails;
import randomizer.DataGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

class DataInitializer {
    private TicketDatesInitiation datesInitializer;
    static DataGenerator random = new DataGenerator();
    private transient Status status;

    DataInitializer(Status status, ValidityState validityState) {
        datesInitializer = new TicketDatesInitiation(status, validityState);
        this.status = status;
    }

    EPassDetails getRandomEPassDetails() {
        return new EPassDetails(random.getValidityKind(), random.getValidityPassType(), random.getName(), random.getSurname(), random.getPassportNumber(), this.status, datesInitializer.getValidityPeriod());
    }

    LocalDateTime getActivationDate() {
        return datesInitializer.getActivationDate();
    }

    LocalDate getValidityDate() {
        return datesInitializer.getValidityDate();
    }
}