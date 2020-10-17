package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_PLANNER_GOTO;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.tr4cker.logic.commands.PlannerCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;

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

        // user wants to goto a specific date/month
        LocalDate gotoDate = ParserUtil.parseGotoDay(argMultimap.getValue(PREFIX_PLANNER_GOTO).get());
        return new PlannerCommand(gotoDate);
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
