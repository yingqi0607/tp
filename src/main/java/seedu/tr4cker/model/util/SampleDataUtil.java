package seedu.tr4cker.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.TaskDescription;

/**
 * Contains utility methods for populating {@code Tr4cker} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Name("Expired Task 1"), new Deadline("10-Oct-2019 1800", false),
                    new CompletionStatus(90), new TaskDescription("An expired task"),
                    null, getTagSet("exp")),
            new Task(new Name("Expired Task 2"), new Deadline("10-Oct-2019 1800", false),
                    new CompletionStatus(80), new TaskDescription("Another expired task"),
                    null, getTagSet("exp")),
            new Task(new Name("CS1101S Mission"), new Deadline("10-Oct-2021 1800", false),
                    new CompletionStatus(100), new TaskDescription("A very fun mission"),
                    null, getTagSet("CS1101S", "Mission", "Fun")),
            new Task(new Name("CS1101S Quiz"), new Deadline("11-Sep-2021 2359", false),
                    new CompletionStatus(0), new TaskDescription("A quiz that's too hard for me"),
                    null, getTagSet("CS1101S", "Quiz", "2Hard4Me")),
            new Task(new Name("CS2100 MidTerms"), new Deadline("12-Oct-2021 1200", false),
                    new CompletionStatus(20), new TaskDescription("Need do cheat sheet"),
                    null, getTagSet("cheatSheetsFTW")),
            new Task(new Name("CS2103T Project"), new Deadline("13-Sep-2021 1500", false),
                    new CompletionStatus(50), new TaskDescription("I love project work"),
                    null, getTagSet("CS2103T", "EthanSGKK")),
            new Task(new Name("CS2101 OP2"), new Deadline("14-Apr-2021 1010", false),
                    new CompletionStatus(0), new TaskDescription("All the best Good Luck"),
                    null, getTagSet("CS2101", "20Percent")),
            new Task(new Name("CS1231S Graded Assignment"), new Deadline("25-Dec-2021 0000", false),
                    new CompletionStatus(1), new TaskDescription("JustDueet"),
                    null, getTagSet("CS1231S", "Assignment"))
        };
    }

    public static ReadOnlyTr4cker getSampleTr4cker() {
        Tr4cker sampleAb = new Tr4cker();
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
