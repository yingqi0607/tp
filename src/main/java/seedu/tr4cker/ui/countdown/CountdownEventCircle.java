package seedu.tr4cker.ui.countdown;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.ui.UiPart;

public class CountdownEventCircle extends UiPart<Region> {
    private static final String FXML = "CountdownEventCircle.fxml";

    public final Event event;

    @FXML
    private HBox cardPane;

    @FXML
    private Label eventName;

    @FXML
    private Label daysRemaining;

    /**
     * Creates a {@code CountdownEventCircle} with the given {@code Event} to display.
     */
    public CountdownEventCircle(Event event) {
        super(FXML);
        this.event = event;
        int daysLeft = event.getDaysRemaining();
        if (daysLeft < 0) {
            daysRemaining.setText(" ");
            eventName.setText("No upcoming events!");
        } else {
            daysRemaining.setText(daysLeft + " days!");
            eventName.setText(event.getEventName().eventName);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.tr4cker.ui.countdown.CountdownEventCard)) {
            return false;
        }

        // state check
        seedu.tr4cker.ui.countdown.CountdownEventCircle card = (seedu.tr4cker.ui.countdown.CountdownEventCircle) other;
        return event.equals(card.event);
    }
}
