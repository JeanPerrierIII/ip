import java.util.Scanner;

public class Jord {
    public static void main(String[] args) {
        String[] tasks = new String[100];
        int taskCount = 0;
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("    Hello! I'm Jord\n    What can I do for you?");
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                // print consecutively, list of tasks
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("    " + (i+1) + ". " + tasks[i]);
                }
            }
            else {
                // add to list, increment taskCount
                tasks[taskCount] = line;
                System.out.println("    added: " + line);
                taskCount++;
            }
            line = in.nextLine();
        }

        System.out.println("    Bye, see you again!");
    }
}
