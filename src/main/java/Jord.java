import jord.exception.MissingArgumentException;
import jord.exception.MissingDescriptionException;
import tasks.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Jord {

    public static final String WELCOME_MESSAGE = "    Hello! I'm Jord\n    What can I do for you?";
    public static final String BYE_MESSAGE = "    Bye, see you again!";

    private static ArrayList<Task> TASKS = new ArrayList<Task>(); // todo: change to ArrayList
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String SAVE_PATH = "data/jord.txt";

    public static final String TASK_CORRECT_USAGE = "add <task description>";
    public static final String TODO_CORRECT_USAGE = "todo <description>";
    public static final String EVENT_CORRECT_USAGE = "event <description> /from <date 1> /to <date 2>";
    public static final String DEADLINE_CORRECT_USAGE = "deadline <description> /by <date>";
    public static final String MARKED_COMPLETE = "    The following task has been marked complete";
    public static final String MARKED_INCOMPLETE = "    The following task has been marked incomplete";
    public static final String MARK_CORRECT_USAGE = "mark/unmark <index of task>";
    public static final String LIST_EMPTY_MESSAGE =
            "    No tasks have been added, use todo, event or deadline to add some";
    public static final String DELETE_CORRECT_USAGE = "delete <index of task>";



    public static void printLogo() {
        System.out.println("    ______  _______    _______   ______  \n" +
                "   ╱      ╲╱       ╲╲╱╱       ╲_╱      ╲╲\n" +
                "  ╱       ╱        ╱╱╱        ╱        ╱╱\n" +
                "_╱      ╱╱         ╱        _╱         ╱ \n" +
                "╲______╱╱╲________╱╲____╱___╱╲________╱  ");
    }

    public static void printCorrectUsage(TaskType type) {
        System.out.print("    Correct usage: ");
        switch (type) {
        case TASK:
            System.out.println(TASK_CORRECT_USAGE);
            break;
        case TODO:
            System.out.println(TODO_CORRECT_USAGE);
            break;
        case EVENT:
            System.out.println(EVENT_CORRECT_USAGE);
            break;
        case DEADLINE:
            System.out.println(DEADLINE_CORRECT_USAGE);
            break;
        case MARK:
            System.out.println(MARK_CORRECT_USAGE);
            break;
        case DELETE:
            System.out.println(DELETE_CORRECT_USAGE);
        }
    }

    public static void listTasks() {
        if (TASKS.size() == 0) {
            System.out.println(LIST_EMPTY_MESSAGE);
            return;
        }
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.print("    " + (i+1) + ". ");
            System.out.println(TASKS.get(i).toString());
        }
    }

    public static void printTask(int index) {
        System.out.println("    " + TASKS.get(index).toString());
        System.out.println("    Total tasks: " + (index + 1));
    }

    public static void isTaskMarkerInputValid(String[] input) throws MissingArgumentException, NumberFormatException {
        if (input.length < 2 || input[1].trim().isEmpty() ) {
            throw new MissingArgumentException();
        }
    }

    public static void taskMarker(String[] input) {
        try {
            isTaskMarkerInputValid(input);
            int index = Integer.parseInt(input[1]) - 1;
            boolean isMark = !input[0].contains("un");

            TASKS.get(index).setMarked(isMark);
            System.out.println(isMark ? MARKED_COMPLETE
                    : MARKED_INCOMPLETE);
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
        printCorrectUsage(TaskType.MARK);
    }

    public static void isTaskInputValid(String[] input) throws MissingDescriptionException {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            throw new MissingDescriptionException();
        }
    }

    public static void addTask(String[] input) {
        try {
            isTaskInputValid(input);
            TASKS.add(new Task(input[1]));
            System.out.println("    added task:");
            printTask(TASKS.size()-1);
        } catch (MissingDescriptionException e) {
            System.out.println("    Error: missing task description");
        }
    }



    public static void isTodoInputValid(String[] input) {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            System.out.println("    Error: missing todo description");
            throw new MissingDescriptionException();
        }
    }

    public static void addTodo(String[] input) {
        try {
            isTodoInputValid(input);

            TASKS.add(new Todo(input[1]));
            System.out.println("    added todo:");
            printTask(TASKS.size()-1);

        } catch (MissingDescriptionException e) {
            System.out.println("    Error: missing todo description");
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

    public static void addEvent(String[] input) {
        try {
            isEventInputValid(input);
            // parse input into description, from and to date
            String[] splitInput = input[1].split("/from|/to");

            // checks if /from and /to times are empty or not, done here instead of checker to avoid double work
            TASKS.add(new Event(splitInput[0].trim(), splitInput[1].trim(), splitInput[2].trim()));
            System.out.println("    added task:");
            printTask(TASKS.size()-1);
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Error: from and to duration cannot be empty");
        } catch (MissingDescriptionException e) {
            System.out.println("    Error: missing event description");
        } catch (MissingArgumentException e) {
            System.out.println("    Error: missing event /from, /to arguments");
        }
        printCorrectUsage(TaskType.EVENT);
    }

    public static void isDeadlineInputValid(String[] input) throws MissingDescriptionException, MissingArgumentException {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            throw new MissingDescriptionException();
            }
        if (!input[1].contains("/by")) {
            throw new MissingArgumentException();
        }
    }

    public static void addDeadline(String[] input) {
        try {
            isDeadlineInputValid(input);

            String[] inputs = input[1].split("/by");
            TASKS.add(new Deadline(inputs[0].trim(), inputs[1].trim()));
            System.out.println("    Added deadline:");
            printTask(TASKS.size()-1);
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Error: deadline date cannot be empty");
        } catch (MissingDescriptionException e) {
            System.out.println("    Error: missing deadline description");
        } catch (MissingArgumentException e) {
            System.out.println("    Error: missing completion date");
        }
        printCorrectUsage(TaskType.DEADLINE);
    }

    public static void isDeleteTaskInputValid(String[] input) throws MissingDescriptionException {
        if (input.length < 2 || input[1].trim().isEmpty() ) {
            throw new MissingDescriptionException();
        }
    }

    public static void deleteTask(String[] input) {
        try {
            isDeleteTaskInputValid(input);
            int index = Integer.parseInt(input[1]) - 1;

//            System.out.print(TASKS.get(index).toString());
            TASKS.remove(index);
            System.out.println(" Task deleted");
            return;
        } catch (MissingDescriptionException e) {
            System.out.println("    Error: missing task index");
        } catch (NumberFormatException e) {
            System.out.println("    Error: index specified is not an integer");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("    Error: task of specified index does not exist; use \"list\" to get index ");
        }

        printCorrectUsage(TaskType.DELETE);
    }

    public static String[] getUserInput() {
        // splits inputs into first word and everything else
        String[] splitInput = SCANNER.nextLine().split(" ", 2);
        return splitInput;
    }

    public static void exitJord() {
        writeSave();
        System.out.print(BYE_MESSAGE);
        System.exit(0);
    }

    public static void unknownInput(String input) {
        System.out.println("    Unknown command: " + input);
    }

    private static void processInput(String[] input) {
        String command = input[0].trim().toLowerCase();
        // (un)mark and delete will call writeSave inside, the rest will just append the newly added task

        switch (command) {
        case "list":
            listTasks();
            break;
        case "mark":
            // Fallthrough
        case "unmark":
            taskMarker(input);
            break;
        case "bye":
            exitJord();
            break;
        case "add":
            addTask(input);
            break;
        case "todo":
            addTodo(input); // incomplete
            break;
        case "event":
            addEvent(input); // incomplete
            break;
        case "deadline" :
            addDeadline(input); // incomplete
            break;
        case "delete":
            deleteTask(input);
            break;
        default:
            unknownInput(command);
            break;
        }
    }

    private static void writeSave() {
        // overwrite save with current
        System.out.println("    Saving tasks!");
        try {
            // clears the file
            new FileWriter(SAVE_PATH, false).close();

            FileWriter fw = new FileWriter(SAVE_PATH, true);
            for (int i = 0; i < TASKS.size(); i++) {
//                System.out.println("save text: " + TASKS.get(i).save());
                fw.write(TASKS.get(i).save());
                fw.write(System.lineSeparator());
            }
            fw.close();
            System.out.println("    Save success!");
        } catch (IOException e) {
            System.out.println("    Saving failed! " + e.getMessage());
        }
    }

    private static void loadSave(File save) throws FileNotFoundException {
        Scanner s = new Scanner(save);
        while (s.hasNext()) {
            String tempStr = s.nextLine();
            String[] splitInput = tempStr.split(";");
            Task tempTask;
            switch (splitInput[0]) {
            case "T":
                tempTask = new Todo();
                break;
            case "E":
                tempTask= new Event();
                break;
            case "D":
                tempTask = new Deadline();
                break;
            default:
                tempTask = new Task();
            }
            tempTask.load(splitInput);
            TASKS.add(tempTask);
        }
    }

    private static void createSave(File save) {
        System.out.println("    No save found. Creating!");

        if (!save.getParentFile().exists()) {
            save.getParentFile().mkdirs(); // create "data/" if missing
        }

        try {
            save.createNewFile(); // returns bool
        } catch (IOException e) {
            System.out.println("    Error creating save!");
            return;
        }
        System.out.println("    Save created!");
    }

    private static void saveSetup() {
        // format: <task type>;<completion status>;<desc>;<date 1>;<date 2>\n
        File save = new File(SAVE_PATH);
        try {
            loadSave(save);
            System.out.println("    Save found and loaded!");
        } catch (FileNotFoundException e) {
            createSave(save);
        }
    }

    public static void main(String[] args) {
        printLogo();
        System.out.println(WELCOME_MESSAGE);

        saveSetup();
        while (true) {
            String[] userInput = getUserInput();
            processInput(userInput);
        }
    }
}
