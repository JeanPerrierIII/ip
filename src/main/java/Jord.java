import java.util.Scanner;

public class Jord {

    private static final int TASK_LIMIT = 100;
    private static Task[] TASKS = new Task[TASK_LIMIT];
    private static int TASK_COUNT = 0;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void listTasks() {
        int i = 0;
        while (TASKS[i] != null) {
            System.out.print("    " + (i+1) + ". ");
            System.out.println(TASKS[i].toString());
            i++;
        }
    }

    public static void printTask(int index) {
        System.out.println(TASKS[TASK_COUNT].toString());
        System.out.println("Total tasks: " + (TASK_COUNT + 1));
    }

    public static void taskMarker(String[] input) {
        int index = Integer.parseInt(input[1]) - 1;
        TASKS[index].setMarked(!input[0].contains("un"));
        System.out.println(input[0].contains("un") ? "    The following task has been marked incomplete"
                : "    The following task has been marked complete");
        System.out.print("    ");
        System.out.println(TASKS[index].toString());
    }

    public static void addTask(String[] input) {
        String recombinedInput = input[0] + (input.length > 1 ? (" " + input[1]) : ""); // catch null condition
        TASKS[TASK_COUNT] = new Task(recombinedInput);
        printTask(TASK_COUNT);
        TASK_COUNT++;
    }

    public static void addTodo(String input) {
        TASKS[TASK_COUNT] = new Todo(input);
        System.out.println("    added todo: ");
        printTask(TASK_COUNT);
        TASK_COUNT++;
    }

    public static void addEvent(String input) {
        // parse input into description, from and to date
        String[] inputs = input.split("/from");
        String[] duration = inputs[1].split("/to");
        TASKS[TASK_COUNT] = new Event(inputs[0].trim(), duration[0].trim(), duration[1].trim());
        System.out.println("    added task: ");
        printTask(TASK_COUNT);
        TASK_COUNT++;
    }

    public static void addDeadline(String input) {
        String[] inputs = input.split("/by");
        TASKS[TASK_COUNT] = new Deadline(inputs[0].trim(), inputs[1].trim());
        System.out.println("    Added deadline:");
        printTask(TASK_COUNT);
        TASK_COUNT++;
    }

    public static String[] getUserInput() {
        // splits inputs into first word and everything else
        String[] words = SCANNER.nextLine().split(" ", 2);
        return words;
    }

    public static void exitJord() {
        System.out.println("    Bye, see you again!");
        System.exit(0);
    }

    private static void processInput(String[] input) {
        switch (input[0]) {
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
            addTodo(input[1]); // incomplete
            break;
        case "event":
            addEvent(input[1]); // incomplete
            break;
        case "deadline" :
            addDeadline(input[1]); // incomplete
            break;
        default:
            addTask(input);
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
