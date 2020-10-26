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
 * A UI component that displays the second earliest upcoming event in Countdowns.
 */
public class CountdownSecondEventBadge extends UiPart<Region> {

    private static final String FXML = "CountdownEventBadge.fxml";
    private final Logger logger = LogsCenter.getLogger(seedu.tr4cker.ui.countdown.CountdownSecondEventBadge.class);

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
     * Creates a {@code CountdownFirstEventBadge} with the given {@code Event} to display.
     */
    public CountdownSecondEventBadge(Event event) {
        super(FXML);
        logger.fine("Initialising countdownSecondEventBadge");
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
        if (!(other instanceof seedu.tr4cker.ui.countdown.CountdownSecondEventBadge)) {
            return false;
        }

        // state check
        seedu.tr4cker.ui.countdown.CountdownSecondEventBadge badge =
                (seedu.tr4cker.ui.countdown.CountdownSecondEventBadge) other;
        return event.equals(badge.event);
    }
}
