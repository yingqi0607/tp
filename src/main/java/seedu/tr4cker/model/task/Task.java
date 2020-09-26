package seedu.tr4cker.model.task;

import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.tr4cker.model.tag.Tag;

/**
 * Represents a Task in Tr4cker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Name name;
    private final Deadline deadline;
    private final CompletionStatus completionStatus;

    // Data fields
    private final TaskDescription taskDescription;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Deadline deadline, CompletionStatus completionStatus,
                TaskDescription taskDescription, Set<Tag> tags) {
        requireAllNonNull(name, deadline, completionStatus, taskDescription, tags);
        this.name = name;
        this.deadline = deadline;
        this.completionStatus = completionStatus;
        this.taskDescription = taskDescription;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    public TaskDescription getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both tasks of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName())
                && (otherTask.getDeadline().equals(getDeadline()));
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getName().equals(getName())
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getCompletionStatus().equals(getCompletionStatus())
                && otherTask.getTaskDescription().equals(getTaskDescription())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, deadline, completionStatus, taskDescription, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Deadline: ")
                .append(getDeadline())
                .append(" CompletionStatus: ")
                .append(getCompletionStatus())
                .append(" Address: ")
                .append(getTaskDescription())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
