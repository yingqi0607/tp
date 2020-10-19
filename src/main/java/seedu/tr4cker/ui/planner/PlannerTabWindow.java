package seedu.tr4cker.ui.planner;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.logic.Logic;
import seedu.tr4cker.logic.commands.CommandResult;
import seedu.tr4cker.model.planner.PlannerDay;
import seedu.tr4cker.model.util.GotoDateUtil;
import seedu.tr4cker.ui.TaskListPanel;
import seedu.tr4cker.ui.UiPart;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.logging.Logger;

/**
 * An UI component that displays Planner tab.
 * Displays the calendar and tasks list.
 */
public class PlannerTabWindow extends UiPart<Region>  {

    private static final String FXML = "PlannerTabWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(PlannerCalendarPanel.class);
    private final PlannerCalendarPanel plannerCalendarPanel;
    private final TaskListPanel taskListPanel;

    @FXML
    private StackPane plannerCalendarPanelPlaceholder;
    @FXML
    private StackPane plannerTaskListPanelPlaceholder;

    /**
     * Constructor for PlannerTabWindow.
     *
     * @param logic Logic of TR4CKER.
     */
    public PlannerTabWindow(Logic logic) {
        super(FXML);
        this.plannerCalendarPanel = new PlannerCalendarPanel(PlannerDay.getCurrDay());
        this.taskListPanel = new TaskListPanel(logic.getPlannerFilteredTaskList());

        plannerCalendarPanelPlaceholder.getChildren().add(plannerCalendarPanel.getRoot());
        plannerTaskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
    }

    /**
     * Updates calendar aspect of Planner tab.
     *
     * @param commandResult CommandResult being passed in.
     */
    public void updateCalendar(CommandResult commandResult) { // from mainwindow
        LocalDate localDate = commandResult.getLocalDate();
        YearMonth yearMonth = commandResult.getYearMonth();
        if (localDate != null && yearMonth == null) { // user wants to go to specified date
            if (localDate.getMonthValue() == plannerCalendarPanel.getCurrentMonth()) {
                int dayIndex = localDate.getDayOfMonth();
                if (localDate.equals(GotoDateUtil.getToday())) {
                    // need to do UI
                } else {

                    // need to do UI
                }
            }
        } else if (localDate == null && yearMonth != null) { // user wants to go to specified month
            LocalDate firstDayOfMonth = yearMonth.atDay(1);
            PlannerDay newDay = new PlannerDay(firstDayOfMonth);
            plannerCalendarPanel.clearCalendar();
            plannerCalendarPanel.changeCalendarMonthYear(yearMonth);
            plannerCalendarPanel.fillCalendarTable(newDay);
            // need to do UI
        }
    }

}
