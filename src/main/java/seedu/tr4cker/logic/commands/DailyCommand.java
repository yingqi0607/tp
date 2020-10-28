package seedu.tr4cker.logic.commands;

import seedu.tr4cker.model.Model;

public class DailyCommand extends Command {

    public static final String COMMAND_WORD = "daily";

    public static final String MESSAGE_SWITCH_TAB_USAGE = COMMAND_WORD + ": Switches to Daily tab\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SWITCH_TAB_SUCCESS = "Switched to Daily tab!";

    /**
     * Constructor for DailyCommand when user wants to switch to Daily tab.
     */
    public DailyCommand() {
    }

    @Override
    public CommandResult execute(Model model) {
        return CommandResult.createDailyTabSwitchCommandResult(MESSAGE_SWITCH_TAB_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { // short circuit if same object
            return true;
        } else if (!(other instanceof DailyCommand)) {
            return false;
        } else {
            return false;
        }
    }
}
