package seedu.tr4cker.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_ADDRESS_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalTasks.MANUALTASK2;
import static seedu.tr4cker.testutil.TypicalTasks.TASK1;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(TASK1.isSameTask(TASK1));

        // null -> returns false
        assertFalse(TASK1.isSameTask(null));

        // different deadline and address -> returns false
        Task editedTask1 =
                new TaskBuilder(TASK1).withDeadline(VALID_DEADLINE_2).withAddress(VALID_ADDRESS_2).build();
        assertFalse(TASK1.isSameTask(editedTask1));

        // different name -> returns false
        editedTask1 = new TaskBuilder(TASK1).withName(VALID_NAME_2).build();
        assertFalse(TASK1.isSameTask(editedTask1));

        // same name, same deadline, different attributes -> returns true
        editedTask1 = new TaskBuilder(TASK1).withCompletionStatus(VALID_COMPLETION_STATUS_2)
                .withAddress(VALID_ADDRESS_2).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(TASK1.isSameTask(editedTask1));

        // same name, different attributes -> returns false
        editedTask1 = new TaskBuilder(TASK1).withDeadline(VALID_DEADLINE_2).withAddress(VALID_ADDRESS_2)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(TASK1.isSameTask(editedTask1));

        // same name, same deadline, different attributes -> returns true
        editedTask1 = new TaskBuilder(TASK1).withAddress(VALID_ADDRESS_2).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(TASK1.isSameTask(editedTask1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(TASK1).build();
        assertTrue(TASK1.equals(aliceCopy));

        // same object -> returns true
        assertTrue(TASK1.equals(TASK1));

        // null -> returns false
        assertFalse(TASK1.equals(null));

        // different type -> returns false
        assertFalse(TASK1.equals(5));

        // different task -> returns false
        assertFalse(TASK1.equals(MANUALTASK2));

        // different name -> returns false
        Task editedTask1 = new TaskBuilder(TASK1).withName(VALID_NAME_2).build();
        assertFalse(TASK1.equals(editedTask1));

        // different deadline -> returns false
        editedTask1 = new TaskBuilder(TASK1).withDeadline(VALID_DEADLINE_2).build();
        assertFalse(TASK1.equals(editedTask1));

        // different completionStatus -> returns true
        editedTask1 = new TaskBuilder(TASK1).withCompletionStatus(VALID_COMPLETION_STATUS_2).build();
        assertTrue(TASK1.equals(editedTask1));

        // different tr4cker -> returns false
        editedTask1 = new TaskBuilder(TASK1).withAddress(VALID_ADDRESS_2).build();
        assertFalse(TASK1.equals(editedTask1));

        // different tags -> returns false
        editedTask1 = new TaskBuilder(TASK1).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(TASK1.equals(editedTask1));
    }
}
