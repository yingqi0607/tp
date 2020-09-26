package seedu.tr4cker.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.TaskDescription;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;

/**
 * Contains utility methods for populating {@code Tr4cker} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Name("Alex Yeoh"), new Deadline("2020-10-20 1800"), new CompletionStatus(100),
                new TaskDescription("Task 1 description"),
                getTagSet("friends")),
            new Task(new Name("Bernice Yu"), new Deadline("2020-09-30 2359"), new CompletionStatus(0),
                new TaskDescription("Task 2 description"),
                getTagSet("colleagues", "friends")),
            new Task(new Name("Charlotte Oliveiro"), new Deadline("2020-10-03 1200"), new CompletionStatus(20),
                new TaskDescription("Task 3 description"),
                getTagSet("neighbours")),
            new Task(new Name("David Li"), new Deadline("2020-09-21 1500"), new CompletionStatus(50),
                new TaskDescription("Task 4 description"),
                getTagSet("family")),
            new Task(new Name("Irfan Ibrahim"), new Deadline("2020-10-10 1010"), new CompletionStatus(0),
                new TaskDescription("Task 5 description"),
                getTagSet("classmates")),
            new Task(new Name("Roy Balakrishnan"), new Deadline("2020-12-25 0000"), new CompletionStatus(1),
                new TaskDescription("Task 6 description"),
                getTagSet("colleagues"))
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
