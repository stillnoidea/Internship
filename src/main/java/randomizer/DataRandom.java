package randomizer;


import com.github.javafaker.Faker;
import com.sun.tools.javac.util.Pair;
import enums.Kind;
import enums.PassType;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class DataRandom {
    public static Random generator = new java.util.Random();
    private Faker faker = new Faker(new Locale("pl"));

    Pair<Date, Date> getValidToday() {
        int x = DataRandom.generator.nextInt(90);
        int y = x - DataRandom.generator.nextInt(x);
        Date start = java.sql.Date.valueOf(LocalDate.now().minusDays(y));
        Date end = java.sql.Date.valueOf(LocalDate.now().plusDays(x - y));
        return new Pair<>(start, end);
    }

    Pair<Date, Date> getNotValid() {
        int interval = DataRandom.generator.nextInt(90);
        Date start = faker.date().between(new Date(177, 0, 1), new Date(125, 12, 30));
        LocalDate date1 = new java.sql.Date(start.getTime()).toLocalDate();
        Date end = java.sql.Date.valueOf(date1.plusDays(interval));
        return new Pair<>(start, end);
    }

    Pair<Date, Date> getValidYesterday() {
        int interval = DataRandom.generator.nextInt(90);
        LocalDate yesterday = LocalDate.now().minusDays(1);
        Date end = java.sql.Date.valueOf(yesterday);
        Date start = java.sql.Date.valueOf(yesterday.minusDays(interval));
        return new Pair<>(start, end);
    }

    Pair<Date, Date> getNotStarted() {
        int interval = DataRandom.generator.nextInt(90);
        Date start = faker.date().between(new Date(), new Date(125, 12, 30));
        LocalDate startLocalDate = new java.sql.Date(start.getTime()).toLocalDate();
        Date end = java.sql.Date.valueOf(startLocalDate.plusDays(interval));
        return new Pair<>(start, end);
    }

    public String getName() {
        return faker.name().firstName();
    }

    public String getSurname() {
        return faker.name().lastName();
    }

    public PassType getValidityPassType() {
        PassType[] list = PassType.values();
        return list[generator.nextInt(list.length)];
    }

    public Kind getValidityKind() {
        Kind[] list = Kind.values();
        return list[generator.nextInt(list.length)];
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
