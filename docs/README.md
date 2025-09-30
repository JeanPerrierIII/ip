# Jord User Guide

---
Jord is an app for **keeping track and managing tasks, optimised for use via a Command Line Interface (CLI)**

- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help](#getting-help)
  - [Adding a generic task](#adding-generic-tasks)
  - [Adding a todo](#adding-to-dos)
  - [Adding an event](#adding-events)
  - [Adding a deadline](#adding-deadlines)
  - [Listing tasks](#listing-tasks)
  - [Searching for tasks](#searching-for-tasks)
  - [Marking task as complete](#marking-tasks-as-complete)
  - [Marking task as incomplete](#marking-tasks-as-incomplete)
  - [Deleting a task](#deletion-of-tasks)
  - [Exiting the application](#exiting-the-application)
- [Task saving](#saving-of-tasks)
- [Command summary](#command-summary)

## Quick Start

---

1. Ensure that you have Java `17` or above installed on your device
2. Download the latest `.jar` from [releases](https://https://github.com/JeanPerrierIII/ip/releases)
3. Move or copy the jar to the folder you wish to run Jord in
4. Open your command line interface and `cd` into the folder containing the jar
5. Run `java -jar Jord.jar` to run the application
6. You will be presented with Jord's welcome message and be prompted to enter commands

```
    ______  _______    _______   ______  
   /      \/       \\//       \_/      \\
  /       /        ///        /        //
_/      //         /        _/         / 
\______//\________/\____/___/\________/  
    Hello! I'm Jord
    Use "help" to list available commands!
    What can I do for you?
```



## Features

---


### Getting help
Displays all available commands and their function
**Example:** `help`

**Expected output:**
```
    Available commands:
    help - displays this message
    list - lists all tasks
    bye - shuts down the program
    find <description> - search for tasks containing the provided description

    To add tasks, use the following:
    add <task description>
    deadline <description> /by <date>
    event <description> /from <date 1> /to <date 2>
    todo <description>
    mark/unmark <index of task>
    delete <index of task>
```
### Adding generic tasks

Adds generic type task, nothing special

**Format:** `add <description>`

**Example:** `add drink water`

**Expected output:**
```
    added task:
    [ ] drink water
    Total tasks: 1
```

### Adding To dos

Adds a to-do to be done, without any time or date, similar to _task_

**Format:** `todo <description>`

**Example:** `todo buy milk`

**Expected output:**
```
    added todo:
    [T][ ] buy milk
    Total tasks: 1
```

### Adding Events

Add an event with a start and end date. Date and time must be provided in `yyyy/mm/dd hhmm` format

**Format:** `event <description> /from <yyyy/mm/dd hhmm> /to <yyyy/mm/dd hhmm>`

**Example:** `event sawcon /from 2025/03/14/ 0900 /to 2025/03/16 1700`

**Expected output:**
```
    added task:
    [E][ ] sawcon (from: Mar 14 2025, 0900H to: Mar 16 2025, 1700H)
    Total tasks: 1
```

For events that do not have a specific timing, the time may be omitted

**Format:**`event <description> /from <yyyy/mm/dd> /to <yyyy/mm/dd>`

**Example:** `event hawpcon /from 2026/09/17 /to 2025/09/18`

**Expected output:**
```
    added task:
    [E][ ] hawpcon (from: Sep 17 2026, 0000H to: Sep 18 2025, 0000H)
    Total tasks: 1
```

### Adding Deadlines

Add a deadline with a by date. Date and time must be provided in `yyyy/mm/dd hhmm` format

**Format:** `deadline <description> /by <yyyy/mm/dd hhmm>`

**Example:** `deadline Assignment 1 /by 2025/12/04`

**Expected output:**
```
    Added deadline:
    [D][ ] Assignment 1 (by: Dec 4 2025, 0000H)
    Total tasks: 1
```

### Listing tasks

**Format:** `list`

**Expected output:**
```
    1. [ ] foo
    2. [T][ ] bar
    3. [E][ ] baz (from: Jan 1 1970, 0000H to: Jan 2 1970, 0000H)
    4. [D][ ] qux (by: Jan 1 1970, 0000H)
```
Note: the above is just a sample list of tasks
### Searching for tasks

**Format:** `find <description>`

**Example:** `find ba`

**Expected output:** 
```
    Here are the tasks matching the provided description
    [T][ ] bar
    [E][ ] baz (from: Jan 1 1970, 0000H to: Jan 2 1970, 0000H)
```

### Marking tasks as complete
**Format:** `mark <task index>`

**Example:** `mark 1`

**Expected output:**
```
    The following task has been marked complete
    [X] foo
```

### Marking tasks as incomplete
**Format:** `unmark <task index>`

**Example:** `unmark 1`

**Expected output:**
```
    The following task has been marked incomplete
    [ ] foo
```

### Deletion of tasks

Deletes the task matching the user input index

**Format:** `delete <task index>`

**Example:** `delete 1`

**Expected output:**
```
    Deleted task:
      [ ] foo
    You have 3 task(s) left
```

### Exiting the application

Saves tasks to local storage and quits the application

**Format:** `bye`

**Expected output:**
```
    Saving tasks!
    Save success!
    Bye, see you again!
```

## Saving of tasks

---

Loading and saving tasks from/to local storage is handled automatically by Jord.

If a save is found Jord will load and the following will be displayed on startup:
```
    Save found and loaded!
```

In the event of corrupted entries, Jord will skip corrupted entries and load the remaining ones. The following message will be displayed:
```
    A total of n corrupted tasks were found and skipped!
```

If no save is found, Jord will automatically create a new one, and the following will be displayed on startup:
```
    No save found. Creating!
    Save created!
```

## Command summary

---

| Feature                 | Command                                           |
|-------------------------|---------------------------------------------------|
| View help               | `help`                                            |
| Adding a generic task   | `add <description>`                               |
| Adding a todo           | `todo <description>`                              |
| Adding an event         | `event <description> /from <date 1> /to <date 2>` |
| Adding a deadline       | `deadline <description> /by <date>`               |
| Listing tasks           | `list`                                            |
| Searching for tasks     | `find <description>`                              |
| Mark task as complete   | `mark <task index>`                               |
| Mark task as incomplete | `unmark <task_index>`                             |
| Delete task             | `delete <task_index>`                             |
| Exitting the program    | `bye`                                             |
