package seedu.tr4cker.ui.countdown;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.ui.UiPart;

/**
 * A UI component that displays the earliest upcoming events in Countdowns.
 */
public class CountdownEventBadge extends UiPart<Region> {

    private static final String FXML = "CountdownEventBadge.fxml";
    private final Logger logger = LogsCenter.getLogger(seedu.tr4cker.ui.countdown.CountdownEventBadge.class);

    private final Event event;

    @javafx.fxml.FXML
    private Circle circle;

    @FXML
    private VBox cardPane;

    @FXML
    private Label daysRemaining;

    @FXML
    private Label eventName;

    /**
     * Creates a {@code CountdownEventBadge} with the given {@code Event} to display.
     */
    public CountdownEventBadge(Event event) {
        super(FXML);
        logger.fine("Initialising countdownEventBadge");
        this.event = event;
        eventName.setText(event.getEventName().eventName);
        daysRemaining.setText(String.valueOf(event.getDaysRemaining()) + " days!");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.tr4cker.ui.countdown.CountdownEventBadge)) {
            return false;
        }

        // state check
        seedu.tr4cker.ui.countdown.CountdownEventBadge badge =
                (seedu.tr4cker.ui.countdown.CountdownEventBadge) other;
        return event.equals(badge.event);
    }
}
