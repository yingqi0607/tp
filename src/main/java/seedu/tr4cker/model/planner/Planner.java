package seedu.tr4cker.model.planner;

import java.time.LocalDateTime;

/**
 * Represents the calendar in Planner tab.
 */
public class Planner {

    private final LocalDateTime localDateTime;
    private final String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    public Planner(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public static Planner getCurrentDateTime() {
        return new Planner(LocalDateTime.now());
    }

    public int getDay() {
        return localDateTime.getDayOfMonth();
    }

    public int getMonth() {
        return localDateTime.getMonthValue();
    }

    public int getYear() {
        return localDateTime.getYear();
    }

    public String getMonthName() {
        return localDateTime.getMonth().name();
    }

}
