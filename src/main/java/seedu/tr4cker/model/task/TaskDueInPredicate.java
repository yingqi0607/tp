package seedu.tr4cker.model.task;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.function.Predicate;

public class TaskDueInPredicate implements Predicate<Task> {

    private final LocalDate dueDate;

    public TaskDueInPredicate() {
        this.dueDate = LocalDate.now();
    }

    public TaskDueInPredicate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskDueInPredicate(YearMonth month) {
        this.dueDate = month.atDay(1);
    }

    @Override
    public boolean test(Task task) {
        LocalDate taskDate = task.getDeadline().getDateTime().toLocalDate();
        return taskDate.isEqual(dueDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDueInPredicate) // instanceof handles null
                && this.dueDate.equals(((TaskDueInPredicate) other).dueDate);
    }

}
