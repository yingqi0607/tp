package seedu.tr4cker.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalTasks.TASK1;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.exceptions.DuplicateTaskException;
import seedu.tr4cker.testutil.TaskBuilder;

public class Tr4ckerTest {

    private final Tr4cker tr4cker = new Tr4cker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tr4cker.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tr4cker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTr4cker_replacesData() {
        Tr4cker newData = getTypicalTr4cker();
        tr4cker.resetData(newData);
        assertEquals(newData, tr4cker);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedAlice = new TaskBuilder(TASK1).withTaskDescription(VALID_DESCRIPTION_2).withTags(VALID_TAG_URGENT)
                .build();
        List<Task> newTasks = Arrays.asList(TASK1, editedAlice);
        Tr4ckerStub newData = new Tr4ckerStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> tr4cker.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tr4cker.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTr4cker_returnsFalse() {
        assertFalse(tr4cker.hasTask(TASK1));
    }

    @Test
    public void hasTask_taskInTr4cker_returnsTrue() {
        tr4cker.addTask(TASK1);
        assertTrue(tr4cker.hasTask(TASK1));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInTr4cker_returnsTrue() {
        tr4cker.addTask(TASK1);
        Task editedAlice = new TaskBuilder(TASK1).withTaskDescription(VALID_DESCRIPTION_2).withTags(VALID_TAG_URGENT)
                .build();
        assertTrue(tr4cker.hasTask(editedAlice));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tr4cker.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyTr4cker whose tasks list can violate interface constraints.
     */
    private static class Tr4ckerStub implements ReadOnlyTr4cker {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        Tr4ckerStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
