---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of TR4CKER. Given below is a quick overview
of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the
[diagrams](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/docs/diagrams/) folder. Refer to the
[_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create
and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/Main.java)
and [`MainApp`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/MainApp.java).
It is responsible for,
* At TR4CKER launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of TR4CKER consists of four components.

* [**`UI`**](#ui-component): The UI of TR4CKER.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of TR4CKER in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API
`interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and
exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `TaskListPanel`,
`StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are
in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/ui/MainWindow.java)
is specified in [`MainWindow.fxml`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/logic/Logic.java)

1. `Logic` uses the `Tr4ckerParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should
end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the TR4CKER data.
* exposes an unmodifiable `ObservableList<Task>` that can be 'observed' e.g. the UI can be bound to this list so that
the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model
is given below. It has a `Tag` list in the `Tr4cker`, which `Task` references. This allows `Tr4cker` to only require one
`Tag` object per unique `Tag`, instead of each `Task` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>

### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/storage/Storage.java)

The `Storage` component:
* can save `UserPref` objects in json format and read it back
* can save TR4CKER data in json format and read it back

### Common classes

Classes used by multiple components are in the `seedu.tr4cker.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Categorised Tasks Handling feature in Home tab \[coming in v1.3] (Han Wei)

### Feature introduction
This feature allows users to view and handle tasks under 3 categories separately.

### Implementation details
This feature comes in the form of a Task List panel, which is made up of three sub-panels:
1. Pending tasks (Incomplete tasks that are not overdue)
2. Expired tasks (Incomplete tasks that are overdue)
3. Completed tasks (Archived tasks)

Upon starting TR4CKER or refreshing of task lists, tasks which deadlines have passed will automatically appear under Expired tasks panel.
Users can modify tasks displayed in the Expired task panel using the Edit command.

The following diagram shows the sequence flow of a EditCommand which modifies the deadline of a task in the Expired task list:
![EditActivityDiagram](images/EditActivityDiagram.png)
Figure 1: Edit deadline of expired task Activity Diagram

Once the user marks a task as complete, it will automatically appear under Completed tasks.

### Design considerations:

#### Aspect 1: How users can easily view and control all the tasks
This design filters the tasks into 3 lists according to their completion statuses and deadlines which will be useful to the users,
as opposed to having browse through a long task list.

### Planner feature \[coming in v1.3] (Rui Ling)
TR4CKER has a planner feature which provides users to view the calendar side-by-side with the tasks that are due on
specified day. This feature is to allow users to have a clearer view of their schedules and allow them to plan their
time ahead, and hence increasing productivity.

The 2 main functions of Planner command are to:
1. Switch to planner tab
2. Display an overview of tasks for today/tomorrow and on specified date/month

#### Implementation
To implement the UI of this planner feature, there is a `planner` package in `model` and `ui` packages. To implement
the commands of this planner feature, there are `PlannerCommand` and `PlannerCommandParser` classes in `logic` package.
The following class diagram (Figure 1) summarises how the UI aspect of this planner feature is being implemented:
![PlannerClassDiagram](images/PlannerClassDiagram.png)
Figure 1: Planner Class Diagram

During the initialisation of TR4CKER, `PlannerTabWindow` will be initialised, together with `PlannerCalendarPanel` and
`TaskListPanel`. `PlannerTabWindow` will execute `Logic#getPlannerFilteredTaskList` to update the tasks list shown in
Planner tab. The month and year of the calendar will be set in `PlannerCalendarPanel`. There are multiple `PlannerDayCard`
in 1 `PlannerCalendarPanel`. `PlannerDayCard` serves to store the details of each `PlannerDay`, which contains the date
of each grid in the `PlannerCalendarPanel`. When users execute planner commands, The month and year of the calendar will
be updated in `PlannerCalendarPanel`. Existing details of the calendar will also be cleared through
`PlannerCalendarPanel#clearCalendar()` and `PlannerDayCard#clear()`. At the same time, the tasks list will also be updated.

The following sequence diagram (Figure 2) shows how the planner feature works when a user executes `planner goto/today`:
![PlannerSequenceDiagram](images/PlannerSequenceDiagram.png)
Figure 2: Planner Sequence Diagram during execution of `planner goto/today`

