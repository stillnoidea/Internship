package randomizer;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DataRandomTest {

    @Test
    void getValidToday() {
        DataRandom dr = new DataRandom();
        Date date = dr.getValidToday().fst;
        Date date2 = dr.getValidToday().snd;

        System.out.println("year= " + (date.getYear()+1900) + " month= " + date.getMonth() + " day= " + date.getDate());
        System.out.println("year= " + (date2.getYear()+1900) + " month= " + date2.getMonth() + " day= " + date2.getDate());

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