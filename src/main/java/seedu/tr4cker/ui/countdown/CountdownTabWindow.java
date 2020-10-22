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
//    private final TaskListPanel taskListPanel;
//
//    @FXML
//    private StackPane plannerCalendarPanelPlaceholder;
//    @FXML
//    private StackPane plannerTaskListPanelPlaceholder;

    /**
     * Constructor for PlannerTabWindow.
     *
     * @param logic Logic of TR4CKER.
     */
    public CountdownTabWindow(Logic logic) {
        super(FXML);
//        this.plannerCalendarPanel = new PlannerCalendarPanel(PlannerDay.getCurrDay());
//        this.taskListPanel = new TaskListPanel(logic.getPlannerFilteredTaskList());
//
//        plannerCalendarPanelPlaceholder.getChildren().add(plannerCalendarPanel.getRoot());
//        plannerTaskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
    }

//    /**
//     * Updates calendar aspect of Planner tab.
//     *
//     * @param commandResult CommandResult being passed in.
//     */
//    public void updateCalendar(CommandResult commandResult) { // from mainwindow
//        LocalDate localDate = commandResult.getLocalDate();
//        YearMonth yearMonth = commandResult.getYearMonth();
//        if (localDate != null && yearMonth == null) { // user wants to go to specified date
//            int year = localDate.getYear();
//            int month = localDate.getMonthValue();
//            int date = localDate.getDayOfMonth();
//            if (localDate.getMonthValue() == plannerCalendarPanel.getCurrentMonth()
//                    && localDate.getYear() == plannerCalendarPanel.getCurrentYear()) {
//                // check if date is the same as current highlighted day, else need to change highlight
//                plannerCalendarPanel.setCurrentYear(year);
//                plannerCalendarPanel.setCurrentMonth(month);
//
//            } else {
//                // check if date is the same as current highlighted day, else need to change highlight
//                plannerCalendarPanel.setCurrentYear(year);
//                plannerCalendarPanel.setCurrentMonth(month);
//                LocalDate newDate = LocalDate.of(year, month, 1);
//                YearMonth newYearMonth = YearMonth.of(year, month);
//                PlannerDay newDay = new PlannerDay(newDate);
//                plannerCalendarPanel.clearCalendar();
//                plannerCalendarPanel.changeCalendarMonthYear(newYearMonth);
//                plannerCalendarPanel.fillCalendarTable(newDay);
//            }
//        } else if (localDate == null && yearMonth != null) { // user wants to go to specified month
//            int year = yearMonth.getYear();
//            int month = yearMonth.getMonthValue();
//            plannerCalendarPanel.setCurrentYear(year);
//            plannerCalendarPanel.setCurrentMonth(month);
//            LocalDate firstDayOfMonth = yearMonth.atDay(1);
//            PlannerDay newDay = new PlannerDay(firstDayOfMonth);
//            plannerCalendarPanel.clearCalendar();
//            plannerCalendarPanel.changeCalendarMonthYear(yearMonth);
//            plannerCalendarPanel.fillCalendarTable(newDay);
//            // need to do UI
//        }
//    }
}
