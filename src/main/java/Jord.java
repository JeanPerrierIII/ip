import java.util.Scanner;

public class Jord {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("    Hello! I'm Jord\n    What can I do for you?");
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println("    " + line);
            line = in.nextLine();
        }
        System.out.println("    Bye! See you again!");
    }
}
