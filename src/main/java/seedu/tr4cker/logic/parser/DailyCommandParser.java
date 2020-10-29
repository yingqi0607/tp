package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tr4cker.logic.commands.DailyCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DailyCommand object.
 */
public class DailyCommandParser implements Parser<DailyCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DailyCommand
     * and returns an DailyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DailyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        // user wants to go to Daily tab
        String string = argMultimap.getPreamble();
        if (!string.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DailyCommand.MESSAGE_SWITCH_TAB_USAGE));
        }
        return new DailyCommand();
    }
}
