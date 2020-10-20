package seedu.tr4cker.ui.planner;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
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
    private Label dateNumber;

    /**
     * Creates a {@code PlannerDayCard} with the given {@code PlannerDay} to display.
     */
    public PlannerDayCard(PlannerDay plannerDay) {
        super(FXML);
        logger.fine("Initialising plannerDayCard...");
        this.plannerDay = plannerDay;
        dateNumber.setText(Integer.toString(plannerDay.getDay()));
    }

    /**
     * Clears the date number of a PlannerDayCard.
     */
    public void clear() {
        dateNumber.setText("");
    }

}
