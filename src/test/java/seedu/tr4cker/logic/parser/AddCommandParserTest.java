package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DEADLINE_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DEADLINE_DESC_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DESCRIPTION_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DESCRIPTION_DESC_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.NAME_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.NAME_DESC_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.tr4cker.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.tr4cker.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.tr4cker.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tr4cker.testutil.TypicalTasks.MANUALTASK1;
import static seedu.tr4cker.testutil.TypicalTasks.MANUALTASK2;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.logic.commands.AddCommand;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.TaskDescription;
import seedu.tr4cker.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(MANUALTASK2).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_2 + DEADLINE_DESC_2
                + DESCRIPTION_DESC_2 + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_1 + NAME_DESC_2 + DEADLINE_DESC_2
                + DESCRIPTION_DESC_2 + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, NAME_DESC_2 + DEADLINE_DESC_1 + DEADLINE_DESC_2
                + DESCRIPTION_DESC_2 + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, NAME_DESC_2 + DEADLINE_DESC_2 + DESCRIPTION_DESC_1
                + DESCRIPTION_DESC_2 + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(MANUALTASK2).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_2 + DEADLINE_DESC_2 + DESCRIPTION_DESC_2
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(MANUALTASK1).withTags().build();
        assertParseSuccess(parser, NAME_DESC_1 + DEADLINE_DESC_1 + DESCRIPTION_DESC_1,
                new AddCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_2 + DEADLINE_DESC_2 + DESCRIPTION_DESC_2,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_2 + VALID_DEADLINE_2 + DESCRIPTION_DESC_2,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_2 + DEADLINE_DESC_2 + VALID_DESCRIPTION_2,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_2 + VALID_DEADLINE_2 + VALID_DESCRIPTION_2,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DEADLINE_DESC_2 + DESCRIPTION_DESC_2
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, NAME_DESC_2 + INVALID_DEADLINE_DESC + DESCRIPTION_DESC_2
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Deadline.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_2 + DEADLINE_DESC_2 + INVALID_DESCRIPTION_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, TaskDescription.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_2 + DEADLINE_DESC_2 + DESCRIPTION_DESC_2
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + DEADLINE_DESC_2 + INVALID_DESCRIPTION_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_2 + DEADLINE_DESC_2
                + DESCRIPTION_DESC_2 + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
