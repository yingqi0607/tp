package seedu.tr4cker.ui.countdown;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.tr4cker.logic.Logic;
import seedu.tr4cker.ui.TaskListPanel;
import seedu.tr4cker.ui.UiPart;

/**
 * An UI component that displays Planner tab.
 * Displays the calendar and tasks list.
 */
public class CountdownTabWindow extends UiPart<Region> {

    private static final String FXML = "CountdownTabWindow.fxml";

    private final TaskListPanel taskListPanel;

    @FXML
    private StackPane countdownListPanelPlaceholder;

    /**
     * Constructor for PlannerTabWindow.
     *
     * @param logic Logic of TR4CKER.
     */
    public CountdownTabWindow(Logic logic) {
        super(FXML);
        this.taskListPanel = new TaskListPanel(logic.getFilteredTaskList());

        countdownListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
    }
}
