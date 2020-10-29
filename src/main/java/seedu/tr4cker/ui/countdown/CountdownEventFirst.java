package seedu.tr4cker.ui.countdown;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Region;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;
import seedu.tr4cker.ui.UiPart;

import java.util.logging.Logger;

/** UI Component to display the first event in Countdown events list. */
public class CountdownEventFirst extends UiPart<Region> {

    private static final String FXML = "CountdownEventFirst.fxml";
    private final Logger logger = LogsCenter.getLogger(CountdownEventFirst.class);

    private final ObservableValue<Event> event;

    @FXML
    private ListCell<ObservableValue<Event>> eventListCell;

    /**
     * Creates a {@code CountdownEventFirst} with the given {@code CountdownEventFirst}.
     */
    public CountdownEventFirst(ObservableValue<Event> eventObservableValue) {
        super(FXML);
        logger.fine("Initializing Countdown First Event display...");
        this.event = eventObservableValue;
        eventListCell.setItem(eventObservableValue);
        eventListCell.setGraphic(new EventListViewCell());
        logger.fine("Created Countdown First Event display...");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code CountdownEventCircle}.
     */
    static class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty) {
                setGraphic(null);
                setText(null);
            } else if (event == null) {
                setGraphic(new CountdownEventCircle(new Event(new EventName("null"),
                        new EventDate("01-01-2019", false))).getRoot());
            } else {
                setGraphic(new CountdownEventCircle(event).getRoot());
            }
        }
    }

}