package jord.function;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String SAVE_PATH;
    private final File save;

    public Storage(String filepath) {
        SAVE_PATH = filepath;
        save = new File(SAVE_PATH);

    }

    /**
     * Takes in task list object and writes it to the save
     * @param taskList task list object to write to the save
     */
    public void writeSave(TaskList taskList) {
        ArrayList<Task> TASKS = taskList.getTasks();
        // overwrite save with current
        System.out.println("    Saving tasks!");
        try {
            // clears the file
            new FileWriter(SAVE_PATH, false).close();

            FileWriter fw = new FileWriter(SAVE_PATH, true);
            for (Task task : TASKS) {
//                System.out.println("save text: " + TASKS.get(i).save());
                fw.write(task.save());
                fw.write(System.lineSeparator());
            }
            fw.close();
            System.out.println("    Save success!");
        } catch (IOException     e) {
            System.out.println("    Saving failed! " + e.getMessage());
        }
    }

    /**
     * Attempts to load save from the stored filepath when Storage object was initialised
     * Returns and array list of type task storing the stored tasks within the save
     * If no save file is found, an exception is thrown
     * @return
     * @throws FileNotFoundException
     */
    public ArrayList<Task> loadSave() throws FileNotFoundException {
        // return ArrayList of the tasks
        ArrayList<Task> TASKS = new ArrayList<>();
        // save was instanced when Storage() is called
        Scanner s = new Scanner(save);
        int errorCount = 0;
        while (s.hasNext()) {
            String tempStr = s.nextLine();
            String[] splitInput = tempStr.split(";");
            Task tempTask;
            switch (splitInput[0]) {
            case "T":
                tempTask = new Todo();
                break;
            case "E":
                tempTask= new Event();
                break;
            case "D":
                tempTask = new Deadline();
                break;
            default:
                tempTask = new Task();
            }
            try {
                tempTask.load(splitInput);
                TASKS.add(tempTask);
            } catch (DateTimeParseException e) {
                errorCount++;
            }
        }
        if (errorCount > 0) {
            System.out.println("    A total of " + errorCount + " corrupted tasks were found and skipped!");
        }
        return TASKS;
    }

    /**
     * Attempts to create a save at the location specified when the storage object was initialised
     * If the function fails to create a save, an error message will be printed and the function will return
     */
    public void createSave() {
        if (!save.getParentFile().exists()) {
            save.getParentFile().mkdirs(); // create "data/" if missing
        }
        try {
            save.createNewFile(); // returns bool
        } catch (IOException e) {
            System.out.println("    Error creating save!");
            return;
        }
        System.out.println("    Save created!");
    }
}
