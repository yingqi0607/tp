package seedu.tr4cker.logic.commands;

import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_PLANNER_GOTO;

import java.time.LocalDate;

import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.task.TaskDueInPredicate;

/**
 * Allows user to go to Planner tab or go to specific date/month of calendar in Planner tab.
 */
public class PlannerCommand extends Command {

    public static final String COMMAND_WORD = "planner";

    public static final String MESSAGE_SWITCH_TAB_USAGE = COMMAND_WORD + ": Switches to Planner tab\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_GOTO_USAGE = COMMAND_WORD + ": Goes to a specific date/month on Planner tab\n"
            + "Parameters: "
            + "[" + PREFIX_PLANNER_GOTO + "DAY]\n"
            + "Examples: \n"
            + COMMAND_WORD + " " + PREFIX_PLANNER_GOTO + "today OR "
            + COMMAND_WORD + " " + PREFIX_PLANNER_GOTO + "tdy\n"
            + COMMAND_WORD + " " + PREFIX_PLANNER_GOTO + "tomorrow OR "
            + COMMAND_WORD + " " + PREFIX_PLANNER_GOTO + "tmr\n"
            + COMMAND_WORD + " " + PREFIX_PLANNER_GOTO + "12-2020 OR "
            + COMMAND_WORD + " " + PREFIX_PLANNER_GOTO + "Dec-2020\n"
            + COMMAND_WORD + " " + PREFIX_PLANNER_GOTO + "25-12-2020 OR "
            + COMMAND_WORD + " " + PREFIX_PLANNER_GOTO + "25-Dec-2020";

    public static final String MESSAGE_SWITCH_TAB_SUCCESS = "Switched to Planner tab!";

    public static final String MESSAGE_GOTO_DATE_SUCCESS = "Showed tasks on: %1$s";

    private final String message;
    private final TaskDueInPredicate taskDueInPredicate;

    /**
     * Constructor for PlannerCommand when user wants to switch to Planner tab.
     */
    public PlannerCommand() {
        this.message = null;
        this.taskDueInPredicate = new TaskDueInPredicate(LocalDate.now());
    }

    /**
     * Constructor for PlannerCommand when user wants to switch to specific date/month.
     */
    public PlannerCommand(String message, TaskDueInPredicate taskDueInPredicate) {
        this.message = message;
        this.taskDueInPredicate = taskDueInPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        if (message == null) {
            model.updatePlannerFilteredTaskList(taskDueInPredicate);
            return new CommandResult(MESSAGE_SWITCH_TAB_SUCCESS, false, false, true);
        }
        model.updatePlannerFilteredTaskList(taskDueInPredicate);
        String result = String.format(MESSAGE_GOTO_DATE_SUCCESS, message);
        return new CommandResult(result, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this // short circuit if same object
                || (other instanceof PlannerCommand // instanceof handles nulls
                && message == null && ((PlannerCommand) other).message == null
                && taskDueInPredicate.equals(((PlannerCommand) other).taskDueInPredicate))) {
            return true;
        } else if (!(other instanceof PlannerCommand)) {
            return false;
        }
        assert message != null;
        return message.equals(((PlannerCommand) other).message) // instanceof handles not null
                && taskDueInPredicate.equals(((PlannerCommand) other).taskDueInPredicate); // state check
    }

}
