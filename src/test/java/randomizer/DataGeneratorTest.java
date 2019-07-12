package randomizer;

import com.sun.tools.javac.util.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;


class DataGeneratorTest {


    static Stream<Pair<LocalDate, LocalDate>> setUp() {
        DataGenerator dr = new DataGenerator();
        return Stream.of(dr.getExpiredPeriod(), dr.getExpiredPeriod(), dr.getExpiredPeriod(), dr.getExpiredPeriod(),
                dr.getExpiredPeriod(), dr.getExpiredPeriod(), dr.getExpiredPeriod(), dr.getExpiredPeriod());
    }

    @ParameterizedTest
    @MethodSource("setUp")
    void getExpiredPeriod(Pair<LocalDate, LocalDate> date) {
        assertTrue(date.fst.isBefore(date.snd));
        assertTrue(date.snd.isBefore(LocalDate.now()));
    }

    private void assertTrue(boolean before) {
    }

    @Test
    void getNotStartedPeriod() {
        DataGenerator dr = new DataGenerator();
        Pair<LocalDate, LocalDate> date = dr.getNotStartedPeriod();
        System.out.println(date.fst);
        System.out.println(date.snd);
        Pair<LocalDate, LocalDate> date1 = dr.getNotStartedPeriod();
        System.out.println(date1.fst);
        System.out.println(date1.snd);
        Pair<LocalDate, LocalDate> date2 = dr.getNotStartedPeriod();
        System.out.println(date2.fst);
        System.out.println(date2.snd);
        Pair<LocalDate, LocalDate> date3 = dr.getNotStartedPeriod();
        System.out.println(date3.fst);
        System.out.println(date3.snd);
        Pair<LocalDate, LocalDate> date4 = dr.getNotStartedPeriod();
        System.out.println(date4.fst);
        System.out.println(date4.snd);
    }

    @Test
    void getPeriod() {
        DataGenerator dr = new DataGenerator();
        Pair<LocalDate, LocalDate> date = dr.getPeriod();
        System.out.println(date.fst);
        System.out.println(date.snd);
        Pair<LocalDate, LocalDate> date1 = dr.getPeriod();
        System.out.println(date1.fst);
        System.out.println(date1.snd);
        Pair<LocalDate, LocalDate> date2 = dr.getPeriod();
        System.out.println(date2.fst);
        System.out.println(date2.snd);
        Pair<LocalDate, LocalDate> date3 = dr.getPeriod();
        System.out.println(date3.fst);
        System.out.println(date3.snd);
        Pair<LocalDate, LocalDate> date4 = dr.getPeriod();
        System.out.println(date4.fst);
        System.out.println(date4.snd);
    }

    @Test
    void getValidPeriod() {
        DataGenerator dr = new DataGenerator();
        Pair<LocalDate, LocalDate> date = dr.getValidPeriod();
        System.out.println(date.fst);
        System.out.println(date.snd);
        Pair<LocalDate, LocalDate> date1 = dr.getValidPeriod();
        System.out.println(date1.fst);
        System.out.println(date1.snd);
        Pair<LocalDate, LocalDate> date2 = dr.getValidPeriod();
        System.out.println(date2.fst);
        System.out.println(date2.snd);
        Pair<LocalDate, LocalDate> date3 = dr.getValidPeriod();
        System.out.println(date3.fst);
        System.out.println(date3.snd);
        Pair<LocalDate, LocalDate> date4 = dr.getValidPeriod();
        System.out.println(date4.fst);
        System.out.println(date4.snd);
    }

    @Test
    void getValidYesterdayPeriod() {
        DataGenerator dr = new DataGenerator();
        Pair<LocalDate, LocalDate> date = dr.getValidYesterdayPeriod();
        System.out.println(date.fst);
        System.out.println(date.snd);
        Pair<LocalDate, LocalDate> date1 = dr.getValidYesterdayPeriod();
        System.out.println(date1.fst);
        System.out.println(date1.snd);
        Pair<LocalDate, LocalDate> date2 = dr.getValidYesterdayPeriod();
        System.out.println(date2.fst);
        System.out.println(date2.snd);
        Pair<LocalDate, LocalDate> date3 = dr.getValidYesterdayPeriod();
        System.out.println(date3.fst);
        System.out.println(date3.snd);
        Pair<LocalDate, LocalDate> date4 = dr.getValidYesterdayPeriod();
        System.out.println(date4.fst);
        System.out.println(date4.snd);
    }

    @Test
    void getValidTodayDate() {
        DataGenerator dr = new DataGenerator();
        LocalDate date = dr.getValidTodayDate();
        System.out.println(date);
    }

    @Test
    void getValidYesterdayDate() {
        DataGenerator dr = new DataGenerator();
        LocalDate date = dr.getValidYesterdayDate();
        System.out.println(date);
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

    @Test
    void getName() {
    }

    @Test
    void getSurname() {
    }

    @Test
    void getValidityPassType() {
    }

    @Test
    void getValidityKind() {
    }
}