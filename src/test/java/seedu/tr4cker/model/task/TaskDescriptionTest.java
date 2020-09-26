package seedu.tr4cker.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDescription(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskDescription(invalidAddress));
    }

    @Test
    public void isValidTaskDescription() {
        // null tr4cker
        assertThrows(NullPointerException.class, () -> TaskDescription.isValidTaskDescription(null));

        // invalid addresses
        assertFalse(TaskDescription.isValidTaskDescription("")); // empty string
        assertFalse(TaskDescription.isValidTaskDescription(" ")); // spaces only

        // valid addresses
        assertTrue(TaskDescription.isValidTaskDescription("Blk 456, Den Road, #01-355"));
        assertTrue(TaskDescription.isValidTaskDescription("-")); // one character
        assertTrue(TaskDescription.isValidTaskDescription("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long tr4cker
    }
}
