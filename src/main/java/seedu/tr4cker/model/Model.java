package seedu.tr4cker.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.tr4cker.commons.core.GuiSettings;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Task;

/** The API of the Model component. */
public interface Model {
    /** {@code Predicate} that always evaluate to true. */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /** {@code Predicate} that evaluates to true when task is not expired. */
    Predicate<Task> PREDICATE_SHOW_PENDING_TASKS = task -> Deadline.isFutureDeadline(task.getDeadline().toString())
            && !task.isCompleted();

    /** {@code Predicate} that evaluates to true when task is expired. */
    Predicate<Task> PREDICATE_SHOW_EXPIRED_TASKS = task -> !Deadline.isFutureDeadline(task.getDeadline().toString())
            && !task.isCompleted();

    /** {@code Predicate} that evaluates to true when task is completed. */
    Predicate<Task> PREDICATE_SHOW_COMPLETED_TASKS = Task::isCompleted;

    /** Replaces user prefs data with the data in {@code userPrefs}. */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /** Returns the user prefs. */
    ReadOnlyUserPrefs getUserPrefs();

    /** Returns the user prefs' GUI settings. */
    GuiSettings getGuiSettings();

    /** Sets the user prefs' GUI settings. */
    void setGuiSettings(GuiSettings guiSettings);

    /** Returns the user prefs' Tr4cker file path. */
    Path getTr4ckerFilePath();

    /** Sets the user prefs' Tr4cker file path. */
    void setTr4ckerFilePath(Path tr4ckerFilePath);

    /** Replaces Tr4cker data with the data in {@code tr4cker}. */
    void setTr4cker(ReadOnlyTr4cker tr4cker);

    /** Returns the Tr4cker. */
    ReadOnlyTr4cker getTr4cker();

    /** Returns true if a task with the same identity as {@code task} exists in Tr4cker. */
    boolean hasTask(Task task);

    /** Returns true if a task with the same module code as {@code module} exists in Tr4cker. */
    boolean hasRelatedTasks(Module module);

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

    /** Returns true if a module with the same identity as {@code module} exists in Tr4cker. */
    boolean hasModule(Module module);

    /** Returns true if a {@code task} has a module code that exists in Tr4cker, or if
     * it's module field is null.
     */
    boolean hasValidModuleField(Task task);

    /**
     * Deletes the given module.
     * The module must exist in Tr4cker.
     */
    void deleteModule(Module target);

    /**
     * Adds the given module.
     * {@code module} must not already exist in Tr4cker.
     */
    void addModule(Module module);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the tr4cker.
     * The task identity of {@code editedTask} must not be the same as another existing task in Tr4cker.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list. */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the filtered expired task list. */
    ObservableList<Task> getFilteredExpiredTaskList();

    /** Returns an unmodifiable view of the filtered completed task list. */
    ObservableList<Task> getFilteredCompletedTaskList();

    /** Returns an unmodifiable view of the list of modules. */
    ObservableList<Module> getFilteredModuleList();

    /** Returns an unmodifiable view of the filtered task list for PlannerDay. */
    ObservableList<Task> getPlannerFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Updates the filter of the filtered expired task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExpiredTaskList(Predicate<Task> predicate);

    /**
     * Updates the filter of the filtered completed task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCompletedTaskList(Predicate<Task> predicate);

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate} for Planner tab.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updatePlannerFilteredTaskList(Predicate<Task> predicate);

}
