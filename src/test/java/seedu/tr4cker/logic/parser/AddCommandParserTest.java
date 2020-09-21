package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.tr4cker.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DEADLINE_DESC_AMY;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DEADLINE_DESC_BOB;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.tr4cker.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.tr4cker.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.tr4cker.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.tr4cker.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.tr4cker.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tr4cker.testutil.TypicalTasks.AMY;
import static seedu.tr4cker.testutil.TypicalTasks.BOB;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.logic.commands.AddCommand;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.Address;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + DEADLINE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + DEADLINE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DEADLINE_DESC_AMY + DEADLINE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple addresses - last tr4cker accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DEADLINE_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + DEADLINE_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + DEADLINE_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + DEADLINE_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_DEADLINE_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + DEADLINE_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_DEADLINE_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DEADLINE_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_DEADLINE_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Deadline.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + DEADLINE_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + DEADLINE_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + DEADLINE_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + DEADLINE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
