package seedu.tr4cker.ui.planner;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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

    @FXML
    private VBox vbox;

    /**
     * Creates a {@code PlannerCalendarPanel} with the given {@code PlannerDay} to display.
     */
    public PlannerCalendarPanel(PlannerDay plannerDay) {
        super(FXML);
        logger.fine("Initialising Planner Calendar panel...");
        this.plannerDay = plannerDay;
        this.calendarMonthYear.setText(plannerDay.getMonthName() + " " + plannerDay.getYear());
        this.calendarMonthYear.setId("month-year-label");
        vbox.getStyleClass().add("vbox");
        calendarTable.getStyleClass().add("calendarGrid");

        PlannerDay startDay = plannerDay.createFirstDayOfMonth();
        fillCalendarTable(startDay, null);
        logger.fine("Created Planner Calendar panel.");
    }

    /**
     * Fills up the calendar with dates of the current month.
     *
     * @param startDay First day of the month.
     * @param localDate User's specified date (can be null or not).
     */
    public void fillCalendarTable(PlannerDay startDay, LocalDate localDate) {
        int index = startDay.getDayOfWeek();
        PlannerDay currDay = startDay;
        if (index != 1) {
            for (int i = index - 1; i > 0; i--) {
                currDay = currDay.getPrevDay();
            }
        }
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
                highlightDay(startDay, currDay, localDate, plannerDayCard);
                currDay = currDay.getNextDay();
            }
        }
        logger.fine("Filled calendar view with start date: " + startDay.toString());
    }

    /**
     * Checks the days and set the colour and highlight dates.
     *
     * @param startDay Start day.
     * @param currDay Current day.
     * @param localDate User's specified date (can be null or not).
     * @param plannerDayCard Planner Day Card.
     */
    private void highlightDay(PlannerDay startDay, PlannerDay currDay,
                              LocalDate localDate, PlannerDayCard plannerDayCard) {
        if (localDate != null) {
            if (currDay.getDay() == localDate.getDayOfMonth()
                    && currDay.getMonth() == localDate.getMonthValue()) {
                plannerDayCard.setToday();
            }
        } else {
            if (currDay.getDay() == startDay.getDay()
                    && currDay.getMonth() == startDay.getMonth()) {
                plannerDayCard.setToday();
            }
        }

        if (currDay.getMonth() == startDay.getMonth()) {
            plannerDayCard.setSameMonthColour();
        } else {
            plannerDayCard.setDifferentMonthColour();
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

    /**
     * Updates indicator of all Planner Day Cards.
     */
    public void updateIndicator() {
        for (PlannerDayCard plannerDayCard : plannerDayCards) {
            plannerDayCard.updateIndicator();
        }
    }

}
