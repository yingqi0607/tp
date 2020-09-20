package seedu.tr4cker.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null deadline time
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid deadline times
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("2020-10-10")); // only has date
        assertFalse(Deadline.isValidDeadline("deadline")); // non-numeric
        assertFalse(Deadline.isValidDeadline("2020-Oct-10 2359")); // alphabets within digits
        assertFalse(Deadline.isValidDeadline("2020-10-10   2359")); // additional spaces within digits

        // valid deadline times
        assertTrue(Deadline.isValidDeadline("2020-02-02 0000"));
        assertTrue(Deadline.isValidDeadline("2020-10-10 2359"));
        assertTrue(Deadline.isValidDeadline("2020-09-23 1800"));
    }
}
