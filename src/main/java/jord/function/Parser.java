package jord.function;

import jord.exception.MissingArgumentException;
import jord.exception.MissingDescriptionException;
import tasks.CommandType;

import java.util.Scanner;

public class Parser {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static void printCorrectUsage(CommandType type) {
        System.out.print("    Correct usage: ");
        switch (type) {
        case TASK:
            System.out.println(Ui.TASK_CORRECT_USAGE);
            break;
        case TODO:
            System.out.println(Ui.TODO_CORRECT_USAGE);
            break;
        case EVENT:
            System.out.println(Ui.EVENT_CORRECT_USAGE);
            break;
        case DEADLINE:
            System.out.println(Ui.DEADLINE_CORRECT_USAGE);
            break;
        case MARK:
            System.out.println(Ui.MARK_CORRECT_USAGE);
            break;
        case DELETE:
            System.out.println(Ui.DELETE_CORRECT_USAGE);
        }
    }

    public static void isTaskMarkerInputValid(String[] input) throws MissingArgumentException, NumberFormatException {
        if (input.length < 2 || input[1].trim().isEmpty() ) {
            throw new MissingArgumentException();
        }
    }

    public static void isTaskInputValid(String[] input) throws MissingDescriptionException {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            throw new MissingDescriptionException();
        }
    }

    public static void isTodoInputValid(String[] input) {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            System.out.println("    Error: missing todo description");
            throw new MissingDescriptionException();
        }
    }

    public static void isEventInputValid(String[] input) throws MissingArgumentException, MissingDescriptionException {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            throw new MissingDescriptionException();
        }
        // event description exists
        if (!(input[1].contains("/from") && input[1].contains("/to"))) {
            throw new MissingArgumentException();
        }
    }

    public static void isDeadlineInputValid(String[] input) throws MissingDescriptionException, MissingArgumentException {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            throw new MissingDescriptionException();
        }
        if (!input[1].contains("/by")) {
            throw new MissingArgumentException();
        }
    }

    public static void isDeleteTaskInputValid(String[] input) throws MissingDescriptionException {
        if (input.length < 2 || input[1].trim().isEmpty() ) {
            throw new MissingDescriptionException();
        }
    }

    public static void unknownInput(String input) {
        System.out.println("    Unknown command: " + input);
    }

    public static boolean isExit(String input) {
        return (input.trim().toLowerCase().equals("bye")) ? true : false;
    }

    public static void processInput(String[] input, TaskList tasks) {
        String command = input[0].trim().toLowerCase();
        switch (command) {
        case "list":
            tasks.listTasks();
            break;
        case "mark":
            // Fallthrough
        case "unmark":
            tasks.taskMarker(input);
            break;
        case "bye":
//            exitJord(tasks);
            break;
        case "add":
            tasks.addTask(input);
            break;
        case "todo":
            tasks.addTodo(input); // incomplete
            break;
        case "event":
            tasks.addEvent(input); // incomplete
            break;
        case "deadline" :
            tasks.addDeadline(input); // incomplete
            break;
        case "delete":
            tasks.deleteTask(input);
            break;
        default:
            unknownInput(command);
            break;
        }
    }

    public static String[] getUserInput() {
        // splits inputs into first word and everything else
        String[] splitInput = SCANNER.nextLine().split(" ", 2);
        return splitInput;
    }
}
