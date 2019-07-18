package tickets;

import com.sun.tools.javac.util.Pair;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class ValidityPeriod {

    private LocalDate startDate;
    private LocalDate endDate;

    public ValidityPeriod() {
        startDate = null;
        endDate = null;
    }

    ValidityPeriod(Pair<LocalDate, LocalDate> dates) {
        startDate = dates.fst;
        endDate = dates.snd;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Pair<LocalDate, LocalDate> obtainPeriod() {
        return new Pair<>(startDate, endDate);
    }

}