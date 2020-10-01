package seedu.tr4cker.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task's deadline time in Tr4cker.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "deadline times should only contain numbers, and it should follow the format yyyy-MM-dd HHmm";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{4}";
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final String DEFAULT_TIME = " 2359";
    public final LocalDateTime dateTime;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline time.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        if (!isDeadlineWithTime(deadline)) {
            dateTime = LocalDateTime.parse(deadline + DEFAULT_TIME, DATE_TIME_FORMAT);
        } else {
            dateTime = LocalDateTime.parse(deadline, DATE_TIME_FORMAT);
        }
    }

    /**
     * Returns true if a given string is a valid deadline time.
     */
    public static boolean isValidDeadline(String test) {
        try {
            if (!isDeadlineWithTime(test)) {
                LocalDateTime.parse(test + DEFAULT_TIME, DATE_TIME_FORMAT);
            } else {
                LocalDateTime.parse(test, DATE_TIME_FORMAT);
            }
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    public static boolean isDeadlineWithTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && dateTime.equals(((Deadline) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

}
