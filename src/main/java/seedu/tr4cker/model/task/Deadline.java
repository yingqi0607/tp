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
            "Invalid format to input Deadline. Please ensure the following:\n"
                    + "1. Enter a valid date\n"
                    + "2. Formats accepted: dd-MM-yyyy HHmm and dd-MMM-yyyy HHmm\n"
                    + "(e.g. 25-01-2021 1800 or 25-Jan-2021 1800)\n"
                    + "3. If deadline time is not entered, it will be set to 2359 by default";
    public static final String MESSAGE_FUTURE_CONSTRAINT =
            "Deadline should be a time in the future";
    public static final String VALIDATION_REGEX_MM = "\\d{2}-\\d{2}-\\d{4} \\d{4}";
    public static final String VALIDATION_REGEX_MMM = "\\d{2}-[a-zA-Z]{3}-\\d{4} \\d{4}";

    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-[MM][MMM]-yyyy HHmm");
    public static final String DEFAULT_TIME = " 2359";
    public final LocalDateTime dateTime;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline time.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        checkArgument(isFutureDeadline(deadline), MESSAGE_FUTURE_CONSTRAINT);
        dateTime = LocalDateTime.parse(deadline, DATE_TIME_FORMAT);
    }

    /**
     * Returns true if a given string is a valid deadline time.
     */
    public static boolean isValidDeadline(String test) {
        try {
            LocalDateTime.parse(test, DATE_TIME_FORMAT);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    public static boolean isDeadlineWithTime(String test) {
        return test.matches(VALIDATION_REGEX_MM) || test.matches(VALIDATION_REGEX_MMM);
    }

    /**
     * Returns true if a given string is a future time.
     */
    public static boolean isFutureDeadline(String test) {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.parse(test, DATE_TIME_FORMAT).isAfter(now);
    }

    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HHmm"));
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