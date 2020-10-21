package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COMPLETION_STATUS;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_PENDING_TASKS;

import java.util.List;
import java.util.Set;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.TaskDescription;

/**
 * Marks the completion status of an existing task in TR4CKER.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the Completion Status of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing completion progress will be overwritten by the input value.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_COMPLETION_STATUS + "COMPLETION_STATUS (must be different from current and not exceed 100)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_COMPLETION_STATUS + "50 ";

    public static final String MESSAGE_DONE_TASK_SUCCESS_DECREASE = "Decreased Completion Percentage of Task: %1$s";
    public static final String MESSAGE_DONE_TASK_SUCCESS_INCREASE = "Increased Completion Percentage of Task: %1$s";
    public static final String MESSAGE_COMPLETION_SAME = "Percentage must be different from current value.";
    public static final String MESSAGE_EXCEEDS_RANGE = "Percentage should not exceed 100.";

    private final Index index;
    private final CompletionStatus completionStatus;

    /**
     * @param index of the task in the filtered task list to mark as done
     * @param completionStatus new completion status of the task
     */
    public DoneCommand(Index index, CompletionStatus completionStatus) {
        requireNonNull(index);
        requireNonNull(completionStatus);

        this.index = index;
        this.completionStatus = completionStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToComplete = lastShownList.get(index.getZeroBased());
        Task completedTask = createCompletedTask(taskToComplete, completionStatus);

        model.setTask(taskToComplete, completedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_PENDING_TASKS);
        if (completedTask.getCompletionStatus()
                .compareTo(taskToComplete.getCompletionStatus()) > 0) {
            return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS_INCREASE, completedTask));
        } else {
            return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS_DECREASE, completedTask));

        }
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToComplete}
     * and new percentage with {@code completionStatus}.
     */
    private static Task createCompletedTask(Task taskToComplete, CompletionStatus completionStatus)
            throws CommandException {
        assert taskToComplete != null;

        Name initialName = taskToComplete.getName();
        Deadline initialDeadline = taskToComplete.getDeadline();
        CompletionStatus initialCompletionStatus = taskToComplete.getCompletionStatus();
        TaskDescription initialTaskDescription = taskToComplete.getTaskDescription();
        Set<Tag> initialTags = taskToComplete.getTags();
        if (completionStatus.equals(initialCompletionStatus)) {
            throw new CommandException(MESSAGE_COMPLETION_SAME);
        }
        return new Task(initialName, initialDeadline, completionStatus,
                initialTaskDescription, initialTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DoneCommand)) {
            return false;
        }

        // state check
        DoneCommand e = (DoneCommand) other;
        return index.equals(e.index)
                && completionStatus.equals(e.completionStatus);
    }
}
