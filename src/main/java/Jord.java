import java.util.Scanner;

public class Jord {

    public static void printTasks(Task[] tasks) {
        int i = 0;
        while (tasks[i] != null) {
            System.out.print("    " + (i+1) + ". ");
            tasks[i].printStatus();
            i++;
        }
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("    Hello! I'm Jord\n    What can I do for you?");
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printTasks(tasks);
            }
            else if (line.contains("mark")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].setMarked(!words[0].contains("un"));
                System.out.println(words[0].contains("un") ? "    The following task hae been marked incomplete" : "    The following task has been marked complete");
                System.out.print("    ");
                tasks[index].printStatus();
            }
            else {
                // add to list, increment taskCount
                tasks[taskCount] = new Task(line);
                System.out.println("    added: " + line);
                taskCount++;
            }
            line = in.nextLine();
        }

        System.out.println("    Bye, see you again!");
    }
}
