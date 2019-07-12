package tickets;

import com.sun.tools.javac.util.Pair;

import java.time.LocalDate;

class ValidityPeriod {
    private LocalDate startDate;
    private LocalDate endDate;

    ValidityPeriod(Pair<LocalDate, LocalDate> dates) {
        startDate = dates.fst;
        endDate = dates.snd;
    }

    public Pair<LocalDate, LocalDate> getPeriod() {
        return new Pair<>(startDate, endDate);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

}