package seedu.tr4cker.ui.countdown;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.ui.UiPart;

/**
 * Panel containing the two earliest events in Countdown.
 */
public class CountdownEventBadgePanel extends UiPart<Region> {

    private static final String FXML = "CountdownEventBadgePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CountdownEventBadgePanel.class);

    @FXML
    private ListView<Event> eventListView;

    /**
     * Creates a {@code CountdownEventBadgePanel} with the given {@code ObservableList}.
     */
    public CountdownEventBadgePanel(ObservableList<Event> eventList) {
        super(FXML);
        logger.fine("Initialising Countdown Events Badges panel...");
        eventListView.setMaxHeight(400);
        eventListView.setItems(eventList);
        eventListView.setCellFactory(listView -> new EventListViewCell());
        logger.fine("Created Countdown Events Badges panel.");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code CountdownEventBadge}.
     */
    static class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CountdownEventBadge(event).getRoot());
            }
        }
    }

}
