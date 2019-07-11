package randomizer;

import jdk.internal.vm.compiler.collections.Pair;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class DataRandom {
    public static Random generator = new java.util.Random();

//    public static String getName(){
////        NameGenerator generator = new NameGenerator();
//    }

//    public Pair<Date, Date> getNotValidPeriod() {
//        RandomDate rd = new RandomDate(LocalDate.of(2018, 1, 1), LocalDate.now());
//    }
//
//    public Pair<Date, Date> getValidPeriod() {
//        RandomDate rd = new RandomDate(LocalDate.of(2018, 1, 1), LocalDate.now().minusDays(1));
//        RandomDate rd1 = new RandomDate(LocalDate.now().plusDays(1), LocalDate.of(2020, 12, 30));
//    }
//
//    public Pair<Date, Date> getNotStartedPeriod() {
//        RandomDate rd = new RandomDate(LocalDate.now().plusDays(1), LocalDate.of(2020, 12, 30));
//    }
//
//    public Pair<Date, Date> getValidYesterdayPeriod() {
//        RandomDate rd = new RandomDate(LocalDate.of(2018, 1, 1), LocalDate.now().minusDays(1));
//    }
//
//    public String generateName() {
//        return NameGenerator.generateName();
//    }
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
    private LocalDate minDate;
    private LocalDate maxDate;
    private LocalDate intervalDate;
    private int daysInterval;
    private boolean isFurtherDate;

    RandomDate(LocalDate minDate, LocalDate maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    RandomDate(LocalDate date, int interval, boolean isFurtherDate) {
        this.intervalDate = date;
        this.isFurtherDate = isFurtherDate;
        this.daysInterval = interval;
    }

    LocalDate nextDateByDates() {
        int minDay = (int) minDate.toEpochDay();
        int maxDay = (int) maxDate.toEpochDay();
        long randomDay = minDay + DataRandom.generator.nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    LocalDate nextDateByInterval() {
        int minDay = (int) intervalDate.toEpochDay();
        long randomDay = 0;
        if (isFurtherDate) {
            randomDay = minDay + DataRandom.generator.nextInt(daysInterval);
        } else randomDay = minDay - DataRandom.generator.nextInt(daysInterval);
        return LocalDate.ofEpochDay(randomDay);
    }

    @Override
    public String toString() {
        return "RandomDate{" +
                "maxDate=" + maxDate +
                ", minDate=" + minDate +
                '}';
    }

    void changeIntervalTurn() {
        isFurtherDate = !isFurtherDate;
    }

    public int getDaysInterval() {
        return daysInterval;
    }

    public void setDaysInterval(int daysInterval) {
        this.daysInterval = daysInterval;
    }

    public LocalDate getMinDate() {
        return minDate;
    }

    public void setMinDate(LocalDate minDate) {
        this.minDate = minDate;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(LocalDate maxDate) {
        this.maxDate = maxDate;
    }
}