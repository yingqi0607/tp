package seedu.tr4cker.ui.planner;

import java.util.logging.Logger;

import javafx.scene.layout.Region;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.ui.UiPart;

public class PlannerPanel extends UiPart<Region> {
    private static final String FXML = "PlannerPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PlannerPanel.class);

    /**
     * Creates a {@code PlannerPanel}.
     */
    public PlannerPanel() {
        super(FXML);
    }

}
