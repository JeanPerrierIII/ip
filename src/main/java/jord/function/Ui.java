package jord.function;

public class Ui {
    public static final String WELCOME_MESSAGE = "    Hello! I'm Jord\n    What can I do for you?";
    public static final String BYE_MESSAGE = "    Bye, see you again!";
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


    private void printLogo() {
        System.out.println("    ______  _______    _______   ______  \n" +
                "   /      \\/       \\\\//       \\_/      \\\\\n" +
                "  /       /        ///        /        //\n" +
                "_/      //         /        _/         / \n" +
                "\\______//\\________/\\____/___/\\________/  ");
    }

    public void noSave() {
        System.out.println("    No save found. Creating!");
    }

    public void saveFound() {
        System.out.println("    Save found and loaded!");
    }

    public void printWelcome() {
        printLogo();
        System.out.println(Ui.WELCOME_MESSAGE);
    }

    public void printGoodbye() {
        System.out.print(Ui.BYE_MESSAGE);
    }
}
