package seedu.tr4cker.logic.commands;

import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_PLANNER_GOTO;

import seedu.tr4cker.model.Model;

/**
 * Allows user to go to Planner tab or go to specific date/month of calendar in Planner tab.
 */
public class PlannerCommand extends Command {

    public static final String COMMAND_WORD = "planner";

    public static final String MESSAGE_SWITCH_TAB_USAGE = COMMAND_WORD + ": Switches to Planner tab\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_GOTO_USAGE = COMMAND_WORD + ": Goes to a specific date/month on Planner tab\n"
            + "Parameters: "
            + "[" + PREFIX_PLANNER_GOTO + "DATE/MONTH]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PLANNER_GOTO + "25-12-2020";

    public static final String MESSAGE_SWITCH_TAB_SUCCESS = "Switched to Planner tab!";

    public static final String MESSAGE_GOTO_DATE_SUCCESS = "Showed tasks on: %1$s";

    private final String message;

    /**
     * Constructor for PlannerCommand when user wants to switch to Planner tab.
     */
    public PlannerCommand() {
        this.message = null;
    }

    /**
     * Constructor for PlannerCommand when user wants to switch to specific date/month.
     */
    public PlannerCommand(String message) {
        this.message = message;
    }

    @Override
    public CommandResult execute(Model model) {
        if (message == null) {
            return new CommandResult(MESSAGE_SWITCH_TAB_SUCCESS, false, false, true);
        }
        String result = String.format(MESSAGE_GOTO_DATE_SUCCESS, message);
        return new CommandResult(result, false, false, true);
    }

}
