package seedu.tr4cker.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.tr4cker.commons.core.GuiSettings;
import seedu.tr4cker.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true. */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' Tr4cker file path.
     */
    Path getTr4ckerFilePath();

    /**
     * Sets the user prefs' Tr4cker file path.
     */
    void setTr4ckerFilePath(Path tr4ckerFilePath);

    /**
     * Replaces Tr4cker data with the data in {@code tr4cker}.
     */
    void setTr4cker(ReadOnlyTr4cker tr4cker);

    /** Returns the Tr4cker. */
    ReadOnlyTr4cker getTr4cker();

    /**
     * Returns true if a task with the same identity as {@code task} exists in Tr4cker.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in Tr4cker.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in Tr4cker.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the tr4cker.
     * The task identity of {@code editedTask} must not be the same as another existing task in Tr4cker.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list. */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);
}
