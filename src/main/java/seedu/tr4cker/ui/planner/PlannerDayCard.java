package seedu.tr4cker.ui.planner;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.model.planner.PlannerDay;
import seedu.tr4cker.ui.UiPart;

/**
 * An UI component that displays information of PlannerDay.
 * Displays details of a day in the calendar.
 */
public class PlannerDayCard extends UiPart<Region> {

    private static final String FXML = "PlannerDayCard.fxml";
    private final Logger logger = LogsCenter.getLogger(PlannerDayCard.class);

    private final PlannerDay plannerDay;

    @FXML
    private Circle circle;

    @FXML
    private Label dateNumber;

    /**
     * Creates a {@code PlannerDayCard} with the given {@code PlannerDay} to display.
     */
    public PlannerDayCard(PlannerDay plannerDay) {
        super(FXML);
        logger.fine("Initialising plannerDayCard...");
        this.plannerDay = plannerDay;
        dateNumber.setText(Integer.toString(plannerDay.getDay()));
        circle.setId("circle-not-today");
    }

    /**
     * Clears the date number and highlight of a PlannerDayCard.
     */
    public void clear() {
        dateNumber.setText("");
        circle.setId("circle-not-today");
    }

    /**
     * Sets the date of the same month to be black colour.
     */
    public void setSameMonthColour() {
        dateNumber.setId("same-month-colour");
    }

    /**
     * Sets the date of the same month to be grey colour.
     */
    public void setDifferentMonthColour() {
        dateNumber.setId("different-month-colour");
    }

    /**
     * Highlights the date.
     */
    public void setToday() {
        circle.setId("circle-today");
    }

}
