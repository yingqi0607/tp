package seedu.tr4cker.model.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static seedu.tr4cker.model.util.SampleDataUtil.getTagSet;
import static seedu.tr4cker.model.util.SampleDataUtil.getModuleCodeSet;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.module.ModuleCode;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.TaskDescription;

import java.util.HashSet;

class SampleDataUtilTest {

    private final Task[] tasks = new Task[] {
        new Task(new Name("Expired Task 1"), new Deadline("10-Oct-2019 1800", false),
               new CompletionStatus(90), new TaskDescription("An expired task"),
               new HashSet<>(), getTagSet("exp")),
        new Task(new Name("Expired Task 2"), new Deadline("10-Oct-2019 1800", false),
               new CompletionStatus(80), new TaskDescription("Another expired task"),
               new HashSet<>(), getTagSet("exp")),
        new Task(new Name("CS1101S Mission"), new Deadline("10-Oct-2021 1800", false),
                new CompletionStatus(100), new TaskDescription("A very fun mission"),
                getModuleCodeSet("CS1101S"), getTagSet("CS1101S", "Mission", "Fun")),
        new Task(new Name("CS1101S Quiz"), new Deadline("11-Sep-2021 2359", false),
                new CompletionStatus(0), new TaskDescription("A quiz that's too hard for me"),
                getModuleCodeSet("CS1101S"), getTagSet("CS1101S", "Quiz", "2Hard4Me")),
        new Task(new Name("CS2100 MidTerms"), new Deadline("12-Oct-2021 1200", false),
                new CompletionStatus(20), new TaskDescription("Need do cheat sheet"),
                getModuleCodeSet("CS2100"), getTagSet("cheatSheetsFTW")),
        new Task(new Name("CS2103T Project"), new Deadline("13-Sep-2021 1500", false),
                new CompletionStatus(50), new TaskDescription("I love project work"),
                getModuleCodeSet("CS2103T"), getTagSet("CS2103T", "EthanSGKK")),
        new Task(new Name("CS2101 OP2"), new Deadline("14-Apr-2021 1010", false),
                new CompletionStatus(0), new TaskDescription("All the best Good Luck"),
                getModuleCodeSet("CS2101"), getTagSet("CS2101", "20Percent")),
        new Task(new Name("CS1231S Graded Assignment"), new Deadline("25-Dec-2021 0000", false),
                new CompletionStatus(1), new TaskDescription("JustDueet"),
                getModuleCodeSet("CS1231S"), getTagSet("CS1231S", "Assignment"))
    };

    private final Module[] modules = new Module[] {
            new Module("Programming Methodology", new ModuleCode("CS1101S")),
            new Module("Computer Organization", new ModuleCode("CS2100")),
            new Module("Software Engineering", new ModuleCode("CS2103T")),
            new Module("Effective Communication for Computing Professionals",
                    new ModuleCode("CS2101")),
            new Module("Discrete Structures", new ModuleCode("CS1231S"))
    };

    @Test
    public void testGetSampleData() {
        assertArrayEquals(SampleDataUtil.getSampleTasks(), tasks);
    }

    @Test
    public void testGetSampleModules() {
        assertArrayEquals(SampleDataUtil.getSampleModules(), modules);
    }

}
