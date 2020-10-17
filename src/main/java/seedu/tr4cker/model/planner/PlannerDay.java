package seedu.tr4cker.model.planner;

import java.time.LocalDate;

/**
 * Represents the calendar in Planner tab.
 */
public class PlannerDay {

    /** Date of the PlannerDay. */
    private final LocalDate localDate;

    /**
     * Constructor for PlannerDay.
     *
     * @param localDate Date in the calendar.
     */
    public PlannerDay(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Returns the date.
     *
     * @return Date of PlannerDay.
     */
    public int getDay() {
        return localDate.getDayOfMonth();
    }

    /** Returns the day of the date represented by 1 to 7 (1 for Monday, 7 for Sunday).
     *
     * @return 1 to 7 depending on the day.
     */
    public int getDayOfWeek() {
        return localDate.getDayOfWeek().getValue();
    }

    /**
     * Returns the month in string representation.
     *
     * @return String representation of month of date.
     */
    public String getMonthName() {
        return localDate.getMonth().name();
    }

    /** Returns the year.
     *
     * @return Year of PlannerDay.
     */
    public int getYear() {
        return localDate.getYear();
    }

    //public int getLengthOfCurrMonth() {
    //    return this.localDate.lengthOfMonth();
    //}
    //
    //public int getLengthOfPrevMonth() {
    //    return this.localDate.minusMonths(1).lengthOfMonth();
    //}
    //
    //public int getLengthOfNextMonth() {
    //    return this.localDate.plusMonths(1).lengthOfMonth();
    //}

    /**
     * Creates the first day of the month.
     *
     * @return A new PlannerDay with first day of month.
     */
    public PlannerDay createFirstDayOfMonth() {
        LocalDate firstDay = this.localDate.withDayOfMonth(1);
        return new PlannerDay(firstDay);
    }

    //public PlannerDay createPrevMonthWithDate(int date) {
    //    LocalDate prevMonth = this.localDate.minusMonths(1);
    //    LocalDate prevMonthDate = prevMonth.withDayOfMonth(date);
    //    return new PlannerDay(prevMonthDate);
    //}
    //
    //public PlannerDay createNextMonthWithDate(int date) {
    //    LocalDate nextMonth = this.localDate.plusMonths(1);
    //    LocalDate nextMonthDate = nextMonth.withDayOfMonth(date);
    //    return new PlannerDay(nextMonthDate);
    //}

    /**
     * Returns the current date.
     *
     * @return Current date.
     */
    public static PlannerDay getCurrDay() {
        return new PlannerDay(LocalDate.now());
    }

    /**
     * Returns the previous date.
     *
     * @return Previous date.
     */
    public PlannerDay getPrevDay() {
        LocalDate prevDay = this.localDate.minusDays(1);
        return new PlannerDay(prevDay);
    }

    /**
     * Returns the next date.
     *
     * @return Next date.
     */
    public PlannerDay getNextDay() {
        LocalDate nextDay = this.localDate.plusDays(1);
        return new PlannerDay(nextDay);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PlannerDay)) {
            return false;
        }

        PlannerDay plannerDay = (PlannerDay) other;
        return this.getDay() == plannerDay.getDay()
                && this.getMonthName().equals(plannerDay.getMonthName())
                && this.getYear() == plannerDay.getYear();
    }

}
