//package seedu.tr4cker.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.tr4cker.logic.commands.CommandTestUtil.showTaskAtIndex;
//import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;
//import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_SECOND_TASK;
//import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.tr4cker.commons.core.Messages;
//import seedu.tr4cker.commons.core.index.Index;
//import seedu.tr4cker.model.Model;
//import seedu.tr4cker.model.ModelManager;
//import seedu.tr4cker.model.UserPrefs;
//import seedu.tr4cker.model.task.CompletionStatus;
//import seedu.tr4cker.model.task.NameContainsKeywordsPredicate;
//import seedu.tr4cker.model.task.Task;
//
//import java.util.Collections;
//
///**
// * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
// * {@code DeleteCommand}.
// */
//public class DoneCommandTest {
//
//    private final Model model = new ModelManager(getTypicalTr4cker(), new UserPrefs());
//
//    @Test
//    public void execute_percentageIncreasesUnfilteredList_success() {
//        Task taskToComplete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
//        CompletionStatus percentage = new CompletionStatus(75);
//        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK, percentage);
//
//        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS_INCREASE, taskToComplete);
//
//        ModelManager expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
//        taskToComplete.
//        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);
//
//
//        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_validIndexUnfilteredList_success() {
//        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
//        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK, add, delete);
//
//        String expectedMessage = String.format(TagCommand.MESSAGE_SUCCESS, taskToEdit);
//
//        ModelManager expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
//        taskToEdit.addTags(add);
//        taskToEdit.deleteTags(delete);
//
//        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
//        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
//
//        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void execute_validIndexFilteredList_success() {
//        showTaskAtIndex(model, INDEX_FIRST_TASK);
//
//        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
//        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_TASK);
//
//        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);
//
//        Model expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
//        expectedModel.deleteTask(taskToDelete);
//        showNoTask(expectedModel);
//
//        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_invalidIndexFilteredList_throwsCommandException() {
//        showTaskAtIndex(model, INDEX_FIRST_TASK);
//
//        Index outOfBoundIndex = INDEX_SECOND_TASK;
//        // ensures that outOfBoundIndex is still in bounds of Tr4cker list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getTr4cker().getTaskList().size());
//
//        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex, );
//
//        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        CompletionStatus firstCompletionStatus = new CompletionStatus(66);
//        CompletionStatus secondCompletionStatus = new CompletionStatus(75);
//
//        DoneCommand doneFirstCommand = new DoneCommand(INDEX_FIRST_TASK, firstCompletionStatus);
//        DoneCommand doneSecondCommand = new DoneCommand(INDEX_SECOND_TASK, secondCompletionStatus);
//
//        // same object -> returns true
//        assertTrue(doneFirstCommand.equals(doneFirstCommand));
//
//        // same values -> returns true
//        DoneCommand doneFirstCommandCopy = new DoneCommand(INDEX_FIRST_TASK, firstCompletionStatus);
//        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(doneFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(doneFirstCommand.equals(null));
//
//        // different task -> returns false
//        assertFalse(doneFirstCommand.equals(doneSecondCommand));
//    }
//}
