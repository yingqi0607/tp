package seedu.tr4cker.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.Address;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline II";
    public static final String DEFAULT_DEADLINE = "2020-12-31 2359";
    public static final int DEFAULT_COMPLETION_STATUS = 0;
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Deadline deadline;
    private CompletionStatus completionStatus;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new Name(DEFAULT_NAME);
        deadline = new Deadline(DEFAULT_DEADLINE);
        completionStatus = new CompletionStatus(DEFAULT_COMPLETION_STATUS);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        deadline = taskToCopy.getDeadline();
        completionStatus = taskToCopy.getCompletionStatus();
        address = taskToCopy.getAddress();
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
     * Sets the {@code Address} of the {@code Task} that we are building.
     */
    public TaskBuilder withAddress(String address) {
        this.address = new Address(address);
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
        return new Task(name, deadline, completionStatus, address, tags);
    }

}
