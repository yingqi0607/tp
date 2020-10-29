package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_EXPIRED_TASKS;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_PENDING_TASKS;

import javafx.collections.ObservableList;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.task.Task;

/**
 * Lists all tasks in TR4CKER to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list:" + "\n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPendingTaskList(PREDICATE_SHOW_PENDING_TASKS);
        model.updateFilteredExpiredTaskList(PREDICATE_SHOW_EXPIRED_TASKS);

        ObservableList<Task> taskList = model.getFilteredTaskList();
        String displayListNames = "";

        for (int i = 1; i <= taskList.size(); i++) {
            displayListNames += i + ". " + taskList.get(i - 1).getName().toString() + "\n";
        }

        CommandResult commandResult = new CommandResult(MESSAGE_SUCCESS + displayListNames);
        commandResult.setHomeTab();
        return commandResult;
    }
}
