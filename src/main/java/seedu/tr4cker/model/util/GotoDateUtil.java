package seedu.tr4cker.model.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Contains utility methods for PlannerCommandParser to check validity of goto dates.
 */
public class GotoDateUtil {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-[MM][MMM]-yyyy");
    private static final DateTimeFormatter MONTH_FORMAT = DateTimeFormatter.ofPattern("[MM][MMM]-yyyy");
    private static final DateTimeFormatter STRING_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    private static final DateTimeFormatter STRING_MONTH_FORMAT = DateTimeFormatter.ofPattern("MMM-yyyy");

    /**
     * Returns true if a given string is a valid goto date.
     *
     * @param test Input string.
     * @return true if a given string is a valid goto date.
     */
    public static boolean isValidGotoDate(String test) {
        try {
            LocalDate.parse(test, DATE_FORMAT);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if a given string is a valid goto month.
     *
     * @param test Input string.
     * @return true if a given string is a valid goto month.
     */
    public static boolean isValidGotoMonth(String test) {
        try {
            YearMonth.parse(test, MONTH_FORMAT);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    /**
     * Returns a LocalDate object of the input gotoDay.
     *
     * @param gotoDay Checked gotoDay.
     * @return LocalDate of input.
     */
    public static LocalDate splitGotoDay(String gotoDay) {
        return LocalDate.parse(gotoDay, DATE_FORMAT);
    }

    /**
     * Returns a YearMonth object of the input gotoMonth.
     *
     * @param gotoMonth Checked gotoMonth.
     * @return YearMonth of input.
     */
    public static YearMonth splitGotoMonth(String gotoMonth) {
        return YearMonth.parse(gotoMonth, MONTH_FORMAT);
    }

    /**
     * Parses a LocalDate to a string representation.
     *
     * @param gotoDay GotoDay.
     * @return String representation of the GotoDay.
     */
    public static String parseGotoDay(LocalDate gotoDay) {
        return gotoDay.format(STRING_DATE_FORMAT);
    }

    /**
     * Parses a YearMonth to a string representation.
     *
     * @param gotoMonth GotoMonth.
     * @return String representation of the GotoMonth.
     */
    public static String parseGotoMonth(YearMonth gotoMonth) {
        return gotoMonth.format(STRING_MONTH_FORMAT);
    }

    /**
     * Checks if user's input is "today" or "tdy".
     *
     * @param gotoDay User's input.
     * @return True if matches.
     */
    public static boolean checkToday(String gotoDay) {
        return gotoDay.contentEquals("today") || gotoDay.contentEquals("tdy");
    }

    /**
     * Gets today's date.
     *
     * @return LocalDate object of today's date.
     */
    public static LocalDate getToday() {
        return LocalDate.now();
    }

    /**
     * Checks if user's input is "tomorrow" or "tmr".
     *
     * @param gotoDay User's input.
     * @return True if matches.
     */
    public static boolean checkTomorrow(String gotoDay) {
        return gotoDay.contentEquals("tomorrow") || gotoDay.contentEquals("tmr");
    }

    /**
     * Gets tomorrow's date.
     *
     * @return LocalDate object of tomorrow's date.
     */
    public static LocalDate getTomorrow() {
        return LocalDate.now().plusDays(1);
    }

}
