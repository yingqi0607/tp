package seedu.tr4cker.ui.planner;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.model.planner.PlannerDay;
import seedu.tr4cker.ui.UiPart;

/**
 * An UI component that displays information of PlannerCalendarPanel.
 * Displays the calendar.
 */
public class PlannerCalendarPanel extends UiPart<Region> {

    private static final String FXML = "PlannerCalendarPanel.fxml";
    private static int count;
    private final Logger logger = LogsCenter.getLogger(PlannerCalendarPanel.class);

    private final PlannerDay plannerDay;
    private final ArrayList<PlannerDayCard> plannerDayCards = new ArrayList<>();
    private int month;
    private int year;

    @FXML
    private GridPane calendarTable;

    @FXML
    private Label calendarMonthYear;

    /**
     * Creates a {@code PlannerCalendarPanel} with the given {@code PlannerDay} to display.
     */
    public PlannerCalendarPanel(PlannerDay plannerDay) {
        super(FXML);
        logger.fine("Initialising plannerCalendar panel...");
        this.plannerDay = plannerDay;
        this.calendarMonthYear.setText(plannerDay.getMonthName() + " " + plannerDay.getYear());
        this.calendarMonthYear.setId("month-year-label");

        PlannerDay startDay = plannerDay.createFirstDayOfMonth();
        fillCalendarTable(startDay);
    }

    /**
     * Fills up the calendar with dates of the current month.
     *
     * @param startDay First day of the month.
     */
    public void fillCalendarTable(PlannerDay startDay) {
        int index = startDay.getDayOfWeek();
        PlannerDay currDay = startDay;
        if (index != 1) {
            for (int i = index - 1; i > 0; i--) {
                currDay = currDay.getPrevDay();
            }
        }
        System.out.println(currDay.getMonth());
        if (count == 0) {
            setCurrentMonth(currDay.getMonth());
            setCurrentYear(currDay.getYear());
        }
        count++;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                PlannerDayCard plannerDayCard = new PlannerDayCard(currDay);
                plannerDayCards.add(plannerDayCard);
                calendarTable.add(plannerDayCard.getRoot(), col, row);
                if (currDay.getMonth() == startDay.getMonth()) {
                    plannerDayCard.setSameMonthColour();
                } else {
                    plannerDayCard.setDifferentMonthColour();
                }
                currDay = currDay.getNextDay();
            }
        }
    }

    /**
     * Sets the current month of the calendar.
     */
    public void setCurrentMonth(int month) {
        this.month = month;
    }

    /**
     * Sets the current year of the calendar.
     */
    public void setCurrentYear(int year) {
        this.year = year;
    }

    /**
     * Gets the current month of the calendar.
     *
     * @return Current month.
     */
    public int getCurrentMonth() {
        return month;
    }

    /**
     * Gets the current year of the calendar.
     *
     * @return Current year.
     */
    public int getCurrentYear() {
        return year;
    }

    /**
     * Clears the calendar.
     */
    public void clearCalendar() {
        this.calendarMonthYear.setText("");
        for (PlannerDayCard plannerDayCard : plannerDayCards) {
            plannerDayCard.clear();
        }
    }

    /**
     * Changes the month and year of calendar.
     *
     * @param yearMonth Year month user specified.
     */
    public void changeCalendarMonthYear(YearMonth yearMonth) {
        String label = yearMonth.getMonth().name() + " " + yearMonth.getYear();
        this.calendarMonthYear.setText(label);
        this.calendarMonthYear.setId("month-year-label");
    }

}
