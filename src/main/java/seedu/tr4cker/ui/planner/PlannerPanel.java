package seedu.tr4cker.ui.planner;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.model.planner.PlannerDay;
import seedu.tr4cker.ui.UiPart;

public class PlannerPanel extends UiPart<Region> {
    private static final String FXML = "PlannerPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PlannerPanel.class);

    private PlannerDay plannerDay;
    private ArrayList<PlannerDayCard> plannerDayCards;

    @FXML
    private GridPane plannerTable;

    @FXML
    private Label plannerMonthYear;

    /**
     * Creates a {@code PlannerPanel}.
     */
    public PlannerPanel(PlannerDay plannerDay) {
        super(FXML);
        logger.fine("Initialising plannerDay panel...");
        this.plannerDay = plannerDay;
        this.plannerMonthYear.setText(plannerDay.getMonthName() + " " + plannerDay.getYear());

        PlannerDay startDay = plannerDay.createFirstDayOfMonth();
        fillPlannerTable(startDay);
    }

    private void fillPlannerTable(PlannerDay startDay) {
        int index = startDay.getDayOfWeek();
        PlannerDay currDay = startDay;
        if (index != 1) {
            for (int i = index - 1; i > 0; i--) {
                currDay = currDay.getPrevDay();
            }
        }
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                PlannerDayCard plannerDayCard = new PlannerDayCard(currDay);
                plannerTable.add(plannerDayCard.getRoot(), col, row);
                currDay = currDay.getNextDay();
            }
        }
    }

}