When a user executes a `PlannerCommand` of `planner goto/today`, `MainWindow` will be called to execute the command. It will
then call `LogicManager` to execute, followed by parsing of command in `Tr4ckerParser`. `Tr4ckerParser` will create a new
instance of `PlannerCommandParser` to parse the user's input. After parsing and checking the validity of user's input, a
new `PlannerCommand` instance is created. This new instance `plannerCommand` will be passed back to `LogicManager` to execute
on the `Model` in `PlannerCommand`. After executing, a new instance `CommandResult` `commandResult` is created. `commandResult`
will be passed back to `MainWindow`, then it will be checked in `PlannerTabWindow` if the user wants to switch to Planner
tab, or to view a specific date/month. After that, the calendar and tasks list are updated in Planner tab, and user can
now see the results.

The following activity diagram (Figure 3) summarises what happens when a user executes the 2 main functions of
`PlannerCommand`:
![PlannerActivityDiagram](images/PlannerActivityDiagram.png)
Figure 3: Planner Activity Diagram

This activity diagram shows all the possible paths TR4CKER can take when a user executes a `PlannerCommand`. After
inputting a command, the command is parsed. By checking the arguments provided by the user, it can either mean the
user wants to:
1. switch to Planner tab
2. display an overview of tasks for today/tomorrow and on a specified date/month.

If user wants to switch to Planner tab, TR4CKER will display the planner, and a feedback will be provided to the user. If
user wants to display an overview of tasks, the validity of input will be checked. The input can take these 5 different paths:
1. today
2. tomorrow
3. specified date
4. specified month
5. error (not shown here).

Depending on the inputs, the calendar view and tasks list will be updated accordingly. Planner tab is then displayed to
the user and a feedback is provided.

#### Design considerations:

##### Aspect 1: How users can easily navigate to today's/tomorrow's tasks list

* **Current Choice:** Use the same `planner` command to navigate to today's/tomorrow's tasks list. For example,
`planner goto/today` would navigate users to today's tasks list and `planner goto/tomorrow` would navigate users to
tomorrow's tasks list. Short forms are also provided such as `tdy` and `tmr`.
  * Pros: User-friendly as users would only need to know 1 command.
  * Pros: Users can use short forms, which increase convenience.
  * Cons: Users may not utilise this feature as they do not know the existence of it.

* **Alternative 1:** Separate commands to allow users to navigate to today's/tomorrow's tasks list.
  * Pros: Clearer error messages to prompt users that the input does not conform to standard.
  * Cons: Need to ensure that the implementation of each individual command is correct.
  * Cons: Not as user-friendly as users would need to know multiple commands now.

**Justification for current choice:** After thinking about how different commands would also have their own advantages,
I chose to implement the current choice. The current implementation would allow users to only know 1 command, which would
fairly be more user-friendly, especially after considering how TR4CKER also has many other commands available.
The problem of users not knowing the existence of this command could be solved by documenting this feature clearly in the
User Guide of TR4CKER.

##### Aspect 2: \[tbc]

### Countdown feature \[coming in v1.3] (Wen Ling)
TR4CKER has a countdowns tab which allows users to add important events that they would like TR4CKER to countdown to.
This feature allows users to isolate the most important time sensitive events and deadlines, and tells the user exactly
how many days do they have to a certain event, which enhances the tracking experience.

The 3 main functions of the Countdown feature are to:
1. Display a list of all countdown events
2. Display prominently the 2 earliest upcoming events

#### Implementation \[will be updated with UML diagrams]
The countdown panel is facilitated by the `CountdownPanel` class, which serves as the entry point to show users the
countdown events as a list.

To allow TR4CKER to countdown to events, users can add new events by 2 methods using Countdown Command.

1. Add event based on task in task list (Note: Countdowns list is separate from tasks list, and subsequent changes
to tasks in task list will not be reflected in countdowns list.)

2. Add independent event

Users can also use Countdown Command to

The following activity diagram shows the flow of executing a Countdown Command:
![CountdownCommandActivityDiagram](images/CountdownActivityDiagram.png)


#### Design considerations:

##### Aspect 1: Users should be able to easily view the next most recent event

* **Current Choice:** Use the same `countdown` command to navigate to the next event or previous event. For example,
`countdown first` would display the earliest upcoming event, `countdown next` will display the event after the one
currently displayed, and `countdown previous` with display the event before the one currently displayed.
  * Pros: Users can easily know the chronological sequence of events.
  * Cons: May be difficult to navigate if there is a long list of events.

* **Alternative 1:** Another command to allow users to navigate to the events in a specified day.
  * Pros: More user-friendly as it is faster to navigate to a particular event on a particular day.
  * Cons: Requires user to already know what days have events in the countdown list.

