package seedu.tr4cker.logic.commands;

import seedu.tr4cker.model.Model;

import static seedu.tr4cker.model.Model.PREDICATE_SHOW_ALL_EVENTS;

/**
 * Allows user to go to Countdown tab.
 */
public class CountdownCommand extends Command {

    public static final String COMMAND_WORD = "countdown";

    public static final String MESSAGE_SWITCH_TAB_USAGE = COMMAND_WORD + ": Switches to Countdown tab\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SWITCH_TAB_SUCCESS = "Switched to Countdown tab!";

    /**
     * Constructor for CountdownCommand when user wants to switch to Countdown tab.
     */
    public CountdownCommand() {
    }

    @Override
    public CommandResult execute(Model model) {
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(MESSAGE_SWITCH_TAB_SUCCESS, true);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { // short circuit if same object
            return true;
        } else if (!(other instanceof CountdownCommand)) {
            return false;
        } else {
            return false; // to be implemented
        }
    }
}
