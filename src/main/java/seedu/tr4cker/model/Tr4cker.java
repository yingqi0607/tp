package seedu.tr4cker.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.UniqueTaskList;

/**
 * Wraps all data at TR4CKER level.
 * Duplicates are not allowed (by .isSameTask comparison).
 */
public class Tr4cker implements ReadOnlyTr4cker {

    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tasks = new UniqueTaskList();
    }

    public Tr4cker() {}

    /**
     * Creates an Tr4cker using the Tasks in the {@code toBeCopied}
     */
    public Tr4cker(ReadOnlyTr4cker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code Tr4cker} with {@code newData}.
     */
    public void resetData(ReadOnlyTr4cker newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in Tr4cker.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to Tr4cker.
     * The task must not already exist in Tr4cker.
     */
    public void addTask(Task p) {
        tasks.add(p);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in Tr4cker.
     * The task identity of {@code editedTask} must not be the same as another existing task in Tr4cker.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code Tr4cker}.
     * {@code key} must exist in Tr4cker.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        tasks.sortTasksAccordingToDeadline();
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tr4cker // instanceof handles nulls
                && tasks.equals(((Tr4cker) other).tasks));
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
