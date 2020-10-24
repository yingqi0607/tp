package seedu.tr4cker.model.module;

import org.junit.jupiter.api.Test;
import seedu.tr4cker.model.task.Name;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalTasks.MANUAL_TASK2;
import static seedu.tr4cker.testutil.TypicalTasks.TASK1;

class ModuleTest {

    private static Module EXAMPLE_MODULE =
            new Module("NAME1", new ModuleCode("CODE1"));
    private static Module EXAMPLE_MODULE_COPY =
            new Module("NAME1", new ModuleCode("CODE1"));
    private static Module MODULE_DIFF_CODE =
            new Module("NAME1", new ModuleCode("CODE2"));
    private static Module MODULE_DIFF_NAME =
            new Module("NAME2", new ModuleCode("CODE1"));

    @Test
    void isValidModuleName() {
        // null name
        assertThrows(NullPointerException.class, () -> Module.isValidModuleName(null));

        // invalid name
        assertFalse(Module.isValidModuleName("@@@")); // non-alphanumeric
        assertFalse(Module.isValidModuleName("      ")); // spaces
        assertFalse(Module.isValidModuleName("")); // empty string

        // valid name
        assertTrue(Module.isValidModuleName("graded assignment")); // alphabets only
        assertTrue(Module.isValidModuleName("12345")); // numbers only
        assertTrue(Module.isValidModuleName("assignment 1")); // alphanumeric characters
        assertTrue(Module.isValidModuleName("Homework Assignment")); // with capital letters
        assertTrue(Module.isValidModuleName("A Very Long name for my Homework")); // long names
    }

    @Test
    void isSameModule() {
        // same object -> returns true
        assertTrue(EXAMPLE_MODULE.isSameModule(EXAMPLE_MODULE));

        // null -> returns false
        assertFalse(EXAMPLE_MODULE.isSameModule(null));

        // different module code -> returns false
        assertFalse(EXAMPLE_MODULE.isSameModule(MODULE_DIFF_CODE));

        // same code different name -> returns true
        assertTrue(EXAMPLE_MODULE.isSameModule(MODULE_DIFF_NAME));
    }

    @Test
    void testEquals() {
        // same values -> returns true
        assertTrue(EXAMPLE_MODULE.equals(EXAMPLE_MODULE_COPY));

        // same object -> returns true
        assertTrue(EXAMPLE_MODULE.equals(EXAMPLE_MODULE));

        // null -> returns false
        assertFalse(EXAMPLE_MODULE.equals(null));

        // different type -> returns false
        assertFalse(EXAMPLE_MODULE.equals(5));

        // different name -> returns false
        assertFalse(EXAMPLE_MODULE.equals(MODULE_DIFF_NAME));

        // different code -> returns false
        assertFalse(EXAMPLE_MODULE.equals(MODULE_DIFF_CODE));
    }

    @Test
    void testHashCode() {
        String moduleName = "name";
        ModuleCode moduleCode = new ModuleCode("code");
        Module module1 = new Module(moduleName, moduleCode);
        Module module2 = new Module(moduleName, moduleCode);
        assertEquals(module1.hashCode(), module2.hashCode());
    }
}