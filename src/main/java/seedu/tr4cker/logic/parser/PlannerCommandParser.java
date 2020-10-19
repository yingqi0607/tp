package seedu.tr4cker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_PLANNER_GOTO;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.stream.Stream;

import seedu.tr4cker.logic.commands.PlannerCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.task.TaskDueInPredicate;
import seedu.tr4cker.model.util.GotoDateUtil;

/**
 * Parses input arguments and creates a new PlannerCommand object.
 */
public class PlannerCommandParser implements Parser<PlannerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PlannerCommand
     * and returns an PlannerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public PlannerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PLANNER_GOTO);

        // user wants to go to Planner tab
        if (!arePrefixesPresent(argMultimap, PREFIX_PLANNER_GOTO)) {
            String string = argMultimap.getPreamble();
            if (!string.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        PlannerCommand.MESSAGE_SWITCH_TAB_USAGE));
            }
            return new PlannerCommand();
        }

        // user wants to goto a specific day/date/month
        return parseGotoDay(argMultimap.getValue(PREFIX_PLANNER_GOTO).get());
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses {@code String gotoDay} into a {@code PlannerCommand}.
     *
     * @throws ParseException if the given {@code gotoDay} is invalid.
     */
    private static PlannerCommand parseGotoDay(String gotoDay) throws ParseException {
        requireNonNull(gotoDay);
        String trimmedGotoDay = gotoDay.trim();

        boolean isToday;
        boolean isTomorrow;
        boolean isValidGotoDate;
        boolean isValidGotoMonth;

        isToday = GotoDateUtil.checkToday(trimmedGotoDay);
        isTomorrow = GotoDateUtil.checkTomorrow(trimmedGotoDay);
        isValidGotoDate = GotoDateUtil.isValidGotoDate(trimmedGotoDay);
        isValidGotoMonth = GotoDateUtil.isValidGotoMonth(trimmedGotoDay);

        LocalDate localDate = null;
        YearMonth yearMonth = null;
        String message;
        TaskDueInPredicate taskDueInPredicate;

        if (isToday) {
            localDate = GotoDateUtil.getToday();
            message = GotoDateUtil.parseGotoDay(localDate) + " (TODAY)";
            taskDueInPredicate = new TaskDueInPredicate();
        } else if (isTomorrow) {
            localDate = GotoDateUtil.getTomorrow();
            message = GotoDateUtil.parseGotoDay(localDate) + " (TOMORROW)";
            taskDueInPredicate = new TaskDueInPredicate(localDate);
        } else if (isValidGotoDate) {
            localDate = GotoDateUtil.splitGotoDay(trimmedGotoDay);
            message = GotoDateUtil.parseGotoDay(localDate);
            taskDueInPredicate = new TaskDueInPredicate(localDate);
        } else if (isValidGotoMonth) {
            yearMonth = GotoDateUtil.splitGotoMonth(trimmedGotoDay);
            message = GotoDateUtil.parseGotoMonth(yearMonth);
            taskDueInPredicate = new TaskDueInPredicate(yearMonth);
        } else {
            throw new ParseException(PlannerCommand.MESSAGE_GOTO_USAGE);
        }
        return new PlannerCommand(message, localDate, yearMonth, taskDueInPredicate);
    }

}
