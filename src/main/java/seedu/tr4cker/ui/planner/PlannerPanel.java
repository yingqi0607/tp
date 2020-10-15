package seedu.tr4cker.ui.planner;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.model.planner.Planner;
import seedu.tr4cker.ui.UiPart;

public class PlannerPanel extends UiPart<Region> {
    private static final String FXML = "PlannerPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PlannerPanel.class);

    private Planner planner;

    @FXML
    private GridPane plannerTable;

    @FXML
    private Label plannerMonthYear;

    /**
     * Creates a {@code PlannerPanel}.
     */
    public PlannerPanel(Planner planner) {
        super(FXML);
        logger.fine("Initialising planner panel...");
        this.planner = planner;
        this.plannerMonthYear.setText(planner.getMonthName() + " " + planner.getYear());
    }

}
