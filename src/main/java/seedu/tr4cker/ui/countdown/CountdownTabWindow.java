package seedu.tr4cker.ui.countdown;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.logic.Logic;
import seedu.tr4cker.logic.commands.CommandResult;
import seedu.tr4cker.model.planner.PlannerDay;
import seedu.tr4cker.ui.TaskListPanel;
import seedu.tr4cker.ui.UiPart;
import seedu.tr4cker.ui.planner.PlannerCalendarPanel;

/**
 * An UI component that displays Planner tab.
 * Displays the calendar and tasks list.
 */
public class CountdownTabWindow extends UiPart<Region> {

    private static final String FXML = "CountdownTabWindow.fxml";
//    private final Logger logger = LogsCenter.getLogger(PlannerCalendarPanel.class);
//    private final PlannerCalendarPanel plannerCalendarPanel;
    private final TaskListPanel taskListPanel;
//
//    @FXML
//    private StackPane plannerCalendarPanelPlaceholder;
    @FXML
    private StackPane countdownListPanelPlaceholder;

    /**
     * Constructor for PlannerTabWindow.
     *
     * @param logic Logic of TR4CKER.
     */
    public CountdownTabWindow(Logic logic) {
        super(FXML);
//        this.plannerCalendarPanel = new PlannerCalendarPanel(PlannerDay.getCurrDay());
        this.taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
//
//        plannerCalendarPanelPlaceholder.getChildren().add(plannerCalendarPanel.getRoot());
        countdownListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
    }
}
