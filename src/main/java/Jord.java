import java.util.Scanner;

public class Jord {

    private static final int TASK_LIMIT = 100;

    private static Task[] TASKS = new Task[TASK_LIMIT];
    private static int TASK_COUNT = 0;
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String TODO_CORRECT_USAGE = "todo <description>";
    private static final String EVENT_CORRECT_USAGE = "event <description> /from <date 1> /to <date 2>";
    private static final String DEADLINE_CORRECT_USAGE = "deadline <description> /by <date>";
    private static final String MARKED_COMPLETE = "    The following task has been marked complete";
    private static final String MARKED_INCOMPLETE = "    The following task has been marked incomplete";
    private static final String MARK_CORRECT_USAGE = "mark/unmark <index of task>";

    public static void printCorrectUsage(TaskType type) {
        System.out.print("    Correct usage: ");
        switch (type) {
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
        }
    }

    public static void listTasks() {
        if (TASK_COUNT == 0) {
            System.out.println("    No tasks have been added, use todo, event or deadline to add some");
        }
        int i = 0;
        while (TASKS[i] != null) {
            System.out.print("    " + (i+1) + ". ");
            System.out.println(TASKS[i].toString());
            i++;
        }
    }

    public static void printTask(int index) {
        System.out.println("    " + TASKS[index].toString());
        System.out.println("    Total tasks: " + (index + 1));
    }

    public static boolean isTaskMarkerInputValid(String[] input) {
        if (input.length < 2 || input[1].trim().isEmpty() ) {
            System.out.println("    Error: missing task index");
            return false;
        }
        // task index is present, and not empty

        try {
            int i = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            // not an integer
            System.out.println("    Error: index specified is not an integer");
            return false;
        }
        // index specified is an integer
        return true;
    }

    public static void taskMarker(String[] input) {
        if (!isTaskMarkerInputValid(input)) {
            printCorrectUsage(TaskType.MARK);
            return;
        }
        int index = Integer.parseInt(input[1]) - 1;
        boolean isMark = !input[0].contains("un");

        // check if index is within bounds
        if (index < 0 || index > TASK_LIMIT || TASKS[index] == null) {
            System.out.println("    Error: task of specified index does not exist; use \"list\" to get index ");
            printCorrectUsage(TaskType.MARK);
            return;
        }

        TASKS[index].setMarked(isMark);
        System.out.println(isMark ? MARKED_COMPLETE
                : MARKED_INCOMPLETE);
        System.out.print("    ");
        System.out.println(TASKS[index].toString());
    }



    public static boolean isTodoInputValid(String[] input) {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            System.out.println("    Error: missing todo description");
            return false;
        }
        return true;
    }

    public static void addTodo(String[] input) {
        if (!isTodoInputValid(input)) {
            printCorrectUsage(TaskType.TODO);
            return;
        }
        TASKS[TASK_COUNT] = new Todo(input[1]);
        System.out.println("    added todo:");
        printTask(TASK_COUNT);
        TASK_COUNT++;
    }

    public static boolean isEventInputValid(String[] input) {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            System.out.println("    Error: missing event description");
            return false;
        }
        // event description exists
        if (!(input[1].contains("/from") && input[1].contains("/to"))) {
            System.out.println("    Error: missing event /from, /to arguments");
            return false;
        }
        // "/from" and "/to" exists
        return true;
    }

    public static void addEvent(String[] input) {
        if (!isEventInputValid(input)) {
            printCorrectUsage(TaskType.EVENT);
            return;
        }
        // parse input into description, from and to date
        String[] splitInput = input[1].split("/from|/to");

        // checks if /from and /to times are empty or not, done here instead of checker to avoid double work
        try {
            TASKS[TASK_COUNT] = new Event(splitInput[0].trim(), splitInput[1].trim(), splitInput[2].trim());
            System.out.println("    added task:");
            printTask(TASK_COUNT);
            TASK_COUNT++;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Error: from and to duration cannot be empty");
            printCorrectUsage(TaskType.EVENT);

        }
    }

    public static boolean isDeadlineInputValid(String[] input) {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            System.out.println("    Error: missing deadline description");
            return false;
        }
        if (!input[1].contains("/by")) {
            System.out.println("    Error: missing completion date");
            return false;
        }
        return true;
    }

    public static void addDeadline(String[] input) {
        if (!isDeadlineInputValid(input)){
            printCorrectUsage(TaskType.DEADLINE);
            return;
        }
        try {
            String[] inputs = input[1].split("/by");
            TASKS[TASK_COUNT] = new Deadline(inputs[0].trim(), inputs[1].trim());
            System.out.println("    Added deadline:");
            printTask(TASK_COUNT);
            TASK_COUNT++;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Error: deadline date cannot be empty");
            printCorrectUsage(TaskType.DEADLINE);

        }
    }

    public static String[] getUserInput() {
        // splits inputs into first word and everything else
        String[] splitInput = SCANNER.nextLine().split(" ", 2);
        return splitInput;
    }

    public static void exitJord() {
        System.out.print("    Bye, see you again!");
        System.exit(0);
    }

    public static void unknownInput(String input) {
        System.out.println("    Unknown command: " + input);
    }

    private static void processInput(String[] input) {
        String command = input[0].trim().toLowerCase();
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
        case "todo":
            addTodo(input); // incomplete
            break;
        case "event":
            addEvent(input); // incomplete
            break;
        case "deadline" :
            addDeadline(input); // incomplete
            break;
        default:
            unknownInput(command);
            break;
        }
    }

    public static void main(String[] args) {
        System.out.println("    Hello! I'm Jord\n    What can I do for you?");
        while (true) {
            String[] userInput = getUserInput();
            processInput(userInput);
        }
    }
}
