---
layout: page
title: User Guide to TR4CKER
---

TR4CKER is a desktop app that helps SoC students track their tasks, meet their deadlines, and stay productive. It is 
optimized for Computing students familiar with CLI, who can manage their tasks efficiently by typing in commands.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `tr4cker.jar` from [here](https://github.com/AY2021S1-CS2103T-T10-2/tp/releases/tag/v1.1).

3. Copy the file to the folder you want to use as the _home folder_ for your TR4CKER.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:
   
    * **`add`**`n/task 1 dl/2020-09-08 1700 des/task 1 description t/urgent`: Adds a task named `task 1` with description `task 1 
description`, with a deadline of `8 Sep 2020, 1700 hrs` and with an `urgent` tag into TR4CKER.

    * **`done`**`1`: Marks the 1st task as done.

    * **`delete`**`3`: Deletes the 3rd task shown in the current task list.

    * **`edit`**`1 n/actually task 2`: Edits 1st task's name to be `actually task 2`.

    * **`find`**`task`: Finds the tasks with `task` as a keyword.

    * **`list`**: Lists all tasks in current task list.

    * **`exit`**: Exits TR4CKER.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/tP week 7`.

* Items in square brackets are optional.<br>
  e.g `n/ NAME [t/TAG]` can be used as `n/tP week 7 t/urgent` or as `n/tP week 7`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/urgent`, `t/urgent t/cs2103t` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME dl/DEADLINE`, `dl/DEADLINE n/NAME` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a new task: `add`

You can add a new task to TR4CKER.

Default deadline time would be set to 2359 if you do not provide a deadline time.

You should enter time in this format: yyyy-MM-dd HHmm

Format: `add n/NAME dl/DEADLINE des/description [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A task can have any number of tags (including 0)
</div>

Examples:
* `add n/CS1101S Quiz dl/2020-01-25 des/Post-Lecture quiz`
* `add n/CS1231S Homework Assignment dl/2020-09-08 2200 des/task 1 description t/assignment t/urgent`

### Marking tasks as done: `done`

Marks the tasks you have completed as 'done' in TR4CKER.

Format: `done [INDEX]`

* Marks the task as done at the specified `INDEX`.
* The index refers to a valid index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `done 2` marks the 2nd task in the displayed task list as done.
* `find essay` followed by `done 1` marks the 1st task in the results of the `find` command as done.

### Listing all persons : `list`

Shows a list of all persons in tr4cker.

Format: `list`

### Editing a task: `edit`

Edits an existing task’s details in TR4CKER.

Format: `edit INDEX [n/NAME] [dl/DEADLINE] [des/TASKDESCRIPTION] [t/TAG]…​`

* Edits the task's name, deadline, description and tags at the specified `INDEX`. 
* The index refers to the index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing task’s details will be updated to the new task’s details being inputted.
* When editing tags, the existing tags of the task will be removed i.e adding of tags is not cumulative.
* You can remove all the task’s tags by typing `t/` without specifying any tags after it.

Examples:
* `edit 1 n/ prepare for tP tasks` - Edits the description of the 1st task to be `prepare for tP tasks`.
* `edit 2 dl/ 2020-09-13 1930` - Edits the deadline time of the 2nd task to be 13 Sep 2020, 1930 hrs.
* `edit 3 t/` - Clears the existing tags of the 3rd task.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting an existing task : `delete`

You can delete an existing task from the task list by providing the 
index number of the task that you want to delete.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to a valid index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the displayed task list.
* `find assignment` followed by `delete 1` deletes the 1st task in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from tr4cker.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

tr4cker data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Archiving data files `[coming in v2.0]`

_{explain the feature here}_

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous tr4cker home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME dl/DEADLINE des/TASKDESCRIPTION [t/TAG]…​` <br> e.g., `add n/task 1 dl/2020-09-08 des/task 1 description t/urgent t/priority`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Done** | `done INDEX`<br> e.g., `done 1`
**Edit** | `edit INDEX [n/NAME] [dl/DEADLINE] [des/TASKDESCRIPTION] [t/TAG]…​`<br> e.g.,`edit 2 n/task 1 dl/2020-09-08`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find task 1`
**List** | `list`
**Help** | `help`
