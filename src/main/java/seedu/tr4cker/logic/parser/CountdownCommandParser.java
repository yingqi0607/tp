package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tr4cker.logic.commands.CountdownCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CountdownCommand object.
 */
public class CountdownCommandParser implements Parser<CountdownCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CountdownCommand
     * and returns an CountdownCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public CountdownCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        // user wants to go to Countdown tab
        String string = argMultimap.getPreamble();
        if (!string.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CountdownCommand.MESSAGE_SWITCH_TAB_USAGE));
        }
        return new CountdownCommand();
    }
}
