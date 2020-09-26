package seedu.tr4cker.testutil;

import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TASK1 = new TaskBuilder().withName("Task1 Name")
            .withTaskDescription("Task 1 description").withCompletionStatus(0)
            .withDeadline("2020-10-20 1800")
            .withTags("friends").build();
    public static final Task TASK2 = new TaskBuilder().withName("Task2 Meier")
            .withTaskDescription("Task 2 description")
            .withCompletionStatus(0).withDeadline("2020-09-30 2359")
            .withTags("owesMoney", "friends").build();
    public static final Task TASK3 = new TaskBuilder().withName("Task3 Name").withDeadline("2020-10-03 1200")
            .withCompletionStatus(0).withTaskDescription("Task 3 description").build();
    public static final Task TASK4 = new TaskBuilder().withName("Task4 Meier").withDeadline("2020-09-21 1500")
            .withCompletionStatus(0).withTaskDescription("Task 4 description").withTags("friends").build();
    public static final Task TASK5 = new TaskBuilder().withName("Task5 Name").withDeadline("2020-10-10 1010")
            .withCompletionStatus(0).withTaskDescription("Task 5 description").build();
    public static final Task TASK6 = new TaskBuilder().withName("Task6 Name").withDeadline("2020-12-25 0000")
            .withCompletionStatus(0).withTaskDescription("Task 6 description").build();
    public static final Task GEORGE = new TaskBuilder().withName("Task7 Name").withDeadline("2021-01-01 0000")
            .withCompletionStatus(0).withTaskDescription("Task 7 description").build();

    // Manually added
    public static final Task TASK_H = new TaskBuilder().withName("Hoon Meier").withDeadline("2020-12-07 0600")
            .withCompletionStatus(0).withTaskDescription("Task 8 description").build();
    public static final Task TASK_I = new TaskBuilder().withName("Ida Mueller").withDeadline("2020-09-27 2359")
            .withCompletionStatus(0).withTaskDescription("Task 9 description").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task MANUALTASK1 = new TaskBuilder().withName(VALID_NAME_1).withDeadline(VALID_DEADLINE_1)
            .withCompletionStatus(VALID_COMPLETION_STATUS_1).withTaskDescription(VALID_DESCRIPTION_1)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Task MANUALTASK2 = new TaskBuilder().withName(VALID_NAME_2).withDeadline(VALID_DEADLINE_2)
            .withCompletionStatus(VALID_COMPLETION_STATUS_2).withTaskDescription(VALID_DESCRIPTION_2)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code Tr4cker} with all the typical tasks.
     */
    public static Tr4cker getTypicalTr4cker() {
        Tr4cker ab = new Tr4cker();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASK1, TASK2, TASK3, TASK4, TASK5, TASK6, GEORGE));
    }
}
