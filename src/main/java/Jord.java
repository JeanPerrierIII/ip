import jord.function.Parser;
import jord.function.Storage;
import jord.function.TaskList;
import jord.function.Ui;

import java.io.FileNotFoundException;


public class Jord {
    private static final String DEFAULT_SAVE_PATH = "data/jord.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Jord(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.loadSave());
            ui.saveFound();
        } catch (FileNotFoundException e) {
            // display loading error
            ui.noSave();
            storage.createSave();
            tasks = new TaskList();
        }
    }

    private void exit() {
        storage.writeSave(tasks);
        ui.printGoodbye();
        System.exit(0);
    }

    private void run() {
        ui.printWelcome();
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
