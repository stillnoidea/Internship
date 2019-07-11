package randomizer;


import com.sun.tools.javac.util.Pair;
import enums.Kind;
import enums.PassType;

import java.time.LocalDate;
import java.util.Random;

public class DataRandom {
    public static Random generator = new java.util.Random();
    private RandomDate randomDate = new RandomDate();

    public static String getName() {
        return NameGenerator.generateName();
    }

    public Pair<LocalDate, LocalDate> getNotValidPeriod(int interval) {
        randomDate.setDaysInterval(interval);
        randomDate.setMaxDate(LocalDate.now().minusDays(3));
        LocalDate end = randomDate.nextDateByDates();
        randomDate.setIntervalDate(end);
        randomDate.setFurtherDate(false);
        return new Pair<>(randomDate.nextDateByInterval(), end);
    }

    public Pair<LocalDate, LocalDate> getValidPeriod(int interval) {
        int daysToEnd = generator.nextInt(interval);
        LocalDate today = LocalDate.now();
        return new Pair<>(today.minusDays(interval - daysToEnd), today.plusDays(daysToEnd));
    }

    public Pair<LocalDate, LocalDate> getNotStartedPeriod(int interval) {
        randomDate.setDaysInterval(interval);
        randomDate.setMinDate(LocalDate.now().plusDays(1));
        LocalDate start = randomDate.nextDateByDates();
        randomDate.setIntervalDate(start);
        randomDate.setFurtherDate(true);
        return new Pair<>(randomDate.nextDateByInterval(), start);
    }

    public Pair<LocalDate, LocalDate> getValidYesterdayPeriod(int interval) {
        randomDate.setDaysInterval(interval);
        randomDate.setIntervalDate(LocalDate.now().minusDays(2));
        LocalDate start = randomDate.nextDateByInterval();
        return new Pair<>(start, LocalDate.now().minusDays(1));
    }

    public String generateName() {
        return NameGenerator.generateName();
    }

    public PassType getValidityPassType() {
        PassType[] list = PassType.values();
        return list[generator.nextInt(list.length)];
    }

    public Kind getValidityKind() {
        Kind[] list = Kind.values();
        return list[generator.nextInt(list.length)];
    }
}

class NameGenerator {

    private static String[] name = {"Virgina", "Stan", "Nina", "Gene", "Reagan", "Noble", "Georgina", "Shanell", "Arlena",
            "Alanna", "Bailey", "Ebony", "Kristian", "Anette", "Zachary", "Patience", "Sebastian", "Annmarie",
            "Arie", "Hui", "Regan", "Flora", "Cassaundra", "Emil", "Dayna", "Pinkie", "Gabriela", "Gilberto", "Laurena",
            "Dorotea", "Marta", "Charmaine", "Venita", "Giovanna", "Dennis", "Nathanael", "Barney", "Spring", "Vern",
            "Gertrude", "Lanita", "Jodi", "Jessica", "Odessa", "Ewa", "Royal", "Lai", "Tatiana", "Malwina", "Wanda"};
    private static String[] surname = {"Farley", "Sparks", "Foster", "Eaton", "Mcguire", "Farmer", "Matthews", "Weiss",
            "Short", "Mercado", "Hicks", "Baxter", "Gates", "Blackwell", "Jones", "Walton", "Woodward", "Mckenzie", "Wagner",
            "Silva", "Francis", "Galloway", "Kent", "Kim", "Barrera", "Stark", "Atkins", "Mooney", "Steele", "Perkins",
            "Myers", "Craig", "Fuentes", "Burton", "Harvey", "Pierce", "Nowak", "Decker", "Colon", "Davies", "Sosa",
            "House", "Church", "Mcknight", "Bradshaw", "Burns", "Hatfield", "Parrish", "Tanner"};

    static String generateName() {
        return name[DataRandom.generator.nextInt(name.length)] + " " + surname[DataRandom.generator.nextInt(surname.length)];
    }
}

class RandomDate {
    private LocalDate minDate = LocalDate.of(2017, 1, 1);
    private LocalDate maxDate = LocalDate.of(2025, 12, 30);
    private LocalDate intervalDate = LocalDate.now();
    private int daysInterval = 7;


    private boolean isFurtherDate = true;

    RandomDate() {
    }

    LocalDate nextDateByDates() {
        int minDay = (int) minDate.toEpochDay();
        int maxDay = (int) maxDate.toEpochDay();
        long randomDay = minDay + DataRandom.generator.nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    LocalDate nextDateByInterval() {
        int minDay = (int) intervalDate.toEpochDay();
        long randomDay;
        if (isFurtherDate) {
            randomDay = minDay + DataRandom.generator.nextInt(daysInterval);
        } else randomDay = minDay - DataRandom.generator.nextInt(daysInterval);
        return LocalDate.ofEpochDay(randomDay);
    }

    @Override
    public String toString() {
        return "RandomDate{" + "maxDate=" + maxDate + ", minDate=" + minDate + " intervalDate" + intervalDate +
                "interval " + daysInterval + "is further interval " + isFurtherDate + '}';
    }

    void setDaysInterval(int daysInterval) {
        this.daysInterval = daysInterval;
    }

    void setMinDate(LocalDate minDate) {
        this.minDate = minDate;
    }

    void setMaxDate(LocalDate maxDate) {
        this.maxDate = maxDate;
    }

    void setIntervalDate(LocalDate intervalDate) {
        this.intervalDate = intervalDate;
    }

    void setFurtherDate(boolean furtherDate) {
        isFurtherDate = furtherDate;
    }
}