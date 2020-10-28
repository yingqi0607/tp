---
layout: page
title: User Guide to TR4CKER
---

1. [Overview](#Overview)
    1. [About TR4CKER](#1.1.About TR4CKER)
    2. [About this User Guide](#1.2.About this User Guide)
2. [Quick Start](#2.Quick Start)    
3. [Commands]()

## 1. Overview

### 1.1. About TR4CKER     
TR4CKER is a desktop app that helps SoC students track their tasks, meet their deadlines, and stay productive. It is 
optimized for Computing students familiar with Command Line Interface (CLI), who can manage their tasks efficiently by typing in commands.

### 1.2. About this User Guide
This User Guide explains how to use TR4CKER, as well as provides an understanding of the features and commands and some 
common use cases of this application.  
  
In this guide, we cover:
1. How to navigate the Graphical User Interface (GUI) 
2. How to use the Command Line Interface (CLI)

--------------------------------------------------------------------------------------------------------------------

## 2. Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `tr4cker.jar` from [here](https://github.com/AY2021S1-CS2103T-T10-2/tp/releases/tag/v1.3).

3. Copy the file to the folder you want to use as the _home folder_ for your TR4CKER.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
   
   Figure 1: Application home page populated with sample data

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`add`**`n/task 1 dl/2020-09-08 1700 des/task 1 description t/urgent`: Adds a task named `task 1` with description `task 1
description`, with a deadline of `8 Sep 2020, 1700 hrs` and with an `urgent` tag into TR4CKER.

    * **`done`**`1`: Marks the 1st task as done.

    * **`delete`**`3`: Deletes the 3rd task shown in the current task list.

    * **`edit`**`1 n/actually task 2`: Edits 1st task's name to be `actually task 2`.

    * **`tag`**`1 new/urgent`: Adds `urgent` tag to existing tags of 1st task.

    * **`find`**`task`: Finds the tasks with `task` as a keyword.

    * **`list`**: Lists all tasks in current task list.

    * **`exit`**: Exits TR4CKER.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/tP week 7`.

* Items in square brackets are optional.<br>
  e.g `n/ NAME [t/TAG]` can be used as `n/tP week 7 t/urgent` or as `n/tP week 7`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/urgent`, `t/urgent t/cs2103t` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME dl/DEADLINE`, `dl/DEADLINE n/NAME` is also acceptable.

</div>

## 3. Commands

#### 3.1. Common Commands

##### 3.1.1. Viewing help : `help`
If you cannot recall the command you need, you can enter the help command in any panel.
A help window will pop out with a link to this User Guide.

![help message](images/helpMessage.png)

Figure 2: Expected result after running `help`

##### 3.1.2. Exiting the program: `exit`

You can exit the program anytime by entering the `exit` command in any panel.

Format: `exit`

##### 3.1.3. Switching between tabs

You can switch between tabs by using the following commands:

| Command    | Tab switched to |
| :----------| :--------------:|
|  `home`    | Home            |
|  `daily`   | Daily           |
|  `mod`     | Modules         |
| `countdown`| Countdowns      | 
|  `planner` | Planner         |

Table 1: List of Switch Tab commands

### 3.2. Home (Han Wei)

TR4CKER is primarily a task management application that allows you to track and manage your tasks comprehensively.

TR4CKER's Home page was built for this purpose and you will be directed to this page once you start TR4CKER.
  
#### 3.2.1. Reading the Task lists

For the ease of reading, tasks are split into 3 sections and are displayed separately, namely:
1. Pending Tasks
2. Expired Tasks
3. Completed Tasks

![Ui](images/Ui.png)

Figure 2: Application home page populated by sample data

The leftmost panel displays Pending tasks that are not overdue and not completed.
On the other hand, the Expired Tasks panel displays tasks that are overdue but have yet to be completed.
Lastly, the Completed Tasks panel displays completed tasks for archive purposes.

#### 3.2.2. Command format for Task list Commands

The syntax of the `add` command is `add n/NAME des/DESCRIPTION [m/MODULE_CODE] [dl/DEADLINE] [t/TAG]... ` and it will be used as an
example to explain the following points:

1. Parts of the command in UPPER_CASE represent command parameters that have to be supplied by you. Using the given example, 
DESCRIPTION represents a field where you provide the task description, such as des/Graded assignment 2.   

2. Parameters in square brackets are optional, such as the Tag parameter in the given example.

3. Parameters with a trailing `...` are optional and may be used as many times as you want, or even be omitted. 

4. Parameters can be entered in any order.

##### 3.1.3. Adding a task: `add`

You can add a task to TR4CKER using the `add` command when you want to start working on a new task.

Format: `add n/NAME des/description [m/MODULE_CODE] [dl/DEADLINE] [t/TAG]…​` 

![AddCommand](images/AddCommand.png) 

Figure 3: Example of an expected result after excecuting `add n/UG Introduction des/TableOfContents m/CS2103T t/Urgent t/Important`

Important points to note when entering Deadline:
1. While Deadline is an optional parameter, TR4CKER will set the Deadline to default values if you do not enter a Deadline:
    * If Deadline is not entered, it will be set to Today, 2359 if Deadline
    * If Deadline date is entered without time, Deadline time will be set to 2359
2. DateTime formats accepted for Deadline:
    * dd-MM-yyyy HHmm, dd-MMM-yyy HHmm, dd-MM-yyy, dd-MMM-yyyy
        * dd: the corresponding days in 2 numbers
        * MM: the corresponding month in 2 numbers
        * MMM: the corresponding month in 3 letters
        * yyyy: the corresponding year in 4 numbers
        * HH: the hour the task is due, in 24-Hour format
        * mm: the minute the task is due


<div markdown="span" class="alert alert-primary">**Tip:**
A task can have any number of tags (including 0)
</div>

Examples:
* `add n/CS1101S Quiz dl/25-01-2020 m/CS1101S des/Post-Lecture quiz`
* `add n/CS1231S Homework Assignment dl/09-Aug-2021 2200 des/Chapter 3 t/graded t/assignment`

##### Listing all tasks: `list`

Displays a list of all tasks names in TR4CKER.

Format: `list`

##### Marking tasks as done: `done`

Marks the tasks you have completed as 'done' in TR4CKER.

Format: `done [INDEX]`

* Marks the task as done at the specified `INDEX`.
* The index refers to a valid index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `done 2` marks the 2nd task in the displayed task list as done.
* `find essay` followed by `done 1` marks the 1st task in the results of the `find` command as done.

##### Editing a task: `edit`

Edits an existing task’s details in TR4CKER.

Format: `edit INDEX [n/NAME] [dl/DEADLINE] [des/TASKDESCRIPTION]`

* Edits the task's name, deadline and description at the specified `INDEX`.
* The index refers to the index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing task’s details will be updated to the new task’s details being inputted.
* Editing of tags can be done using `tag` command.

Examples:
* `edit 1 n/prepare for tP tasks` - Edits the description of the 1st task to be `prepare for tP tasks`.
* `edit 2 dl/2020-09-13 1930` - Edits the deadline time of the 2nd task to be 13 Sep 2020, 1930 hrs.

##### Editing tags: `tag`

Adds and/or deletes tag(s) from an existing task in TR4CKER.

Format: `tag INDEX [new/NEW_TAG]…​ [del/TAG_TO_DELETE]…​`

* Adds tag(s) to task at the specified `INDEX` when using `new/NEW_TAG`.
* Deletes tag(s) from task at the specified `INDEX` when using `del/TAG_TO_DELETE`.
* The index refers to the index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the fields must be provided.
* Tag(s) will only be added if it does not exist yet.
* Tag(s) will only be deleted if it exists.
* Adding and deleting of tags can be used concurrently.
* Multiple tags can be added and deleted at the same time.

Examples:
* `tag 1 new/urgent` - Adds a new tag `urgent` to the existing tags of 1st task (if the tag does not already exist).
* `tag 2 del/assignment` - Deletes the tag `assignment` from the 2nd task (if the tag exists).
* `tag 3 new/urgent del/assignment new/graded` - Adds 2 new tags `urgent` and `graded`, deletes the tag `assignment`
from the 3rd task.

##### Locating tasks by keyword: `find`

Finds tasks whose names contain any of the given keywords.

Format: `find [KEYWORD_1] [KEYWORD_2] ...`

* The search is case-insensitive. e.g `cs2101` will match `CS2101`
* The order of the keywords does not matter. e.g. `Presentation CS2101` will match `CS2101 Presentation`
* Only the name is searched.
* Only full words will be matched e.g. `2101` will not match `CS2101`
* Tasks matching at least one keyword will be returned.
  e.g. `find CS2101 project` will return `CS2101 Oral Presentation 1`, `CS2103T team project`

Examples:
* `find CS2101` returns `CS2101 Oral Presentation 1`
* `find CS2103T Quiz` returns `CS2103T Project`, `CS1101S Quiz`<br>
  ![result for 'find CS1231S Mission'](images/findCS2103TQuizResult.png)

##### Deleting an existing task : `delete`

You can delete an existing task from the task list by providing the 
index number of the task that you want to delete.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to a valid index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the displayed task list.
* `find assignment` followed by `delete 1` deletes the 1st task in the results of the `find` command.

##### Clearing all tasks: `clear`

Clears all entries from TR4CKER.

Format: `clear`

#### Daily tab features (Yingqi)
You can add all your daily plans of current tasks to a daily to do list so that you have a clearer idea of what you want to complete for the day.

##### Switching to Daily tab: `daily`
If you would like to switch to Daily tab without clicking on the Daily tab button, you can do so easily through the CLI, by just typing `daily`.

Format: `daily`

Once you enter `daily` into the command box, TR4CKER should look like this:

Example: 
* `daily` - Switches to Daily tab, showing a list of daily todo tasks that you have planned for the day.

##### adding a daily todo task: `todo`

You can add a todo task for the day into the daily todo list by providing the index number of the task that you want to add.

Format: `todo INDEX` [To be implemented: add multiple todo tasks at the same time]

* Adds the task at the specified `INDEX` to daily todo list.
* The index refers to a valid index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `todo 1` adds the first task in the displayed task list into daily todo list.

#### Modules tab features

#### Countdown tab features

#### Planner tab features
You can view an overview of your schedule in a calendar view and have your tasks list side-by-side using the Planner
tab. By having an overview of your upcoming schedule, you would be able to better manage your time and hence
be more productive!

##### Switching to Planner tab: `planner`
If you would like to switch to Planner tab without clicking on the Planner tab button, you can do so easily through the
CLI, by just typing `planner`.

Format: `planner`

Once you enter `planner` into the command box, TR4CKER should look like this:
![switch tab](images/plannertab_switchtab.png)
Figure 1: TR4CKER after executing `planner`

By default, TR4CKER will circle today's date and the show you the tasks due on that day.

Example:
* `planner` - Switches to Planner tab, showing calendar view of today and tasks due today.

##### Switching calendar view and tasks list
If you would like to view the calendar on specific date/month, and with the tasks due on that date beside, you can
use this command.

General format: `planner goto/INPUT`

* Input is compulsory and the format varies on the usages.

For the ease of accessing today's and tomorrow's tasks list, TR4CKER has provided you with 2 commands that you can use!
Short forms like "tdy" for "today" and "tmr" for "tomorrow" also allow you to save time without typing the full words.
##### Today
Format: `planner goto/today` or `planner goto/tdy`

* Input can only be `today` or `tdy`. There are no other alternatives.

First, you enter the command into the command box to go to today:
![goto today command](images/plannertab_gototoday.png)
Figure 2: TR4CKER before executing `planner goto/tdy`

Then, TR4CKER will change planner tab to today's calendar view and tasks due today:
![goto today result](images/plannertab_gototoday1.png)
Figure 3: TR4CKER after executing `planner goto/tdy`

Examples:  
These 2 examples:
* `planner goto/today`
* `planner goto/tdy`

will both give you today's calendar view and tasks due today.

##### Tomorrow
Format: `planner goto/tomorrow` or `planner goto/tmr`

* Input can only be `tomorrow` or `tmr`. There are no other alternatives.

First, you enter the command into the command box to go to tomorrow:
![goto tomorrow command](images/plannertab_gototomorrow.png)
Figure 4: TR4CKER before executing `planner goto/tmr`

Then, TR4CKER will change planner tab to tomorrow's calendar view and tasks due tomorrow:
![goto tomorrow result](images/plannertab_gototomorrow1.png)
Figure 5: TR4CKER after executing `planner goto/tmr`

Examples:  
These 2 examples:
* `planner goto/tomorrow`
* `planner goto/tmr`

will both give you tomorrow's calendar view and tasks due tomorrow.

For the ease of accessing of a specific date's or month's tasks list, TR4CKER has provided you with 2 commands that
you can use!
##### Specific date
Format: `planner goto/dd-mm-yyyy` or `planner goto/dd-MMM-yyyy`

* Input must be a valid date.
* You can either use the month number or month name for the month field of the input date. 

First, you enter the command into the command box to go to a specific date:
![goto date command](images/plannertab_gotodate.png)
Figure 6: TR4CKER before executing `planner goto/08-11-2020`

Then, TR4CKER will change planner tab to the calendar view and tasks due on the input date you provided:
![goto date result](images/plannertab_gotodate1.png)
Figure 7: TR4CKER after executing `planner goto/08-11-2020`

Examples:
* `planner goto/02-12-2020` - Shows you the calendar view of December 2020, with 2nd December 2020 being circled, and
shows you the tasks due on 2nd December 2020.
* `planner goto/28-Feb-2021` - Shows you the calendar view of February 2021, with 28th February 2021 being circled, and
shows you the tasks due on 28th February 2021.

##### Specific month
Format: `planner goto/mm-yyyy` or `planner goto/MMM-yyyy`

* Input must be a valid month.
* You can either use the month number or month name for the month field of the input month.

First, you enter the command into the command box to go to a specific month:
![goto month command](images/plannertab_gotomonth.png)
Figure 8: TR4CKER before executing `planner goto/Sep-2021`

Then, TR4CKER will change planner tab to the calendar view and tasks due on the input month you provided:
![goto month result](images/plannertab_gotomonth1.png)
Figure 9: TR4CKER after executing `planner goto/Sep-2021`

By default, TR4CKER will circle the first day of the month you inputted and show you the tasks due on the first day of
the input month.

Examples:
* `planner goto/12-2020` - Shows you the calendar view of December 2020, with 1st December 2020 being circled, and
shows you the tasks due on 1st December 2020.
* `planner goto/Feb-2021` - Shows you the calendar view of February 2021, with 1st February 2021 being circled, and
shows you the tasks due on 1st February 2021.

##### Indicators on Calendar
You would have realised there are different colours tagged to every day in the calendar. To allow you to have a better
overview of your schedule, Planner tab allows you to check if your upcoming days are busy!

The figure below shows the indicators.
![indicator](images/plannertab_indicator.png)
Figure 10: Indicators (circled in red) on calendar

The indicator colours and their meanings are:
* No indicator - no tasks due on that date
* Green indicator - 2 or lesser tasks due on that date
* Red indicator - more than 2 tasks due on that date

##### Saving the data

TR4CKER data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous TR4CKER home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add n/NAME dl/DEADLINE des/TASKDESCRIPTION [t/TAG]…​` <br> e.g., `add n/task 1 dl/2020-09-08 des/task 1 description t/urgent t/priority`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Done** | `done INDEX`<br> e.g., `done 1`
**Edit** | `edit INDEX [n/NAME] [dl/DEADLINE] [des/TASKDESCRIPTION]`<br> e.g.,`edit 2 n/task 1 dl/2020-09-08`
**Find** | `find [KEYWORD_1] [KEYWORD_2] ...`<br> e.g., `find task 1`
**Clear** | `clear`
**List** | `list`
**Exit** | `exit`