**Justification for current choice:** Considering how users who are using the countdown feature will prefer to be able
to know what is the next upcoming event, for example what is the next exam that they have to prepare for. The first implementation
is also less prone to errors as users do are able to know what is the next event without knowing beforehand what day is it on.

### Module feature \[coming in v1.3] (Ethan)
TR4CKER has a module tab which provides users with an alternate view of tasks. Tasks are arranged in shorter lists categorized by modules.
This feature is to allow users to have a more focused overview of tasks, and know how many tasks are
pending for each module as opposed to the most-urgent-task-first organization in  main task list.

The 2 main functions of the Module feature are to:
1. Display all modules taken by the user.
2. Display the list of yet to be completed tasks under each module.

#### Implementation
To implement the UI of this Module feature, there will be a `ModuleTaskCard` & `ModuleTaskListPanel` classes
in the `ui` package, modified from the `TaskCard` and `TaskListPanel` classes. To implement the commands of
this module feature, there are `ModuleCommnad` and `ModuleCommandParser` classes in the `logic` package.

A module in the Modules tab has its own `ModuleTaskListPanel`, updated whenever new tasks are added with the module or are
edited to be associated under the module.

The following diagram shows the sequence flow when a task gets added to the `ModuleTaskListPanel` of a module:
![TaskWithModuleActivityDiagram](images/TaskWithModuleActivityDiagram.png)
Figure 1: Adding task to `ModuleTaskListPanel` of a module.

#### Design considerations:

##### Aspect 1: How modules are deleted and added to tasks

* **Current Choice:** Modules cannot be deleted if there are existing tasks tagged with the module. Tasks can only be
tagged with an existing module.
  * Pros: Safer, will not have stray tasks with non-existent modules.
  * Pros: User has safeguard against deleting modules that still has pending tasks.
  * Cons: Less flexible, extra steps for the user to create modules before assigning tasks, and delete tasks before
  deleting a module.

* **Alternative 1:** Allow both modules to be deleted and tasks to be tagged regardless of the others' existence.
  * Pros: More convenient for user to use without restrictions.
  * Cons: Prone to error, user may assign incorrectly with typos.

**Justification for current choice:** Better reliability of the feature by reducing possible errors by the user. As errors
will cause more time wasted for the user to fix them anyway, it seems that it is better to incur some overhead to
prevent making a mess altogether.

### Daily feature \[coming in v1.3] (Yingqi)
Tracker has a daily feature that allows users to add current tasks to a todo list for the day.
This feature allows users to plan for what they want to do for a particular day.

The 3 main functions of the Daily feature are to:
1. Add tasks from existing task list to a todo list
2. Display all tasks that the user wants to do for the day
3. The daily todo list is cleared everyday

#### Implementation
The UI of the Daily feature is facilitated by the `DailyPanel` class which will show users all daily todo tasks as a list.

To implement the commands of this Daily feature, there are `TodoCommand` and `TodoCommandParser` classes in the `logic` package.

The following diagram shows the sequence flow when a task gets added into the `DailyPanel`:
![DailySequenceDiagram](images/DailySequenceDiagram.png)

#### Design Considerations

##### Aspect 1: How users can plan for the things he/she wants to complete for the day

* **Current Choice:** When users use list command to view all tasks, only task names are shown.
  * Pros: A neater list is being shown.
  * Pros: Users can look through the list quickly and decide what to add to daily todo list.
  * Cons: Important details such and deadline and descriptions are omitted, users may not know which task is more urgent to do.

* **Alternative 1:** When users decides to add a particular task to daily todo list, details of that task is shown.
  * Pros: Users have clearer idea of the details of the tasks that he/she wants to complete for the day.
  * Cons: Details are shown only when the tasks is already added, if the user do not want to do it for the day, he/she has to delete it from todo list.

**Justification for current choice:** Users would already have details of the tasks in mind and therefore they will tend to add the tasks they feel that are more urgent to daily todo list. Hence details can be ommited when users select tasks to add into daily todo list.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* busy SoC students
* has a need to manage a significant number of tasks, such as assignments
* prefer desktop applications over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI applications

**Value proposition**: TR4CKER is a desktop application that helps SoC students track their tasks, meet their deadlines,
and stay productive. It is optimized for Computing students familiar with CLI, who can manage their tasks efficiently
by typing in commands.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | user                                       | add a new task                 |                                                                        |
| `* * *`  | user                                       | delete a task                  | remove tasks that I have completed or no longer need                   |
| `* * *`  | user                                       | edit a task                    | change the tasks that I have entered wrongly without deleting it       |
| `* * *`  | user                                       | view all tasks                 | have a overview of all tasks that I have                               |
| `* * *`  | user                                       | find a task                    | find the tasks that match the keywords that I want to find             |
| `* * *`  | user                                       | mark a task as completed       | when I complete a task, I want to mark it as completed                 |
| `* * *`  | user                                       | exit from the program          | indicate that I am done with what I wanted to do                       |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `TR4CKER`: application and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add a new task**

