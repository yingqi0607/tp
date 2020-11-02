package seedu.tr4cker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_DATE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_DELETE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_NEW;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_TASK;

import java.util.stream.Stream;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.CountdownCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;

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
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_COUNTDOWN_NEW, PREFIX_COUNTDOWN_DATE, PREFIX_COUNTDOWN_TASK, PREFIX_COUNTDOWN_DELETE);

        // should not have preamble whatsoever
        String string = argMultimap.getPreamble();
        if (!string.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CountdownCommand.MESSAGE_SWITCH_TAB_USAGE));
        }

        // user wants to go to Countdown tab
        if (!arePrefixesPresent(argMultimap, PREFIX_COUNTDOWN_NEW, PREFIX_COUNTDOWN_DATE,
                PREFIX_COUNTDOWN_TASK, PREFIX_COUNTDOWN_DELETE)) {
            return new CountdownCommand();
        }

        // user wants to add a new event
        if (argMultimap.getValue(PREFIX_COUNTDOWN_NEW).isPresent()
                && argMultimap.getValue(PREFIX_COUNTDOWN_DATE).isPresent()
                && !arePrefixesPresent(argMultimap, PREFIX_COUNTDOWN_DELETE, PREFIX_COUNTDOWN_TASK)) {
            return getCountdownCommandAdd(argMultimap);
        }

        // user wants to delete an event
        if (argMultimap.getValue(PREFIX_COUNTDOWN_DELETE).isPresent()
                && !arePrefixesPresent(argMultimap, PREFIX_COUNTDOWN_NEW,
                PREFIX_COUNTDOWN_DATE, PREFIX_COUNTDOWN_TASK)) {
            return getCountdownCommandDelete(argMultimap);
        }

        // user wants to add an event from tasks list
        if (argMultimap.getValue(PREFIX_COUNTDOWN_TASK).isPresent()
                && !arePrefixesPresent(argMultimap, PREFIX_COUNTDOWN_DATE,
                PREFIX_COUNTDOWN_DELETE, PREFIX_COUNTDOWN_TASK)) {
            return getCountdownCommandAddFromTask(argMultimap);
        }
        throw new ParseException(CountdownCommand.MESSAGE_SWITCH_TAB_USAGE);
    }

    private CountdownCommand getCountdownCommandAddFromTask(ArgumentMultimap argMultimap) throws ParseException {
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_COUNTDOWN_TASK).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CountdownCommand.MESSAGE_SWITCH_TAB_USAGE), pe);
        }
        return new CountdownCommand(index, false); // false since has task prefix
    }

    private CountdownCommand getCountdownCommandDelete(ArgumentMultimap argMultimap) throws ParseException {
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_COUNTDOWN_DELETE).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CountdownCommand.MESSAGE_SWITCH_TAB_USAGE), pe);
        }
        return new CountdownCommand(index, true); // true since have delete prefix
    }

    private CountdownCommand getCountdownCommandAdd(ArgumentMultimap argMultimap) throws ParseException {
        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_COUNTDOWN_NEW).get());
        EventDate eventDate = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_COUNTDOWN_DATE).get());
        return new CountdownCommand(eventName, eventDate);
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        requireAllNonNull(argumentMultimap, prefixes);
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
