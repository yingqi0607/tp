package seedu.tr4cker.model.util;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.temporal.TemporalAdjusters.next;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import seedu.tr4cker.logic.parser.exceptions.ParseException;

public class NaturalDateUtil {

    public static final String MESSAGE_DAY = "Please enter a valid day of the week in full, e.g. Saturday";
    public static final String MESSAGE_TIME = "Please enter a valid time in 24Hr format";
    public static final String VALIDATION_REGEX = "[a-zA-Z]*";
    public static final String VALIDATION_REGEX_TIME = "[a-zA-Z]* \\d{4}";

    private static final Map<String, DayOfWeek> daysOfWeek;

    static {
        daysOfWeek = new HashMap<>();
        daysOfWeek.put("MONDAY", MONDAY);
        daysOfWeek.put("TUESDAY", TUESDAY);
        daysOfWeek.put("WEDNESDAY", WEDNESDAY);
        daysOfWeek.put("THURSDAY", THURSDAY);
        daysOfWeek.put("FRIDAY", FRIDAY);
        daysOfWeek.put("SATURDAY", SATURDAY);
        daysOfWeek.put("SUNDAY", SUNDAY);
        daysOfWeek.put("TODAY", null);
    }

    /**
     * Returns true if a given string is a Natural Deadline.
     */
    public static boolean isNaturalDeadline(String test) {
        return test.matches(VALIDATION_REGEX) || test.matches(VALIDATION_REGEX_TIME);
    }

    /**
     * Converts a natural date-time input into a formatted DateTime String.
     *
     * @param naturalDateTime natural date-time input
     * @return formatted DateTime String
     * @throws ParseException when the date or time is in the wrong format
     */
    public static String convertToDateTime(String naturalDateTime) throws ParseException {
        String dateTime;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        String [] deadlineTokens = naturalDateTime.split(" ");
        int unitsOfDeadline = deadlineTokens.length;
        assert unitsOfDeadline == 1 || unitsOfDeadline == 2;

        String dayOfDeadline = deadlineTokens[0].toUpperCase();

        if (!daysOfWeek.containsKey(dayOfDeadline)) {
            throw new ParseException(MESSAGE_DAY);
        } else if (dayOfDeadline.equals("TODAY")) {
            dateTime = LocalDate.now().format(dateTimeFormatter);
        } else {
            dateTime = LocalDate.now().with(next(daysOfWeek.get(dayOfDeadline)))
                    .format(dateTimeFormatter);
        }

        if (unitsOfDeadline == 2) {
            try {
                LocalTime.parse(deadlineTokens[1], DateTimeFormatter.ofPattern("HHmm"));
                dateTime += " " + deadlineTokens[1];
            } catch (DateTimeParseException e) {
                throw new ParseException(MESSAGE_TIME);
            }
        }
        return dateTime;
    }
}
