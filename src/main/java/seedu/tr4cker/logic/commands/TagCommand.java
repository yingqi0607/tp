package seedu.tr4cker.logic.commands;

import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_DELETE_TAG;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_NEW_TAG;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Set;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.Task;

public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds/Deletes tag for a task on TR4CKER\n"
            + "Parameters: "
            + "[" + PREFIX_NEW_TAG + "NEW_TAG]... "
            + "[" + PREFIX_DELETE_TAG + "TAG_TO_DELETE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NEW_TAG + "urgent "
            + PREFIX_DELETE_TAG + "stillHaveTime";

    public static final String MESSAGE_NEW_TAG_SUCCESS = "New tag added to Task: %1$s";
    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Tag deleted for Task: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists for Task: %1$s";
    public static final String MESSAGE_ERROR = "This tag cannot be added to Task: %1$s";

    private final Index index;
    private final Set<Tag> tagsToAdd;
    private final Set<Tag> tagsToDelete;
    private boolean isNewTag;

    /**
     * @param index of the task in the filtered task list to edit the tags.
     * @param tagsToAdd Tags to be added to the task.
     * @param tagsToDelete Tags to be deleted from the task.
     */
    public TagCommand(Index index, Set<Tag> tagsToAdd, Set<Tag> tagsToDelete) {
        requireAllNonNull(index);

        this.index = index;
        this.tagsToAdd = tagsToAdd;
        this.tagsToDelete = tagsToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        taskToEdit.addTags(tagsToAdd);
        taskToEdit.deleteTags(tagsToDelete);
        Task editedTask = new Task(taskToEdit.getName(), taskToEdit.getDeadline(), taskToEdit.getCompletionStatus(),
                taskToEdit.getTaskDescription(), taskToEdit.getTags());

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(generateSuccessMessage(editedTask));
    }

    /**
     * Generates a command execution success message based on whether the tag is added
     * to or removed from {@code taskToEdit}.
     */
    private String generateSuccessMessage(Task taskToEdit) {
        String message = MESSAGE_NEW_TAG_SUCCESS;
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
                && tagsToAdd.equals(e.tagsToAdd)
                && tagsToDelete.equals(e.tagsToDelete);
    }

}
