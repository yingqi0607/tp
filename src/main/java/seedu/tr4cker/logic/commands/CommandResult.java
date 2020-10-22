package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private final LocalDate localDate;
    private final YearMonth yearMonth;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** TR4CKER shows Planner tab. */
    private boolean showPlanner;

    /** TR4CKER shows Countdown tab. */
    private boolean showCountdown;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.localDate = null;
        this.yearMonth = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * {@code localDate} and {@code yearMonth}. For usage of PlannerCommand.
     */
    public CommandResult(String feedbackToUser, LocalDate localDate, YearMonth yearMonth) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.showPlanner = true;
        this.showCountdown = false;
        this.localDate = localDate;
        this.yearMonth = yearMonth;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}.
     * For usage of CountdownCommand.
     */
    public CommandResult(String feedbackToUser, boolean isShowCountdown) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.showPlanner = false;
        this.showCountdown = isShowCountdown;
        this.localDate = null;
        this.yearMonth = null;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowPlanner() {
        return showPlanner;
    }

    public boolean isShowCountdown() {
        return showCountdown;
    }

    public LocalDate getLocalDate() {
        return this.localDate;
    }

    public YearMonth getYearMonth() {
        return this.yearMonth;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
