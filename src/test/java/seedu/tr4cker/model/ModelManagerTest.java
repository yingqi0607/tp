package seedu.tr4cker.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalTasks.TASK1;
import static seedu.tr4cker.testutil.TypicalTasks.TASK2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.core.GuiSettings;
import seedu.tr4cker.model.task.NameContainsKeywordsPredicate;
import seedu.tr4cker.testutil.Tr4ckerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Tr4cker(), new Tr4cker(modelManager.getTr4cker()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTr4ckerFilePath(Paths.get("tr4cker/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTr4ckerFilePath(Paths.get("new/tr4cker/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setTr4ckerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTr4ckerFilePath(null));
    }

    @Test
    public void setTr4ckerFilePath_validPath_setsTr4ckerFilePath() {
        Path path = Paths.get("tr4cker/book/file/path");
        modelManager.setTr4ckerFilePath(path);
        assertEquals(path, modelManager.getTr4ckerFilePath());
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTr4cker_returnsFalse() {
        assertFalse(modelManager.hasTask(TASK1));
    }

    @Test
    public void hasTask_taskInTr4cker_returnsTrue() {
        modelManager.addTask(TASK1);
        assertTrue(modelManager.hasTask(TASK1));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void equals() {
        Tr4cker tr4cker = new Tr4ckerBuilder().withTask(TASK1).withTask(TASK2).build();
        Tr4cker differentTr4cker = new Tr4cker();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(tr4cker, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(tr4cker, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different tr4cker -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTr4cker, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = TASK1.getName().taskName.split("\\s+");
        modelManager.updateFilteredTaskList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        System.out.println(modelManager.getFilteredTaskList());
        ModelManager newModelManager = new ModelManager(tr4cker, userPrefs);
        assertFalse(modelManager.equals(newModelManager));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTr4ckerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(tr4cker, differentUserPrefs)));
    }
}
