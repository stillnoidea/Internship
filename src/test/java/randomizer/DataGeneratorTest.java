package randomizer;

import com.sun.tools.javac.util.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DataGeneratorTest {


    static Stream<Pair<LocalDate, LocalDate>> setUpDatesExpired() {
        DataGenerator dr = new DataGenerator();
        return Stream.of(dr.getExpiredPeriod(), dr.getExpiredPeriod(), dr.getExpiredPeriod(), dr.getExpiredPeriod(),
                dr.getExpiredPeriod(), dr.getExpiredPeriod(), dr.getExpiredPeriod(), dr.getExpiredPeriod());
    }

    @ParameterizedTest
    @MethodSource("setUpDatesExpired")
    void getExpiredPeriod(Pair<LocalDate, LocalDate> date) {
        assertTrue(date.fst.isBefore(date.snd));
        assertTrue(date.snd.isBefore(LocalDate.now()));
    }

    static Stream<Pair<LocalDate, LocalDate>> setUpDatesNotStarted() {
        DataGenerator dr = new DataGenerator();
        return Stream.of(dr.getNotStartedPeriod(), dr.getNotStartedPeriod(), dr.getNotStartedPeriod(), dr.getNotStartedPeriod(),
                dr.getNotStartedPeriod(), dr.getNotStartedPeriod(), dr.getNotStartedPeriod(), dr.getNotStartedPeriod());
    }

    @ParameterizedTest
    @MethodSource("setUpDatesNotStarted")
    void getNotStartedPeriod(Pair<LocalDate, LocalDate> date) {
        boolean isNull = (date == null);
        if (!isNull) {
            assertTrue(date.fst.isBefore(date.snd));
            assertTrue(date.fst.isAfter(LocalDate.now()));
        }
    }

    static Stream<Pair<LocalDate, LocalDate>> setUpDates() {
        DataGenerator dr = new DataGenerator();
        return Stream.of(dr.getPeriod(), dr.getPeriod(), dr.getPeriod(), dr.getPeriod(),
                dr.getPeriod(), dr.getPeriod(), dr.getPeriod(), dr.getPeriod());
    }

    @ParameterizedTest
    @MethodSource("setUpDates")
    void getPeriod(Pair<LocalDate, LocalDate> date) {
        boolean isNull = (date == null);
        if (!isNull) {
            assertTrue(date.fst.isBefore(date.snd));
        }
    }

    static Stream<Pair<LocalDate, LocalDate>> setUpDatesValid() {
        DataGenerator dr = new DataGenerator();
        return Stream.of(dr.getValidPeriod(), dr.getValidPeriod(), dr.getValidPeriod(), dr.getValidPeriod(),
                dr.getValidPeriod(), dr.getValidPeriod(), dr.getValidPeriod(), dr.getValidPeriod());
    }

    @ParameterizedTest
    @MethodSource("setUpDatesValid")
    void getValidPeriod(Pair<LocalDate, LocalDate> date) {
        assertTrue(date.fst.isBefore(date.snd));
        assertTrue(date.fst.isBefore(LocalDate.now()));
        assertTrue(date.snd.isAfter(LocalDate.now()));
    }

    static Stream<Pair<LocalDate, LocalDate>> setUpDatesValidYesterday() {
        DataGenerator dr = new DataGenerator();
        return Stream.of(dr.getValidYesterdayPeriod(), dr.getValidYesterdayPeriod(), dr.getValidYesterdayPeriod(), dr.getValidYesterdayPeriod(),
                dr.getValidYesterdayPeriod(), dr.getValidYesterdayPeriod(), dr.getValidYesterdayPeriod(), dr.getValidYesterdayPeriod());
    }

    @ParameterizedTest
    @MethodSource("setUpDatesValidYesterday")
    void getValidYesterdayPeriod(Pair<LocalDate, LocalDate> date) {
        assertTrue(date.fst.isBefore(date.snd));
        assertTrue(date.fst.isBefore(LocalDate.now()));
        assertEquals(date.snd, LocalDate.now().minusDays(1));
    }

    @Test
    void getValidTodayDate() {
        DataGenerator dr = new DataGenerator();
        LocalDate date = dr.getValidTodayDate();
        assertEquals(date, LocalDate.now());
    }

    @Test
    void getValidYesterdayDate() {
        DataGenerator dr = new DataGenerator();
        LocalDate date = dr.getValidYesterdayDate();
        assertEquals(date, LocalDate.now().minusDays(1));
    }

    @Test
    void getDateBetween() {
        DataGenerator dr = new DataGenerator();
        Pair<LocalDate, LocalDate> date4 = dr.getValidYesterdayPeriod();
        System.out.println(date4.fst);
        System.out.println(date4.snd);
        LocalDate date = dr.getDateBetween(date4);
        System.out.println(date);
        Pair<LocalDate, LocalDate> date5 = dr.getExpiredPeriod();
        System.out.println(date5.fst);
        System.out.println(date5.snd);
        LocalDate date1 = dr.getDateBetween(date5);
        System.out.println(date1);
        Pair<LocalDate, LocalDate> date6 = dr.getValidPeriod();
        System.out.println(date6.fst);
        System.out.println(date6.snd);
        LocalDate date2 = dr.getDateBetween(date6);
        System.out.println(date2);
    }

    @Test
    void getNotValidDate() {
    }

    @Test
    void getActivationDate() {
        DataGenerator dr = new DataGenerator();
        System.out.println(dr.getActivationDate(LocalDate.now()).toString());
        System.out.println(dr.getActivationDate(LocalDate.now()).toString());
        System.out.println(dr.getActivationDate(LocalDate.now()).toString());
        System.out.println(dr.getActivationDate(LocalDate.now()).toString());
        System.out.println(dr.getActivationDate(LocalDate.now()).toString());
        System.out.println(dr.getActivationDate(LocalDate.now()).toString());
    }

    static Stream<String> setUpNames() {
        DataGenerator dr = new DataGenerator();
        return Stream.of(dr.getName(), dr.getName(), dr.getName(), dr.getName(),
                dr.getName(), dr.getName(), dr.getName(), dr.getName());
    }

    @ParameterizedTest
    @MethodSource("setUpNames")
    void getName(String name) {
        assertTrue(name.length() > 1);
    }

    static Stream<String> setUpSurnames() {
        DataGenerator dr = new DataGenerator();
        return Stream.of(dr.getSurname(), dr.getSurname(), dr.getSurname(), dr.getSurname(),
                dr.getSurname(), dr.getSurname(), dr.getSurname(), dr.getSurname());
    }

    @ParameterizedTest
    @MethodSource("setUpSurnames")
    void getSurname(String name) {
        assertTrue(name.length() > 1);
    }

    static Stream<String> setUpPasses() {
        DataGenerator dr = new DataGenerator();
        return Stream.of(dr.getPassportNumber(), dr.getPassportNumber(), dr.getPassportNumber(), dr.getPassportNumber(),
                dr.getPassportNumber(), dr.getPassportNumber(), dr.getPassportNumber(), dr.getPassportNumber());
    }

    @ParameterizedTest
    @MethodSource("setUpPasses")
    void getPassportNumber(String pass) {
        assertTrue(pass.length() > 7);
    }

    @Test
    void getValidityPassType() {
    }

    @Test
    void getValidityKind() {
    }
}