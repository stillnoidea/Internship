package randomizer;

import com.github.javafaker.Faker;
import enums.Kind;
import enums.PassType;
import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    public static Random generator = new java.util.Random();
    private Faker faker = new Faker(new Locale("pl"));


    private Pair<LocalDate, LocalDate> getRandomPeriod(Date startDate, Date endDate) {
        int interval = DataGenerator.generator.nextInt(90) + 1;
        Date date2 = faker.date().between(startDate, endDate);
        LocalDate start = new java.sql.Date(date2.getTime()).toLocalDate();
        LocalDate end = start.plusDays(interval);
        return new Pair<>(start, end);
    }

    public Pair<LocalDate, LocalDate> getExpiredPeriod() {
        return getRandomPeriod(new Date(117, 0, 1), new Date());
    }

    public Pair<LocalDate, LocalDate> getNotStartedPeriod() {
        if (isRandomDate()) {
            return getRandomPeriod(new Date(), new Date(125, 12, 30));
        } else return null;
    }

    public Pair<LocalDate, LocalDate> getPeriod() {
        if (isRandomDate()) {
            return getRandomPeriod(new Date(117, 0, 1), new Date(125, 12, 30));
        } else return null;
    }

    public Pair<LocalDate, LocalDate> getValidPeriod() {
        int x = DataGenerator.generator.nextInt(90) + 1;
        int y = x - DataGenerator.generator.nextInt(x);
        LocalDate start = (LocalDate.now().minusDays(y));
        LocalDate end = LocalDate.now().plusDays(x - y);
        return new Pair<>(start, end);
    }

    public Pair<LocalDate, LocalDate> getValidYesterdayPeriod() {
        int interval = DataGenerator.generator.nextInt(90) + 1;
        LocalDate end = getValidYesterdayDate();
        LocalDate start = LocalDate.now().minusDays((interval + 1));
        return new Pair<>(start, end);
    }

    public LocalDate getValidTodayDate() {
        return LocalDate.now();
    }

    public LocalDate getValidYesterdayDate() {
        return LocalDate.now().minusDays(1);
    }

    public LocalDate getDateBetween(Pair<LocalDate, LocalDate> dates) {
        Date fst = java.sql.Date.valueOf(dates.getKey());
        Date snd = java.sql.Date.valueOf(dates.getValue());
        if (fst.before(snd)) {
            return new java.sql.Date(faker.date().between(fst, snd).getTime()).toLocalDate();
        } else return new java.sql.Date(fst.getTime()).toLocalDate();

    }

    public LocalDate getNotValidDate(Pair<LocalDate, LocalDate> dates) {
        if (isRandomDate()) {
            return getDateBetween(dates);
        }
        return null;
    }

    private boolean isRandomDate() {
        return generator.nextBoolean();
    }

    public LocalDateTime getActivationDate(LocalDate validityDate) {
        return validityDate.atTime(generator.nextInt(24), generator.nextInt(60), generator.nextInt(60), generator.nextInt(999) * 1000000);

    }

    public String getName() {
        return faker.name().firstName();
    }

    public String getSurname() {
        return faker.name().lastName();
    }

    public String getValidityPassType() {
        PassType[] list = PassType.values();
        return list[generator.nextInt(list.length)].getType();
    }

    public String getValidityKind() {
        Kind[] list = Kind.values();
        return list[generator.nextInt(list.length)].getKind();
    }

    public String getPassportNumber() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
