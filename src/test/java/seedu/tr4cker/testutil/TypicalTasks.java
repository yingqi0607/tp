package seedu.tr4cker.testutil;

import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_HELP;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_URGENT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TASK1 = new TaskBuilder().withName("Task1Name")
            .withTaskDescription("Task 1 description").withCompletionStatus(0)
            .withDeadline("20-Oct-2021 1800").build();
    public static final Task TASK1_ADD = new TaskBuilder().withName("Task1Name")
            .withTaskDescription("Task 1 description").withCompletionStatus(0)
            .withDeadline("20-Oct-2021 1800")
            .withTags("tag1", "tag2").build();
    public static final Task TASK1_DELETE = new TaskBuilder().withName("Task1Name")
            .withTaskDescription("Task 1 description").withCompletionStatus(0)
            .withDeadline("20-Oct-2021 1800").build();
    public static final Task TASK2 = new TaskBuilder().withName("Task2 Name")
            .withTaskDescription("Task 2 description").withCompletionStatus(0)
            .withDeadline("30-09-2021 2359")
            .withTags("graded", "assignment").build();
    public static final Task TASK3 = new TaskBuilder().withName("Task3 Name")
            .withDeadline("10-03-2021 1200")
            .withCompletionStatus(0).withTaskDescription("Task 3 description").build();
    public static final Task TASK4 = new TaskBuilder().withName("Task4 Name")
            .withDeadline("21-09-2021 1500")
            .withCompletionStatus(0).withTaskDescription("Task 4 description").withTags("friends").build();
    public static final Task TASK5 = new TaskBuilder().withName("Task5 Name")
            .withDeadline("10-10-2021 1010")
            .withCompletionStatus(0).withTaskDescription("Task 5 description").build();
    public static final Task TASK6 = new TaskBuilder().withName("Task6 Name")
            .withDeadline("25-12-2021 0000")
            .withCompletionStatus(0).withTaskDescription("Task 6 description").build();
    public static final Task TASK7 = new TaskBuilder().withName("Task7 Name")
            .withDeadline("01-01-2021 0000")
            .withCompletionStatus(0).withTaskDescription("Task 7 description").build();

    public static final Event EVENT1 = new Event(new EventName("Event1 Name"), new EventDate("01-01-2021"));
    public static final Event EVENT2 = new Event(new EventName("Event2 Name"), new EventDate("10-10-2020"));
    public static final Event EVENT3 = new Event(new EventName("Event3 Name"), new EventDate("30-11-2020"));


    // Manually added
    public static final Task TASK_H = new TaskBuilder().withName("TASK H").withDeadline("12-Jul-2021 0600")
            .withCompletionStatus(0).withTaskDescription("Task H description").build();
    public static final Task TASK_I = new TaskBuilder().withName("TASK I").withDeadline("27-Sep-2021 2359")
            .withCompletionStatus(0).withTaskDescription("Task I description").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task MANUAL_TASK1 = new TaskBuilder().withName(VALID_NAME_1).withDeadline(VALID_DEADLINE_1)
            .withCompletionStatus(VALID_COMPLETION_STATUS_1).withTaskDescription(VALID_DESCRIPTION_1)
            .withTags(VALID_TAG_HELP).build();
    public static final Task MANUAL_TASK2 = new TaskBuilder().withName(VALID_NAME_2).withDeadline(VALID_DEADLINE_2)
            .withCompletionStatus(VALID_COMPLETION_STATUS_2).withTaskDescription(VALID_DESCRIPTION_2)
            .withTags(VALID_TAG_URGENT, VALID_TAG_HELP).build();
    public static final Task MANUAL_TASK_DEFAULT_DEADLINE = new TaskBuilder().withName(VALID_NAME_1)
            .withDeadline(Deadline.DEFAULT_DATE_TODAY + Deadline.DEFAULT_TIME)
            .withCompletionStatus(VALID_COMPLETION_STATUS_1).withTaskDescription(VALID_DESCRIPTION_1)
            .withTags(VALID_TAG_HELP).build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code Tr4cker} with all the typical tasks.
     */
    public static Tr4cker getTypicalTr4cker() {
        Tr4cker tr4cker = new Tr4cker();
        for (Task task : getTypicalTasks()) {
            tr4cker.addTask(task);
        }
        for (Event event : getTypicalEvents()) {
            tr4cker.addEvent(event);
        }
        return tr4cker;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASK1, TASK2, TASK3, TASK4, TASK5, TASK6, TASK7));
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(EVENT1, EVENT2, EVENT3));
    }
}
