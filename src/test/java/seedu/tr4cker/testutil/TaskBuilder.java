package seedu.tr4cker.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.TaskDescription;
import seedu.tr4cker.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline II";
    public static final String DEFAULT_DEADLINE = "2021-12-31 2359";
    public static final int DEFAULT_COMPLETION_STATUS = 0;
    public static final String DEFAULT_DESCRIPTION = "description";

    private Name name;
    private Deadline deadline;
    private CompletionStatus completionStatus;
    private TaskDescription taskDescription;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new Name(DEFAULT_NAME);
        deadline = new Deadline(DEFAULT_DEADLINE);
        completionStatus = new CompletionStatus(DEFAULT_COMPLETION_STATUS);
        taskDescription = new TaskDescription(DEFAULT_DESCRIPTION);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        deadline = taskToCopy.getDeadline();
        completionStatus = taskToCopy.getCompletionStatus();
        taskDescription = taskToCopy.getTaskDescription();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskDescription(String description) {
        this.taskDescription = new TaskDescription(description);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code CompletionStatus} of the {@code Task} that we are building.
     * @param completionStatus
     */
    public TaskBuilder withCompletionStatus(int completionStatus) {
        this.completionStatus = new CompletionStatus(completionStatus);
        return this;
    }

    public Task build() {
        return new Task(name, deadline, completionStatus, taskDescription, tags);
    }

}
