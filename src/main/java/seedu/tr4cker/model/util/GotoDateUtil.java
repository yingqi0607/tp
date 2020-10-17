package seedu.tr4cker.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Contains utility methods for PlannerCommandParser to check validity of goto dates.
 */
public class GotoDateUtil {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-[MM][MMM]-yyyy");

    /**
     * Returns true if a given string is a valid goto day.
     *
     * @param test Input string.
     * @return true if a given string is a valid goto day.
     */
    public static boolean isValidGoto(String test) {
        boolean isValid;
        String gotoMonth = "";
        isValid = isValidGotoDate(test);
        if (!isValid) {
            gotoMonth += "01-" + test;
            isValid = isValidGotoDate(gotoMonth);
        }
        return isValid;
    }

    /**
     * Returns true if a given string is a valid goto date.
     *
     * @param test Input string.
     * @return true if a given string is a valid goto date.
     */
    private static boolean isValidGotoDate(String test) {
        try {
            LocalDate.parse(test, DATE_FORMAT);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    /**
     * Returns a valid goto date if the input is in month format.
     *
     * @param gotoDay Unchecked gotoDay.
     * @return Valid goto date.
     */
    public static String checkGotoDay(String gotoDay) {
        String gotoMonth = "";
        if (gotoDay.split("-").length == 3) {
            return gotoDay;
        }
        gotoMonth += "01-" + gotoDay;
        return gotoMonth;
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

}
