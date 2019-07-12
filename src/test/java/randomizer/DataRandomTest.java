package randomizer;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

class DataRandomTest {

    @Test
    void getValidToday() {
        DataGenerator dr = new DataGenerator();
        System.out.println(dr.getActivationDate(LocalDate.now()).toString());
        System.out.println(dr.getActivationDate(LocalDate.now()).toString());
        System.out.println(dr.getActivationDate(LocalDate.now()).toString());
        System.out.println(dr.getActivationDate(LocalDate.now()).toString());
        System.out.println(dr.getActivationDate(LocalDate.now()).toString());
        System.out.println(dr.getActivationDate(LocalDate.now()).toString());
    }

    @Test
    void getNotValid() {
    }

    @Test
    void getValidYesterday() {
    }

    @Test
    void getNotStarted() {
    }

    @Test
    void getName() {
    }

    @Test
    void getValidityPassType() {
    }

    @Test
    void getValidityKind() {
    }

    @Test
    void getPassportNumber() {
    }
}