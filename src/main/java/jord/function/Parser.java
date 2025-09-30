package jord.function;

import jord.exception.MissingArgumentException;
import jord.exception.MissingDescriptionException;
import tasks.CommandType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

    public static final Scanner SCANNER = new Scanner(System.in);



    /**
     * checks if the input for the mark/unmark command is correct
     * @param input the input given to the mark/unmark function
     * @throws MissingArgumentException thrown if missing arguments
     * @throws NumberFormatException thrown if no index was entered
     */
    public static void isTaskMarkerInputValid(String[] input) throws MissingArgumentException, NumberFormatException {
        if (input.length < 2 || input[1].trim().isEmpty() ) {
            throw new MissingArgumentException();
        }
    }

    /**
     * Checks if the input for add command is correct
     * @param input user input given to the add task function
     * @throws MissingDescriptionException thrown if missing a description
     */
    public static void isTaskInputValid(String[] input) throws MissingDescriptionException {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            throw new MissingDescriptionException();
        }
    }

    /**
     * Checks if the input for todo command is correct
     * @param input user input given to the todo function
     * @throws MissingDescriptionException thrown if missing a description
     */
    public static void isTodoInputValid(String[] input) throws MissingDescriptionException{
        if (input.length < 2 || input[1].trim().isEmpty()) {
            throw new MissingDescriptionException();
        }
    }

    /**
     * Checks if the input for the event command is correct
     * @param input user input given to event function
     * @throws MissingArgumentException thrown if missing arguments such as /from or /to
     * @throws MissingDescriptionException thrown if missing description
     */
    public static void isEventInputValid(String[] input)
            throws MissingArgumentException, MissingDescriptionException {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            throw new MissingDescriptionException();
        }
        // event description exists
        if (!(input[1].contains("/from") && input[1].contains("/to"))) {
            throw new MissingArgumentException();
        }
    }

    /**
     * Check if the input for the deadline command is correct
     * @param input user input to deadline function
     * @throws MissingArgumentException thrown if missing arguments such as /by
     * @throws MissingDescriptionException thrown if missing description
     */
    public static void isDeadlineInputValid(String[] input)
            throws MissingDescriptionException, MissingArgumentException {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            throw new MissingDescriptionException();
        }
        if (!input[1].contains("/by")) {
            throw new MissingArgumentException();
        }
    }

    /**
     * Checks if the input for the delete function is correct
     * @param input user input to delete function
     * @throws MissingArgumentException thrown if no index for task is provided
     */
    public static void isDeleteTaskInputValid(String[] input) throws MissingArgumentException {
        if (input.length < 2 || input[1].trim().isEmpty() ) {
            throw new MissingArgumentException();
        }
    }

    /**
     * Checks if the input to the find function is correct
     * @param input user input to find function
     * @throws MissingDescriptionException thrown if no description is provided to search for
     */
    public static void isFindTaskInputValid(String[] input) throws MissingDescriptionException {
        if (input[1].isEmpty()) {
            throw new MissingDescriptionException();
        }
    }

    /**
     * Parses date and time string provided by user into date and time format
     * @param dateAndTime user provided string
     * @return parsed user provided date and time into LocalDateTime format
     * @throws DateTimeParseException when the provided date and time does not match the expected formatting
     */
    public static LocalDateTime parseDateTime(String dateAndTime) throws DateTimeParseException {
        // append 00:00 to the end of the string if no timing is provided
        if (!dateAndTime.contains(" ")) { // no space, likely no timing
            dateAndTime += " 0000";
        }
        // the only accepted format is ISO and whatever is written below
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return LocalDateTime.parse(dateAndTime, formatter);
    }

    public static void unknownInput(String input) {
        System.out.println("    Unknown command: " + input);
    }

    public static boolean isExit(String input) {
        return (input.trim().toLowerCase().equals("bye"));
    }

    /**
     * Calls the appropriate command function, does nothing else
     * @param input array of the user's input, split into the first word and the rest of the input
     * @param tasks TaskList object that the command is called on
     */
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
        case "find":
            tasks.findTask(input);
            break;
        case "help":
            Ui.printHelp();
            break;
        default:
            unknownInput(command);
            break;
        }
    }

    /**
     * Scans the command line input, and then splits it into an array of size 2
     * The first word and the rest of the input
     * @return String array of the split input
     */
    public static String[] getUserInput() {
        // splits inputs into first word and everything else
        String[] splitInput = SCANNER.nextLine().split(" ", 2);
        return splitInput;
    }
}
