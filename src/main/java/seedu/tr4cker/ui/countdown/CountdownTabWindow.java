package seedu.tr4cker.ui.countdown;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.logic.Logic;
import seedu.tr4cker.ui.UiPart;
import seedu.tr4cker.ui.planner.PlannerCalendarPanel;

/**
 * An UI component that displays Countdown tab.
 */
public class CountdownTabWindow extends UiPart<Region> {

    private static final String FXML = "CountdownTabWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(PlannerCalendarPanel.class);
    private final CountdownEventListPanel countdownEventsListPanel;
    private final CountdownFirstEventBadge countdownFirstEventBadge;
    private final CountdownSecondEventBadge countdownSecondEventBadge;

    @FXML
    private StackPane countdownListPanelPlaceholder;

    @FXML
    private StackPane countdownFirstEventBadgePlaceholder;
    @FXML
    private StackPane countdownSecondEventBadgePlaceholder;
    /**
     * Constructor for CountdownTabWindow.
     *
     * @param logic Logic of TR4CKER.
     */
    public CountdownTabWindow(Logic logic) {
        super(FXML);
        logger.fine("Initialising Countdown Tab Window...");
        this.countdownEventsListPanel = new CountdownEventListPanel(logic.getFilteredEventList());
        countdownListPanelPlaceholder.getChildren().add(countdownEventsListPanel.getRoot());
        this.countdownFirstEventBadge = new CountdownFirstEventBadge(logic.getFirstEvent());
        countdownFirstEventBadgePlaceholder.getChildren().add(countdownFirstEventBadge.getRoot());
        this.countdownSecondEventBadge = new CountdownSecondEventBadge(logic.getSecondEvent());
        countdownFirstEventBadgePlaceholder.getChildren().add(countdownSecondEventBadge.getRoot());
        logger.fine("Created Countdown Tab Window.");
    }
}
