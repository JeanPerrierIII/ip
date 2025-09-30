package jord.function;

import tasks.CommandType;

public class Ui {
    public static final String WELCOME_MESSAGE = "    Hello! I'm Jord" +
            "\n    Use \"help\" to list available commands!" +
            "\n    What can I do for you?";
    public static final String BYE_MESSAGE = "    Bye, see you again!";
    public static final String TASK_CORRECT_USAGE = "add <description>";
    public static final String TODO_CORRECT_USAGE = "todo <description>";
    public static final String EVENT_CORRECT_USAGE = "event <description> /from <date 1> /to <date 2>";
    public static final String DEADLINE_CORRECT_USAGE = "deadline <description> /by <date>";
    public static final String MARKED_COMPLETE = "    The following task has been marked complete";
    public static final String MARKED_INCOMPLETE = "    The following task has been marked incomplete";
    public static final String MARK_CORRECT_USAGE = "mark/unmark <index of task>";
    public static final String LIST_EMPTY_MESSAGE =
            "    No tasks have been added, use todo, event or deadline to add some";
    public static final String DELETE_CORRECT_USAGE = "delete <index of task>";

    public static final String LOGO = "    ______  _______    _______   ______\n" +
            "   /      \\/       \\\\//       \\_/      \\\\\n" +
            "  /       /        ///        /        //\n" +
            "_/      //         /        _/         /\n" +
            "\\______//\\________/\\____/___/\\________/";


    private static void printLogo() {
        System.out.println(LOGO);
    }

    public static void printHelp() {
        System.out.println("    Available commands:");
        System.out.println("    help - displays this message");
        System.out.println("    list - lists all tasks");
        System.out.println("    bye - shuts down the program");
        System.out.println("    find <description> - search for tasks containing the provided description");
        System.out.println("\n    To add tasks, use the following:");
        for (CommandType val: CommandType.values()) {
            printCorrectUsage(val, true);
        }
    }

    /**
     * Prints out the correct usage for the given command
     * @param type command type to print the correct usage of
     */
    public static void printCorrectUsage(CommandType type, boolean isHelpMessage) {
        System.out.print("    ");
        if (!isHelpMessage) {
            System.out.print("Correct usage: ");
        }
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

    public static void printNoSave() {
        System.out.println("    No save found. Creating!");
    }

    public static void printSaveFound() {
        System.out.println("    Save found and loaded!");
    }

    public static void printWelcome() {
        printLogo();
        System.out.println(Ui.WELCOME_MESSAGE);
    }

    public static void printGoodbye() {
        System.out.print(Ui.BYE_MESSAGE);
    }


}
