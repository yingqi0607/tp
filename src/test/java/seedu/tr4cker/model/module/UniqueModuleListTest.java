package seedu.tr4cker.model.module;

import org.junit.jupiter.api.Test;
import seedu.tr4cker.model.module.exceptions.DuplicateModuleException;
import seedu.tr4cker.model.module.exceptions.ModuleNotFoundException;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.UniqueTaskList;
import seedu.tr4cker.model.task.exceptions.DuplicateTaskException;
import seedu.tr4cker.model.task.exceptions.TaskNotFoundException;
import seedu.tr4cker.testutil.TaskBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalTasks.MANUAL_TASK2;
import static seedu.tr4cker.testutil.TypicalTasks.TASK1;

class UniqueModuleListTest {

    private final UniqueModuleList uniqueModuleList = new UniqueModuleList();
    private final Module module1 = new Module("name1", new ModuleCode("code1"));
    private final Module module2 = new Module("name2", new ModuleCode("code2"));

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.contains(null));
    }

    @Test
    public void contains_moduleNotInList_returnsFalse() {
        assertFalse(uniqueModuleList.contains(module1));
    }

    @Test
    public void contains_moduleInList_returnsTrue() {
        uniqueModuleList.add(module1);
        assertTrue(uniqueModuleList.contains(module1));
    }

    @Test
    public void contains_moduleWithSameCode_returnsTrue() {
        uniqueModuleList.add(module1);
        Module sameCodeModule = new Module("asdf", new ModuleCode("code1"));
        assertTrue(uniqueModuleList.contains(sameCodeModule));
    }

    @Test
    public void add_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.add(null));
    }

    @Test
    public void add_duplicateModule_throwsDuplicateModuleException() {
        uniqueModuleList.add(module1);
        assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.add(module1));
    }

    @Test
    public void remove_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.remove(null));
    }

    @Test
    public void remove_moduleDoesNotExist_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.remove(module1));
    }

    @Test
    public void remove_existingModule_removesModule() {
        uniqueModuleList.add(module1);
        uniqueModuleList.remove(module1);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullUniqueModuleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((UniqueModuleList) null));
    }

    @Test
    public void setModules_uniqueModuleList_replacesOwnListWithProvidedUniqueModuleList() {
        uniqueModuleList.add(module1);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(module2);
        uniqueModuleList.setModules(expectedUniqueModuleList);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((List<Module>) null));
    }

    @Test
    public void setModules_list_replacesOwnListWithProvidedList() {
        uniqueModuleList.add(module1);
        List<Module> moduleList = Collections.singletonList(module2);
        uniqueModuleList.setModules(moduleList);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(module2);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_listWithDuplicateTasks_throwsDuplicateModuleException() {
        List<Module> listWithDuplicateModules = Arrays.asList(module1, module1);
        assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.setModules(listWithDuplicateModules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueModuleList.asUnmodifiableObservableList().remove(0));
    }
}