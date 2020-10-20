package seedu.tr4cker.logic.parser;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.temporal.TemporalAdjusters.next;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.TaskDescription;
import seedu.tr4cker.model.util.NaturalDateUtil;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_COMPLETION_STATUS_1 = "1000";
    private static final String INVALID_COMPLETION_STATUS_2 = "-5";
    private static final String INVALID_DEADLINE = "90-90-2021 9999";
    private static final String INVALID_EXPIRED_DEADLINE = "01-01-2020 2359";
    private static final String INVALID_NATURAL_DEADLINE_1 = "Tuesdday";
    private static final String INVALID_NATURAL_DEADLINE_2 = "Tuesday 2500";
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_COMPLETION_STATUS = "50";
    private static final String VALID_DEADLINE_MM = "25-12-2021 2359";
    private static final String VALID_DEADLINE_MMM = "25-Dec-2021 2359";
    private static final String VALID_DEADLINE_NO_TIME_MM = "25-12-2021";
    private static final String VALID_DEADLINE_NO_TIME_MMM = "25-Dec-2021";
    private static final String VALID_DESCRIPTION = "description";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));

    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseCompletionStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCompletionStatus((String) null));
    }

    @Test
    public void parseCompletionStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCompletionStatus(INVALID_COMPLETION_STATUS_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseCompletionStatus(INVALID_COMPLETION_STATUS_2));
    }

    @Test
    public void parseCompletionStatus_validValueWithoutWhitespace_returnsCompletionStatus() throws Exception {
        CompletionStatus expectedCompletionStatus = new CompletionStatus(50);
        assertEquals(expectedCompletionStatus, ParserUtil.parseCompletionStatus(VALID_COMPLETION_STATUS));
    }

    @Test
    public void parseCompletionStatus_validValueWithWhitespace_returnsTrimmedCompletionStatus() throws Exception {
        String completionStatusWithWhitespace = WHITESPACE + VALID_COMPLETION_STATUS + WHITESPACE;
        CompletionStatus expectedCompletionStatus = new CompletionStatus(50);
        assertEquals(expectedCompletionStatus, ParserUtil.parseCompletionStatus(completionStatusWithWhitespace));
    }

    @Test
    public void parseDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadline((String) null));
    }

    @Test
    public void parseDeadline_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_DEADLINE));
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_EXPIRED_DEADLINE));
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_NATURAL_DEADLINE_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_NATURAL_DEADLINE_2));
    }

    @Test
    public void parseDeadline_validValueWithoutWhitespace_returnsDeadline() throws Exception {
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE_MM);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_MM));
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_MMM));
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_NO_TIME_MM));
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_NO_TIME_MMM));

        String expectedDate = LocalDate.now().with(next(FRIDAY))
                .format(NaturalDateUtil.DATE_TIME_FORMATTER);
        expectedDeadline = new Deadline(expectedDate + " 2200");
        assertEquals(expectedDeadline, ParserUtil.parseDeadline("friday 2200"));
        expectedDeadline = new Deadline(expectedDate + " 2359");
        assertEquals(expectedDeadline, ParserUtil.parseDeadline("friday"));

        expectedDeadline = new Deadline(LocalDate.now().format(NaturalDateUtil.DATE_TIME_FORMATTER)
                + " 2359");
        assertEquals(expectedDeadline, ParserUtil.parseDeadline("today"));
    }

    @Test
    public void parseDeadline_validValueWithWhitespace_returnsTrimmedDeadline() throws Exception {
        String deadlineWithWhitespace = WHITESPACE + VALID_DEADLINE_MM + WHITESPACE;
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE_MM);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(deadlineWithWhitespace));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescriptions() throws Exception {
        TaskDescription expectedTaskDescription = new TaskDescription(VALID_DESCRIPTION);
        assertEquals(expectedTaskDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescriptions() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        TaskDescription expectedTaskDescription = new TaskDescription(VALID_DESCRIPTION);
        assertEquals(expectedTaskDescription, ParserUtil.parseDescription(descriptionWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
