package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.Tr4cker;

/**
 * Clears TR4CKER.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_SUCCESS = "TR4CKER has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTr4cker(new Tr4cker());
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
