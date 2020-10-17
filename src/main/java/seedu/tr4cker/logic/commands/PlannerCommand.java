package seedu.tr4cker.logic.commands;

import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_PLANNER_GOTO;

import java.time.LocalDate;

import seedu.tr4cker.model.Model;

/**
 * Allows user to go to Planner tab or go to specific date/month of calendar in Planner tab.
 */
public class PlannerCommand extends Command {

    public static final String COMMAND_WORD = "planner";

    public static final String MESSAGE_SWITCH_TAB_SUCCESS = "Switched to Planner tab!";

    public static final String MESSAGE_SWITCH_TAB_USAGE = COMMAND_WORD + ": Switches to Planner tab\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_GOTO_USAGE = COMMAND_WORD + ": Goes to a specific date/month on Planner tab\n"
            + "Parameters: "
            + "[" + PREFIX_PLANNER_GOTO + "DATE/MONTH]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PLANNER_GOTO + "25-12-2020";

    private final LocalDate gotoDate;

    /**
     * Constructor for PlannerCommand when user wants to switch to Planner tab.
     */
    public PlannerCommand() {
        this.gotoDate = null;
    }

    /**
     * Constructor for PlannerCommand when user wants to go to a specific date/month in Planner tab.
     *
     * @param gotoDate User's input.
     */
    public PlannerCommand(LocalDate gotoDate) {
        this.gotoDate = gotoDate;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SWITCH_TAB_SUCCESS, false, false, true);
    }

}
