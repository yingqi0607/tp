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
            new Task(new Name("CS1101S Mission"), new Deadline("2020-10-20 1800"), new CompletionStatus(100),
                new TaskDescription("A very fun mission"),
                getTagSet("CS1101S", "Mission", "Fun")),
            new Task(new Name("CS1101S Quiz"), new Deadline("2020-09-30 2359"), new CompletionStatus(0),
                new TaskDescription("A quiz that's too hard for me"),
                getTagSet("CS1101S", "Quiz", "2Hard4Me")),
            new Task(new Name("CS2100 MidTerms"), new Deadline("2020-10-03 1200"), new CompletionStatus(20),
                new TaskDescription("Need do cheat sheet"),
                getTagSet("cheatSheetsFTW")),
            new Task(new Name("CS2103T Project"), new Deadline("2020-09-21 1500"), new CompletionStatus(50),
                new TaskDescription("I love project work"),
                getTagSet("CS2103T", "EthanSGKK")),
            new Task(new Name("CS2101 OP2"), new Deadline("2020-10-10 1010"), new CompletionStatus(0),
                new TaskDescription("All the best Good Luck"),
                getTagSet("CS2101", "20Percent")),
            new Task(new Name("CS1231S Graded Assignment"), new Deadline("2020-12-25 0000"), new CompletionStatus(1),
                new TaskDescription("JustDueet"),
                getTagSet("CS1231S", "Assignment"))
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
