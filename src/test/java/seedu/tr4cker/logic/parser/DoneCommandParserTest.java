package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COMPLETION_STATUS;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.logic.commands.DoneCommand;
import seedu.tr4cker.model.task.CompletionStatus;

public class DoneCommandParserTest {

    private static final String VALID_COMPLETION_STATUS_1 = "0";
    private static final String VALID_COMPLETION_STATUS_2 = "50";
    private static final String VALID_COMPLETION_STATUS_3 = "100";
    private static final String INVALID_COMPLETION_STATUS_1 = "1000";
    private static final String INVALID_COMPLETION_STATUS_2 = "-10";
    private static final String NOT_A_COMPLETION_STATUS = "HEHEXD";

    private static final String VALID_DONE_DESC_1 = "1"
            + " " + PREFIX_COMPLETION_STATUS + VALID_COMPLETION_STATUS_1;
    private static final String VALID_DONE_DESC_2 = "1"
            + " " + PREFIX_COMPLETION_STATUS + VALID_COMPLETION_STATUS_2;
    private static final String VALID_DONE_DESC_3 = "1"
            + " " + PREFIX_COMPLETION_STATUS + VALID_COMPLETION_STATUS_3;
    private static final String INVALID_DONE_DESC_1 = "1"
            + " " + PREFIX_COMPLETION_STATUS + INVALID_COMPLETION_STATUS_1;
    private static final String INVALID_DONE_DESC_2 = "1"
            + " " + PREFIX_COMPLETION_STATUS + INVALID_COMPLETION_STATUS_2;
    private static final String INVALID_DONE_DESC_3 = "1"
            + " " + PREFIX_COMPLETION_STATUS + NOT_A_COMPLETION_STATUS;

    private static final CompletionStatus COMPLETION_STATUS_1 = new CompletionStatus(0);
    private static final CompletionStatus COMPLETION_STATUS_2 = new CompletionStatus(50);
    private static final CompletionStatus COMPLETION_STATUS_3 = new CompletionStatus(100);

    private final DoneCommandParser parser = new DoneCommandParser();

    @Test
    public void parse_validArgs_returnsDoneCommand() {
        assertParseSuccess(parser, VALID_DONE_DESC_1,
                new DoneCommand(INDEX_FIRST_TASK, COMPLETION_STATUS_1));
        assertParseSuccess(parser, VALID_DONE_DESC_2,
                new DoneCommand(INDEX_FIRST_TASK, COMPLETION_STATUS_2));
        assertParseSuccess(parser, VALID_DONE_DESC_3,
                new DoneCommand(INDEX_FIRST_TASK, COMPLETION_STATUS_3));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_DONE_DESC_1, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DoneCommand.MESSAGE_USAGE));
        assertParseFailure(parser, INVALID_DONE_DESC_2, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DoneCommand.MESSAGE_USAGE));
        assertParseFailure(parser, INVALID_DONE_DESC_3, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DoneCommand.MESSAGE_USAGE));
    }
}
