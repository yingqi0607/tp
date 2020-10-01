package seedu.tr4cker.logic.commands;

import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_NEW_TAG;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.Task;

public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds/Deletes tags to a task on TR4CKER. "
            + "Parameters: "
            + "[" + PREFIX_NEW_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NEW_TAG + "friends ";

    public static final String MESSAGE_NEW_TAG_SUCCESS = "New tag added: %1$s";
    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Tag deleted: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists for this task.";

    private final Index index;
    private final Tag tag;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param tag of the person to be updated to
     */
    public TagCommand(Index index, Tag tag) {
        requireAllNonNull(index, tag);

        this.index = index;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        taskToEdit.addTag(tag);
        Task editedTask = new Task(taskToEdit.getName(), taskToEdit.getDeadline(), taskToEdit.getCompletionStatus(),
                taskToEdit.getTaskDescription(), taskToEdit.getTags()); // should update tags here

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(generateSuccessMessage(editedTask));
    }

    /**
     * Generates a command execution success message based on whether the tag is added
     * to or removed from {@code taskToEdit}.
     */
    private String generateSuccessMessage(Task taskToEdit) {
        String message = !tag.tagName.isEmpty() ? MESSAGE_NEW_TAG_SUCCESS : MESSAGE_DUPLICATE_TAG;
        return String.format(message, taskToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        // state check
        TagCommand e = (TagCommand) other;
        return index.equals(e.index)
                && tag.equals(e.tag);
    }

}
