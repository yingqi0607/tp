package seedu.tr4cker.ui.planner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.model.planner.PlannerDay;
import seedu.tr4cker.ui.UiPart;

import java.util.logging.Logger;

public class PlannerDayCard extends UiPart<Region>  {

    private static final String FXML = "PlannerDayCard.fxml";
    private final Logger logger = LogsCenter.getLogger(PlannerDayCard.class);

    private PlannerDay plannerDay;

    @FXML
    private Label dateNumber;

    public PlannerDayCard(PlannerDay plannerDay) {
        super(FXML);
        this.plannerDay = plannerDay;
        dateNumber.setText(Integer.toString(plannerDay.getDay()));
    }

}
