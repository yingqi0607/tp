package seedu.tr4cker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_NEW_TAG;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.commons.exceptions.IllegalValueException;
import seedu.tr4cker.logic.commands.TagCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object.
 */
public class TagCommandParser implements Parser<TagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns an TagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public TagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NEW_TAG);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TagCommand.MESSAGE_USAGE), ive);
        }

        String newTag = argMultimap.getValue(PREFIX_NEW_TAG).orElse("");
        Tag tag = new Tag(newTag);

        return new TagCommand(index, tag);
    }
}
