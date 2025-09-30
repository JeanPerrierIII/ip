package jord.function;

import jord.exception.MissingArgumentException;
import jord.exception.MissingDescriptionException;
import tasks.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> TASKS;

    public TaskList() {
        TASKS = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        TASKS = tasks;
    }

    public void listTasks() {
        if (TASKS.isEmpty()) {
            System.out.println(Ui.LIST_EMPTY_MESSAGE);
            return;
        }
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.print("    " + (i+1) + ". ");
            System.out.println(TASKS.get(i).toString());
        }
    }

    public void printTask(int index) {
        System.out.println("    " + TASKS.get(index).toString());
        System.out.println("    Total tasks: " + (index + 1));
    }

    public void taskMarker(String[] input) {
        try {
            Parser.isTaskMarkerInputValid(input);
            int index = Integer.parseInt(input[1]) - 1;
            boolean isMark = !input[0].contains("un");

            TASKS.get(index).setMarked(isMark);
            System.out.println(isMark ? Ui.MARKED_COMPLETE
                    : Ui.MARKED_INCOMPLETE);
            System.out.print("    ");
            System.out.println(TASKS.get(index).toString());
            return;

        } catch (NumberFormatException e) {
            System.out.println("    Error: index specified is not an integer");
        } catch (MissingArgumentException e) {
            System.out.println("    Error: missing task index");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("    Error: task of specified index does not exist; use \"list\" to get index ");
        }
        // print correct usage
        Ui.printCorrectUsage(CommandType.MARK, false);
    }

    public void addTask(String[] input) {
        try {
            Parser.isTaskInputValid(input);
            TASKS.add(new Task(input[1]));
            System.out.println("    added task:");
            printTask(TASKS.size()-1);
        } catch (MissingDescriptionException e) {
            System.out.println("    Error: missing task description");
            Ui.printCorrectUsage(CommandType.TASK, false);
        }
    }

    public void addTodo(String[] input) {
        try {
            Parser.isTodoInputValid(input);

            TASKS.add(new Todo(input[1]));
            System.out.println("    added todo:");
            printTask(TASKS.size()-1);

        } catch (MissingDescriptionException e) {
            System.out.println("    Error: missing todo description");
            Ui.printCorrectUsage(CommandType.TODO, false);
        }
    }

    public void addEvent(String[] input) {
        try {
            Parser.isEventInputValid(input);
            // parse input into description, from and to date
            String[] splitInput = input[1].split("/from|/to");

            // parse splitInput[2:1] to date and time format
            LocalDateTime fromDate = Parser.parseDateTime(splitInput[1].trim());
            LocalDateTime toDate = Parser.parseDateTime(splitInput[2].trim());
            TASKS.add(new Event(splitInput[0].trim(), fromDate, toDate));
            System.out.println("    added task:");
            printTask(TASKS.size()-1);
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Error: from and to duration cannot be empty");
        } catch (MissingDescriptionException e) {
            System.out.println("    Error: missing event description");
        } catch (MissingArgumentException e) {
            System.out.println("    Error: missing event /from, /to arguments");
        } catch (DateTimeParseException e) {
            System.out.println("    Error: date format incorrect, use yyyy/mm/dd hhmm");
        }
        Ui.printCorrectUsage(CommandType.EVENT, false);
    }

    public void addDeadline(String[] input) {
        try {
            Parser.isDeadlineInputValid(input);

            String[] inputs = input[1].split("/by");
            LocalDateTime byDate = Parser.parseDateTime(inputs[1].trim());
            TASKS.add(new Deadline(inputs[0].trim(), byDate));
            System.out.println("    Added deadline:");
            printTask(TASKS.size()-1);
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Error: deadline date cannot be empty");
        } catch (MissingDescriptionException e) {
            System.out.println("    Error: missing deadline description");
        } catch (MissingArgumentException e) {
            System.out.println("    Error: missing completion date");
        } catch (DateTimeParseException e) {
            System.out.println("    Error: date format incorrect, use yyyy/mm/dd hhmm");
        }
        Ui.printCorrectUsage(CommandType.DEADLINE, false);
    }

    public void deleteTask(String[] input) {
        try {
            Parser.isDeleteTaskInputValid(input);
            int index = Integer.parseInt(input[1]) - 1;

            Task temp = TASKS.get(index);
            TASKS.remove(index);
            System.out.println("    Deleted task:");
            System.out.println("      " + temp);
            System.out.println("    You have " + TASKS.size() + " task(s) left");
            return;
        } catch (MissingArgumentException e) {
            System.out.println("    Error: missing task index");
        } catch (NumberFormatException e) {
            System.out.println("    Error: index specified is not an integer");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("    Error: task of specified index does not exist; use \"list\" to get index ");
        }
        Ui.printCorrectUsage(CommandType.DELETE, false);
    }


    public void findTask(String[] searchString) {
        try {
            Parser.isFindTaskInputValid(searchString);
            System.out.println("    Here are the tasks matching the provided description");
            int count = 0;
            for (Task task : TASKS) {
                if (task.getDescription().contains(searchString[1])) {
                    System.out.println("    " + task);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("    No tasks matching the description was found!");
            }
        } catch (MissingDescriptionException | ArrayIndexOutOfBoundsException e) {
            System.out.println("    Error: search description cannot be empty!");
        }
    }

    public ArrayList<Task> getTasks() {
        return TASKS;
    }
}
