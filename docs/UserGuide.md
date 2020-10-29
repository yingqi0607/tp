---
layout: page
title: User Guide to TR4CKER
---

1. [Overview](#1-overview)
    1. [About TR4CKER](#11-about-tr4cker)
    2. [About this User Guide](#12-about-this-user-guide)
2. [Quick Start](#2-quick-start)    
3. [Commands](#3-commands)
    * 3.1. [Common Commands](#31-common-commands)
        * 3.1.1. [Viewing help: `help`](#311-viewing-help--help)
        * 3.1.2. [Exiting the program: `exit`](#312-exiting-the-program-exit)
        * 3.1.3. [Switching between tabs](#313-switching-between-tabs)
    * 3.2. [Home Tab](#32-home-han-wei)
        * 3.2.1. [Reading the Task lists](#321-reading-the-task-lists)
        * 3.2.2. [Command format for Task list Commands](#322-command-format-for-task-list-commands)
        * 3.2.3. [Adding new tasks: `add`](#323-adding-new-tasks-add)
        * 3.2.4. [Listing all tasks: `list`](#324-listing-all-tasks-list)
        * 3.2.5. [Marking Pending tasks as done: `done`](#325-marking-tasks-as-done-done)
        * 3.2.6. [Editing Pending tasks: `edit`](#326-editing-pending-tasks-edit)
        * 3.2.7. [Editing tags of Pending tasks: `tag`](#327-editing-tags-of-pending-tasks-tag)
        * 3.2.8. [Locating Pending tasks by keywords: `find`](#328-locating-pending-tasks-by-keyword-find)
        * 3.2.9. [Deleting Pending tasks: `delete`](#329-deleting-pending-tasks--delete)
        * 3.2.10. [Editing Expired tasks: `edit expired`](#3210-editing-expired-tasks-edit-expired)
        * 3.2.11. [Deleting Expired tasks: `delete expired`](#3211-deleting-expired-tasks--delete-expired)
        * 3.2.12. [Clearing all tasks: `clear`](#clearing-all-tasks-clear)
4. [FAQ](#faq)
5. [Command Summary](#command-summary)

## 1. Overview
Welcome to the User Guide of TR4CKER!

### 1.1. About TR4CKER     
TR4CKER is a desktop app that helps SoC students track their tasks, meet their deadlines, and stay productive. It is 
optimized for Computing students familiar with Command Line Interface (CLI), who can manage their tasks efficiently
by typing in commands.

### 1.2. About this User Guide
This User Guide explains how to use TR4CKER, as well as provides an understanding of the features and commands, and some 
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

--------------------------------------------------------------------------------------------------------------------

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

TR4CKER's Home page was built for this purpose and you will be directed to this page once you start TR4CKER. Alternatively,
 you can use enter `home` on the Command Box to navigate to this page.
  
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

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

1. Parts of the command in UPPER_CASE represent command parameters that have to be supplied by you. Using the given example, 
DESCRIPTION represents a field where you provide the task description, such as des/Graded assignment 2.   

2. Parameters in square brackets are optional, such as the Tag parameter in the given example.

3. Parameters with a trailing `...` are optional and may be used as many times as you want, or even be omitted. 

4. Parameters can be entered in any order.

</div>

##### 3.2.3. Adding new tasks: `add`

You can add a task to TR4CKER using the `add` command when you want to start working on a new task.

Format: `add n/NAME des/DESCRIPTION [m/MODULE_CODE] [dl/DEADLINE] [t/TAG]…​` 

![AddCommand](images/AddCommand.png) 

Figure 3: Example of an expected result after executing `add n/UG Introduction des/TableOfContents m/CS2103T t/Urgent t/Important`

Before you can add a task with module, you need to ensure that the module is already entered into TR4CKER. 
You can refer to the section on Module Tab to find out more.

<div markdown="block" class="alert alert-info">

**:information_source: Important points to note when entering Deadline:**<br>

1. While Deadline is an optional parameter, TR4CKER will set the Deadline to default values if you do not enter them:
    * If you do not enter a Deadline, it will be set to Today, 2359.
    * If you enter a Deadline date without specifying a time, Deadline time will be set to 2359.
2. DateTime formats accepted for Deadline:
    * dd-MM-yyyy HHmm, dd-MMM-yyy HHmm, dd-MM-yyy, dd-MMM-yyyy
        * dd: the corresponding days in 2 numbers.
        * MM: the corresponding month in 2 numbers.
        * MMM: the corresponding month in 3 letters (First letter must be capitalised, e.g. Jan, Sep).
        * yyyy: the corresponding year in 4 numbers.
        * HH: the hour the task is due, in 24-Hour format.
        * mm: the minute the task is due.
    * Natural Dates
        * Today
        * Days of week (e.g. Monday, Sunday)
            * If Monday is entered, it refers to the upcoming Monday.

</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Tags cannot contain spaces and must be alphanumeric!
</div>

Examples:
* `add n/tP PE m/CS2103T des/Practical Exam`
* `add n/CS1101S Quiz dl/25-11-2020 m/CS1101S des/Post-Lecture quiz`
* `add n/CS1231S Homework Assignment dl/today 2200 m/CS1231S des/Chapter 3 t/graded t/assignment`

##### 3.2.4. Listing all tasks: `list`

Shows a list of all tasks in TR4CKER.

Format: `list`

##### 3.2.5. Marking tasks as done: `done`

TR4CKER allows you to track the progress of a task in TR4CKER and you can use the `done` command when you want to update the Completion Status of a task.

Format: `done INDEX [p/COMPLETION_STATUS]`

![DoneCommand](images/DoneCommand.png) 

Figure 4: Example of an expected result after executing `done 1 p/50`

* Marks the task as done at the specified `INDEX`.
* The index refers to a valid index number shown in the specified Pending Tasks list.
* The index **must be a positive integer** 1, 2, 3, …​
* Completion Status is a percentage of task done and therefore only accept integer values from 0 to 100.
* If Completion Status is not entered, TR4CKER will mark the task as 100% done.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Completion Status can either be increased or decreased!
</div>

Examples:
* `list` followed by `done 2 p/50` marks the 2nd task in the displayed task list as 50% done.
* `find tutorial` followed by `done 1` marks the 1st task in the results of the `find` command as 100% done.

##### 3.2.6. Editing Pending tasks: `edit`

You can edit an existing Pending task in TR4CKER by using the `edit` command when the need arises.

Format: `edit INDEX [n/NAME] [dl/DEADLINE] [des/DESCRIPTION]`

* Edits the task's name, deadline and description at the specified `INDEX`.
* The index refers to the index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Editing of tags is done using `tag` command instead (Refer to the TagCommand section).

Examples:
* `edit 1 n/prepare for tP tasks` - Edits the description of the 1st task to be `prepare for tP tasks`.
* `edit 2 dl/13-Dec-2020 1930` - Edits the deadline time of the 2nd task to be 13 Dec 2020, 1930 hrs.

##### 3.2.7. Editing tags of Pending tasks: `tag`

You can add or delete tags of a Pending task in TR4CKER.

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

##### 3.2.8. Locating Pending tasks by keyword: `find`

You can find tasks whose names contain any of the given keywords.

Format: `find [KEYWORD_1] [KEYWORD_2] ...`

![FindCommand](images/FindCommand.png)

Figure 5: Example of an expected result after executing `find cs2103T Assignment`

* The search is case-insensitive. e.g `cs2103T` will match `CS21013T`
* The order of the keywords does not matter. e.g. `Assignment CS2103T` will match `CS2103T Assignment`
* Only the name is searched.
* Only full words will be matched e.g. `2101` will not match `CS2101`
* Tasks matching at least one keyword will be returned.
  e.g. `find CS2103T Assignment` will return `CS2103T Project`, `CS2100 Assignment 3`

Examples:
* `find CS2101` returns `CS2101 Oral Presentation 1`
* `find CS1231S Mission` returns `CS1101S Mission`, `CS1231S Graded Assignment`

##### 3.2.9. Deleting Pending tasks : `delete`

You can delete an existing task from the task list by providing the 
index number of the task that you want to delete.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to a valid index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the displayed task list.
* `find assignment` followed by `delete 1` deletes the 1st task in the results of the `find` command.

##### 3.2.10. Editing Expired tasks: `edit expired`

The usage of this command is similar to the `edit` command for a Pending task.

Format: `edit expired INDEX [n/NAME] [dl/DEADLINE] [des/DESCRIPTION]`

<div markdown="block" class="alert alert-info">

**:information_source: If you edit the Deadline of an Expired Task:**<br>

Editing the Deadline of an Expired task will turn the task into a Pending task because new Deadline entered
must be a future Deadline. Hence, the edited task will be displayed under the Pending Task panel instead.

</div>

##### 3.2.11. Deleting Expired tasks : `delete expired`

The usage of this command is similar to the `delete` command for a Pending task.

Format: `delete expired INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to a valid index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the displayed task list.
* `find assignment` followed by `delete 1` deletes the 1st task in the results of the `find` command.

##### 3.2.12. Clearing all tasks: `clear`

Clears all entries in TR4CKER.

Format: `clear`

#### Daily tab features

#### Modules tab features

#### Countdown tab features (Wen Ling)
You can countdown to your most essential upcoming events in the Countdown tab. You can know at a quick glance, how many
you have left to that Final Exam or that birthday party.

##### Switching to Countdown tab: `countdown`
To switch to the Countdown tab, simply type `countdown` into the command line. You can also click on the Countdown button
on the tabs menu at the top.

Format: `countdown`
Once you have entered this command, TR4CKER should look like this:
![switch tab](images/countdowntab_switchtab.png)
Figure x: TR4CKER after executing `countdown`

The `Upcoming Events` panel shows the list of upcoming events that are counted down to.

Example:
* `countdown` - Switches to Countdown tab, showing the list of events.

##### Adding a new event to countdowns list
You can add an event to the list of events, and TR4CKER helps you to countdown to it.

Format: `countdown n/NAME d/DATE`

* Adds an event with name `NAME` and date `DATE` to the Countdown events list.
* Both fields are compulsory and can be in any order.
* `NAME` must be in the alphanumeric format (only containing letters and numbers) and preferably under 
100 characters long, so that it can be displayed in the box without being truncated.
* `DATE` must be a day in the future (i.e. NOT today or any day before today) and be in the format 
`DD-MM-YYYY` or `DD-MMM-YYYY`.

Examples:
* `countdown d/31-12-2020 n/New Years Eve Countdown Party at Scarlets` - Adds an event `New Years Eve Countdown Party
at Scarlets` with date `31-Dec-2020` to the Countdowns list of events.
* `countdown n/CS1231 Final Exam d/30-Nov-2020` - Adds an event `CS1231 Final Exam` with date `30-Nov-2020` to the
Countdowns list of events.

![add_new](images/countdowntab_addnew.png)
Figure x: TR4CKER after executing `countdown n/ES2660 Final Presentation d/05-Nov-2020`

##### Adding an event from tasks list
You can add an event that is based on a current task in the tasks list (as seen in the homepage of TR4CKER under the
`Pending Tasks` panel) to your list of countdowns. TR4CKER will help you to countdown to this event, in addition to
tracking it in the tasks list.

Format: `countdown task/INDEX`

* Converts the task at index `INDEX` of the task list into an event, and adds this event to the Countdowns events list.
* `INDEX` has to be a valid index, numbered according to the list in the home page of TR4CKER. 
* The deadline of this task needs to be a day in the future (i.e. NOT today or any day before today).
* Note: any subsequent changes to the task in the task list, such as editing the description or name of the task,
will not be reflected in the event.

Example:
* `countdown task/1` - Adds an event to the Countdowns list of events based on the task at index `1` of the tasks list.

![add_task_before](images/countdowntab_addtask_before.png)
Figure x: Home page of TR4CKER which shows the list of tasks that you can add as an event, in the `Pending Tasks` panel
on the left.

![add_task_after](images/countdowntab_addtask_after.png)
Figure x: TR4CKER after executing `countdown task/2`. The task at index `2` in Figure x is added at index `9` of the
Countdowns events list.

##### Deleting an event from countdowns list
You can delete an event from your list of events if you no longer want to countdown to it, or if it is over.

Format: `countdown del/INDEX`

* Deletes the task at index `INDEX` of the events list.
* `INDEX` has to be a valid index, numbered according to the list in Countdown tab.
* Warning: This action is irreversible!

Example:
* `countdown del/8` - Deletes the event at index `8` of the Countdowns list.

![delete_before](images/countdowntab_delete_before.png)
Figure x: Countdown tab of TR4CKER which shows you the current list of events.

![delete_after](images/countdowntab_delete_after.png)
Figure x: TR4CKER after executing `countdown del/2`. Event at index `2` of Figure x is deleted. 

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

TR4CKER saves data in the hard disk automatically after any command that changes the data.
There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous TR4CKER home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

* **Help**: `help`
* **Exit**: `exit`
* **Add**: `add n/NAME des/DESCRIPTION [dl/DEADLINE] [m/MODULE_CODE] [t/TAG]...`
* **List**: `list`
* **Done**: `done INDEX [p/COMPLETION_STATUS]`
* **Edit Pending tasks**: `edit INDEX [n/NAME] [des/DESCRIPTION] [dl/DEADLINE]`
* **Edit Tags of Pending tasks**: `tag INDEX [old/TAG_TO_DELETE]... [new/NEW_TAG]...`
* **Find Pending tasks**: `find [KEYWORD]...`
* **Delete Pending tasks**: `delete INDEX`
* **Edit Expired tasks**: `edit expired INDEX [n/NAME] [dl/DEADLINE] [des/DESCRIPTION]`
* **Delete Expired tasks**: `delete expired INDEX`
* **Clear all tasks**: `clear`


