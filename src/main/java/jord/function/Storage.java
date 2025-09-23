package jord.function;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String SAVE_PATH;
    private final File save;

    public Storage(String filepath) {
        // replaces saveSetup
        SAVE_PATH = filepath;
        save = new File(SAVE_PATH);

    }

    public void writeSave(TaskList taskList) {
        ArrayList<Task> TASKS = taskList.getTasks();
        // overwrite save with current
        System.out.println("    Saving tasks!");
        try {
            // clears the file
            new FileWriter(SAVE_PATH, false).close();

            FileWriter fw = new FileWriter(SAVE_PATH, true);
            for (int i = 0; i < TASKS.size(); i++) {
//                System.out.println("save text: " + TASKS.get(i).save());
                fw.write(TASKS.get(i).save());
                fw.write(System.lineSeparator());
            }
            fw.close();
            System.out.println("    Save success!");
        } catch (IOException     e) {
            System.out.println("    Saving failed! " + e.getMessage());
        }
    }

    public ArrayList<Task> loadSave() throws FileNotFoundException {
        // return ArrayList of the tasks
        ArrayList<Task> TASKS = new ArrayList<>();
        // save was instanced when Storage() is called
        Scanner s = new Scanner(save);
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
            tempTask.load(splitInput);
            TASKS.add(tempTask);
        }
        return TASKS;
    }

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

//    public static void saveSetup() {
//        // format: <task type>;<completion status>;<desc>;<date 1>;<date 2>\n
//    }
}
