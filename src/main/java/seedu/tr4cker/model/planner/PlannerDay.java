package seedu.tr4cker.model.planner;

import java.time.LocalDate;

/**
 * Represents the calendar in PlannerDay tab.
 */
public class PlannerDay {

    private final LocalDate localDate;

    public PlannerDay(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getDay() {
        return localDate.getDayOfMonth();
    }

    public int getDayOfWeek() {
        return localDate.getDayOfWeek().getValue();
    }

    public String getMonthName() {
        return localDate.getMonth().name();
    }

    public int getYear() {
        return localDate.getYear();
    }

    public int getLengthOfCurrMonth() {
        return this.localDate.lengthOfMonth();
    }

    public int getLengthOfPrevMonth() {
        return this.localDate.minusMonths(1).lengthOfMonth();
    }

    public int getLengthOfNextMonth() {
        return this.localDate.plusMonths(1).lengthOfMonth();
    }

    public PlannerDay createFirstDayOfMonth() {
        LocalDate firstDay = this.localDate.withDayOfMonth(1);
        return new PlannerDay(firstDay);
    }

    public PlannerDay createPrevMonthWithDate(int date) {
        LocalDate prevMonth = this.localDate.minusMonths(1);
        LocalDate prevMonthDate = prevMonth.withDayOfMonth(date);
        return new PlannerDay(prevMonthDate);
    }

    public PlannerDay createNextMonthWithDate(int date) {
        LocalDate nextMonth = this.localDate.plusMonths(1);
        LocalDate nextMonthDate = nextMonth.withDayOfMonth(date);
        return new PlannerDay(nextMonthDate);
    }

    public static PlannerDay getCurrDay() {
        return new PlannerDay(LocalDate.now());
    }

    public PlannerDay getPrevDay() {
        LocalDate prevDay = this.localDate.minusDays(1);
        return new PlannerDay(prevDay);
    }

    public PlannerDay getNextDay() {
        LocalDate nextDay = this.localDate.plusDays(1);
        return new PlannerDay(nextDay);
    }

}
