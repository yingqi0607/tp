package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_EXPIRED_TASKS;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_PENDING_TASKS;

import seedu.tr4cker.model.Model;

/**
 * Lists all tasks in TR4CKER to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPendingTaskList(PREDICATE_SHOW_PENDING_TASKS);
        model.updateFilteredExpiredTaskList(PREDICATE_SHOW_EXPIRED_TASKS);

        CommandResult commandResult = new CommandResult(MESSAGE_SUCCESS);
        commandResult.setHomeTab();
        return commandResult;
    }
}
