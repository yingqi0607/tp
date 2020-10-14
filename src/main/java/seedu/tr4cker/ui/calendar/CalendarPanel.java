package seedu.tr4cker.ui.calendar;

import javafx.scene.layout.Region;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.ui.UiPart;

import java.util.logging.Logger;

public class CalendarPanel extends UiPart<Region>  {
    private static final String FXML = "CalendarPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);

    /**
     * Creates a {@code CalendarPanel}.
     */
    public CalendarPanel() {
        super(FXML);
    }

}
