package seedu.tr4cker.model;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.tr4cker.commons.core.GuiSettings;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Task;

/**
 * Represents the in-memory model of TR4CKER data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Tr4cker tr4cker;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Task> filteredExpiredTasks;
    private final FilteredList<Task> filteredCompletedTasks;
    private final FilteredList<Task> plannerFilteredTasks;

    /**
     * Initializes a ModelManager with the given tr4cker and userPrefs.
     */
    public ModelManager(ReadOnlyTr4cker tr4cker, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(tr4cker, userPrefs);

        logger.fine("Initializing with TR4CKER: " + tr4cker + " and user prefs " + userPrefs);

        this.tr4cker = new Tr4cker(tr4cker);
        this.userPrefs = new UserPrefs(userPrefs);
        //filteredTasks = new FilteredList<>(this.tr4cker.getTaskList();
        filteredTasks = new FilteredList<>(this.tr4cker.getTaskList().filtered(
            x -> Deadline.isFutureDeadline(x.getDeadline().toString()) && !x.isCompleted()));
        filteredExpiredTasks = new FilteredList<>(this.tr4cker.getTaskList().filtered(
            x -> !Deadline.isFutureDeadline(x.getDeadline().toString()) && !x.isCompleted()));
        filteredCompletedTasks = new FilteredList<>(this.tr4cker.getTaskList().filtered(
            Task::isCompleted));
        plannerFilteredTasks = new FilteredList<>(this.tr4cker.getTaskList());
    }

    public ModelManager() {
        this(new Tr4cker(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTr4ckerFilePath() {
        return userPrefs.getTr4ckerFilePath();
    }

    @Override
    public void setTr4ckerFilePath(Path tr4ckerFilePath) {
        requireNonNull(tr4ckerFilePath);
        userPrefs.setTr4ckerFilePath(tr4ckerFilePath);
    }

    //=========== TR4CKER ================================================================================

    @Override
    public void setTr4cker(ReadOnlyTr4cker tr4cker) {
        this.tr4cker.resetData(tr4cker);
    }

    @Override
    public ReadOnlyTr4cker getTr4cker() {
        return tr4cker;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tr4cker.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        tr4cker.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        tr4cker.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_PENDING_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        tr4cker.setTask(target, editedTask);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task}
     * backed by the internal list of {@code versionedTr4cker}.
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task}
     * backed by the internal list of {@code versionedTr4cker}.
     */
    @Override
    public ObservableList<Task> getFilteredExpiredTaskList() {
        return filteredExpiredTasks;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task}
     * backed by the internal list of {@code versionedTr4cker}.
     */
    @Override
    public ObservableList<Task> getFilteredCompletedTaskList() {
        return filteredCompletedTasks;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task}
     * backed by the internal list of {@code versionedTr4cker} for PlannerDay.
     * Should only show current's day tasks by default.
     */
    @Override
    public ObservableList<Task> getPlannerFilteredTaskList() {
        return plannerFilteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public void updateFilteredExpiredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredExpiredTasks.setPredicate(predicate);
    }

    @Override
    public void updateFilteredCompletedTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredCompletedTasks.setPredicate(predicate);
    }

    @Override
    public void updatePlannerFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        plannerFilteredTasks.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return tr4cker.equals(other.tr4cker)
                && userPrefs.equals(other.userPrefs)
                && filteredTasks.equals(other.filteredTasks)
                && plannerFilteredTasks.equals(other.plannerFilteredTasks);
    }

}