**MSS**

1.  User requests to add a new task.
2.  TR4CKER requests for task details.
3.  User provides task details.
4.  TR4CKER inserts the new task to user's task list.

    Use case ends.

**Extensions**

* 3a. TR4CKER detects an error in entered task details.
    * 3a1. TR4CKER requests for the correct task details.
    * 3a2. User enters the new task details.

    Steps 3a1-3a2 are repeated until the details entered are correct.

    Use case resumes at step 4.

**Use case: UC02 - Edit a task**

**MSS**

1.  User requests to <u>list task (UC07)</u>.
2.  User requests to edit a specific task in the list.
3.  TR4CKER asks for the index.
4.  User provides index.
5.  TR4CKER asks for the field that User wishes to edit for the task.
6.  User provides field to edit.
7.  TR4CKER asks for the edited task field.
8.  User provides the new edited task field.
9.  TR4CKER updates the selected field accordingly.

    Use case ends.

**Extensions**

* 4a. TR4CKER detects an error in entered task index.
    * 4a1. TR4CKER shows an error message.

    Use case end.

* 8a. TR4CKER detects an error in edited task field.
    * 8a1. TR4CKER requests for the correct edited field.
    * 8a2. User enters the new edited field.

    Steps 8a1-8a2 are repeated until the edited field entered is correct.

    Use case resumes at step 9.

**Use case: UC03 - Delete a task**

**MSS**

1.  User requests to <u>list task (UC07)</u>.
2.  User requests to delete a specific task in the list.
3.  TR4CKER asks for the index.
4.  User provides index.
5.  TR4CKER requests for confirmation.
6.  User confirms.
7.  TR4CKER deletes the task from the list.

    Use case ends.

**Extensions**

* 4a. TR4CKER detects an error in entered task index.
    * 4a1. TR4CKER shows an error message.

    Use case ends.

**Use case: UC04 - Mark a task as done**

**MSS**

1.  User requests to <u>list task (UC07)</u>.
2.  User requests to mark a specific task in the list as done.
3.  TR4CKER marks the task as done.

    Use case ends.

**Extensions**

* 2a. TR4CKER detects an error in entered task index.

    * 2a1. TR4CKER shows an error message.

     Use case ends.

**Use case: UC05 - View a task**

**MSS**

1.  User requests to <u>list task (UC07)</u>.
2.  User requests to view a specific task in the list.
3.  TR4CKER shows the details of the task.

    Use case ends.

**Extensions**

* 2a. TR4CKER detects an error in entered task index.

    * 2a1. TR4CKER shows an error message.

    Use case ends.

**Use case: UC06 - Find related tasks**

1.  User requests to find tasks using specified keyword(s).
2.  TR4CKER shows a list of all tasks that match the given keyword(s).

    Use case ends.

**Extensions**

* 2a. No tasks match the specified keyword(s).

    * 2a1. TR4CKER shows a 'no matches' error message.

    Use case ends.

**Use case: UC07 - List all tasks**

**MSS**

1.  User requests to list tasks.
2.  TR4CKER shows a list of all tasks.

    Use case ends.

**Extensions**

* 2a. TR4CKER's task list is empty.

    * 2a1. TR4CKER shows an empty list.

    Use case ends.

### Non-Functional Requirements

1.  TR4CKER should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  TR4CKER should be able to hold up to **1000 tasks** without any noticeable sluggishness in performance for typical usage.
3.  Users should be able to use TR4CKER without Internet connection, but accessing of User Guide or Developer Guide would require Internet.
4.  Users with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to
    accomplish most of the tasks faster using commands than using the mouse.
5.  Users should be able to retain their information when they switch laptops if they have the `data folder` (created on first load)
    in the same directory as the `JAR file` on the other laptop.
6.  The user interface should be intuitive enough for users who are not tech-savvy.
7.  The response to any inputted commands should become visible within 5 seconds.
8.  The source code should be open source.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Task**: An item to be completed by a certain deadline.
* **CLI**: Command-Line Interface, a method of interacting with computer programs by typing lines of text.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test TR4CKER manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch TR4CKER by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
