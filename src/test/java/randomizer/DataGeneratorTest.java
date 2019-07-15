package randomizer;

import com.sun.tools.javac.util.Pair;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class DataGeneratorTest {
    private static DataGenerator dr = new DataGenerator();

    DataGeneratorTest() {
        dr = new DataGenerator();
    }

    @RepeatedTest(10)
    void getExpiredPeriod() {
        Pair<LocalDate, LocalDate> date = dr.getExpiredPeriod();
        assertTrue(date.fst.isBefore(date.snd));
        assertTrue(date.snd.isBefore(LocalDate.now()));
    }

    @RepeatedTest(10)
    void getNotStartedPeriod() {
        Pair<LocalDate, LocalDate> date = dr.getNotStartedPeriod();
        boolean isNull = (date == null);
        if (!isNull) {
            assertTrue(date.fst.isBefore(date.snd));
            assertTrue(date.fst.isAfter(LocalDate.now()));
        }
    }

    @RepeatedTest(10)
    void getPeriod() {
        Pair<LocalDate, LocalDate> date = dr.getPeriod();
        boolean isNull = (date == null);
        if (!isNull) {
            assertTrue(date.fst.isBefore(date.snd));
        }
    }

    @RepeatedTest(10)
    void getValidPeriod() {
        Pair<LocalDate, LocalDate> date = dr.getValidPeriod();
        assertTrue(date.fst.isBefore(date.snd));
        assertTrue(date.fst.isBefore(LocalDate.now())|| date.fst.isEqual(LocalDate.now()));
        assertTrue(date.snd.isAfter(LocalDate.now()) || date.snd.isEqual(LocalDate.now()));
    }

    @RepeatedTest(10)
    void getValidYesterdayPeriod() {
        Pair<LocalDate, LocalDate> date = dr.getValidYesterdayPeriod();
        assertTrue(date.fst.isBefore(date.snd));
        assertTrue(date.fst.isBefore(LocalDate.now()));
        assertEquals(date.snd, LocalDate.now().minusDays(1));
    }

    @Test
    void getValidTodayDate() {
        LocalDate date = dr.getValidTodayDate();
        assertEquals(date, LocalDate.now());
    }

    @Test
    void getValidYesterdayDate() {
        LocalDate date = dr.getValidYesterdayDate();
        assertEquals(date, LocalDate.now().minusDays(1));
    }

    static Stream<Pair<LocalDate, LocalDate>> setUpValidDate() {
        return Stream.of(dr.getValidYesterdayPeriod(), dr.getExpiredPeriod(), dr.getValidPeriod(), dr.getValidYesterdayPeriod(),
                dr.getExpiredPeriod(), dr.getValidPeriod(), dr.getValidYesterdayPeriod(), dr.getExpiredPeriod(), dr.getValidPeriod());
    }

    @ParameterizedTest
    @MethodSource("setUpValidDate")
    void getDateBetween(Pair<LocalDate, LocalDate> period) {
        LocalDate date = dr.getDateBetween(period);
        assertTrue(date.isAfter(period.fst) || date.isEqual(period.fst));
        assertTrue(date.isBefore(period.snd) || date.isEqual(period.snd));
    }


    @ParameterizedTest
    @MethodSource("setUpValidDate")
    void getNotValidDate(Pair<LocalDate, LocalDate> period) {
        LocalDate date = dr.getNotValidDate(period);
        if (date != null) {
            assertTrue(date.isAfter(period.fst) || date.isEqual(period.fst));
            assertTrue(date.isBefore(period.snd) || date.isEqual(period.snd));
        }
    }

    static Stream<LocalDateTime> setUpActivationDate() {
        return Stream.of(dr.getActivationDate(LocalDate.now()), dr.getActivationDate(dr.getValidYesterdayDate()),
                dr.getActivationDate(LocalDate.of(2017, 12, 12)), dr.getActivationDate(LocalDate.now().minusDays(13)),
                dr.getActivationDate(LocalDate.of(2025, 1, 30)), dr.getActivationDate(LocalDate.now().plusDays(18)),
                dr.getActivationDate(LocalDate.now().minusMonths(3)), dr.getActivationDate(LocalDate.now().plusYears(2)));
    }

    @ParameterizedTest
    @MethodSource("setUpActivationDate")
    void getActivationDate(LocalDateTime date) {
        assertTrue(date.getHour() >= 0);
        assertTrue(date.getHour() <= 23);
        assertTrue(date.getMinute() <= 59);
        assertTrue(date.getMinute() >= 0);
        assertTrue(date.getSecond() <= 59);
        assertTrue(date.getSecond() >= 0);
    }

    @RepeatedTest(10)
    void getName() {
        String name = dr.getName();
        assertTrue(name.length() > 1);
    }

    @RepeatedTest(10)
    void getSurname() {
        String name = dr.getSurname();
        assertTrue(name.length() > 1);
    }

    @RepeatedTest(10)
    void getPassportNumber() {
        String pass = dr.getPassportNumber();
        assertTrue(pass.length() > 7);
    }

    @RepeatedTest(10)
    void getValidityPassType() {
        String type = dr.getValidityPassType();
        assertTrue(type.length() > 0);
        assertNotEquals(" ", type);
        assertNotEquals("rgbereg", type);
        assertNotEquals("1st class senior", type);
        assertNotEquals("first class Senior", type);
    }

    @RepeatedTest(10)
    void getValidityKind() {
        String kind = dr.getValidityKind();
        assertTrue(kind.length() > 0);
        assertNotEquals(" ", kind);
        assertNotEquals("rgbereg", kind);
        assertNotEquals("global mobile pass", kind);
        assertNotEquals("Global mobile pass", kind);
    }
}