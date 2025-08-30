import java.util.Scanner;

public class Jord {

    private static final int TASK_LIMIT = 100;
    private static Task[] TASKS = new Task[TASK_LIMIT];
    private static int TASK_COUNT = 0;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void printTasks() {
        int i = 0;
        while (TASKS[i] != null) {
            System.out.print("    " + (i+1) + ". ");
            TASKS[i].printStatus();
            i++;
        }
    }

    public static void taskMarker(String input) {
        String[] words = input.split(" ");
        int index = Integer.parseInt(words[1]) - 1;
        TASKS[index].setMarked(!words[0].contains("un"));
        System.out.println(words[0].contains("un") ? "    The following task has been marked incomplete"
                : "    The following task has been marked complete");
        System.out.print("    ");
        TASKS[index].printStatus();
    }

    public static void taskAdder(String input) {
        TASKS[TASK_COUNT] = new Task(input);
        System.out.println("    added: " + input);
        TASK_COUNT++;
    }

    public static String getUserInput() {
        String line = SCANNER.nextLine();
        return line;
    }

    public static void exitJord() {
        System.out.println("    Bye, see you again!");
        System.exit(0);
    }

    private static void processInput(String input) {
        if (input.equals("list")) {
            printTasks();
        } else if (input.contains("mark")) {
            taskMarker(input);
        } else if (input.equals("bye")) {
            exitJord();
        } else {
            taskAdder(input);
        }
    }


    public static void main(String[] args) {
        System.out.println("    Hello! I'm Jord\n    What can I do for you?");
        while (true) {
            String userInput = getUserInput();
            processInput(userInput);
        }
    }


}
