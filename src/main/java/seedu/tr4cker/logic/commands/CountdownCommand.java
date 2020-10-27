package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;

import java.util.List;
import java.util.Objects;

/**
 * Allows user to go to Countdown tab.
 */
public class CountdownCommand extends Command {

    public static final String COMMAND_WORD = "countdown";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to Countdown tab\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SWITCH_TAB_SUCCESS = "Switched to Countdown tab!";

    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted Event from Countdowns: %1$s";

    public static final String MESSAGE_ADD_EVENT_SUCCESS = "New Event added to Countdowns added: %1$s";

    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in TR4CKER.";

    private final EventName eventName;
    private final EventDate eventDate;
    private final Index index;
    private final boolean isDeleteCountdown;

    /**
     * Constructor for CountdownCommand when user wants to switch to Countdown tab.
     */
    public CountdownCommand() {
        this.eventName = null;
        this.eventDate = null;
        this.index = null;
        this.isDeleteCountdown = false;
    }

    /**
     * Constructor for CountdownCommand when User wants to add an event.
     */
    public CountdownCommand(EventName eventName, EventDate eventDate) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.index = null;
        this.isDeleteCountdown = false;
    }

    /**
     * Constructor for CountdownCommand when user wants to delete an event,
     * or add an event from tasks list.
     */
    public CountdownCommand(Index index, boolean isDeleteCountdown) {
        this.eventName = null;
        this.eventDate = null;
        this.index = index;
        this.isDeleteCountdown = isDeleteCountdown;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> eventList = model.getFilteredEventList();

        if (Objects.isNull(eventName) && Objects.isNull(eventDate) && Objects.isNull(index)) {
            model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
            return new CommandResult(MESSAGE_SWITCH_TAB_SUCCESS, true);
        }

        if (Objects.isNull(eventName) && Objects.isNull(eventDate)) {
            if (isDeleteCountdown) {
                return executeCountdownDelete(model, eventList);
            } else {
                //TODO: add conversion method in tasks
                return new CommandResult(MESSAGE_SWITCH_TAB_SUCCESS, true);
            }
        }

        if (Objects.isNull(index)) {
            return executeCountdownAdd(model);
        }

        throw new CommandException(MESSAGE_INVALID_COMMAND_FORMAT);
    }

    private CommandResult executeCountdownAdd(Model model) throws CommandException {
        Event eventToAdd = new Event(eventName, eventDate);
        if (model.hasEvent(eventToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }
        model.addEvent(eventToAdd);
        return new CommandResult(String.format(MESSAGE_ADD_EVENT_SUCCESS, eventToAdd));
    }

    private CommandResult executeCountdownDelete(Model model, List<Event> eventList) throws CommandException {
        if (index.getZeroBased() >= eventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }
        Event eventToDelete = eventList.get(index.getZeroBased());
        model.deleteEvent(eventToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { // short circuit if same object
            return true;
        } else if (!(other instanceof CountdownCommand)) {
            return false;
        } else {
            return false; // to be implemented
        }
    }
}
