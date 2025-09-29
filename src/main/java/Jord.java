import jord.function.Parser;
import jord.function.Storage;
import jord.function.TaskList;
import jord.function.Ui;

import java.io.FileNotFoundException;


public class Jord {
    private static final String DEFAULT_SAVE_PATH = "data/jord.txt";
    private final Storage storage;
    private TaskList tasks;

    public Jord(String filepath) {
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.loadSave());
            Ui.printSaveFound();
        } catch (FileNotFoundException e) {
            // display loading error
            Ui.printNoSave();
            storage.createSave();
            tasks = new TaskList();
        }
    }

    private void exit() {
        storage.writeSave(tasks);
        Ui.printGoodbye();
        System.exit(0);
    }

    private void run() {
        Ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            String[] userInput = Parser.getUserInput();
            Parser.processInput(userInput, tasks);
            isExit = Parser.isExit(userInput[0]);
        }
        exit();
    }

    public static void main(String[] args) {
        new Jord(DEFAULT_SAVE_PATH).run();
    }
}
