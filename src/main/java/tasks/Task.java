package tasks;

public class Task implements TaskIO {
    private String description = null;
    private boolean isMarked = false;

    public Task() {}

    public Task(String description) {
        this.description = description;
    }

    /**
     * Scans command line input and splits it into a string array of the first word (usually command)
     * and the rest of the input (usually the arguments)
     * if the input is one word long, then a array of size 1 is returned
     * @param splitInput user input from command line
     */
    public void load(String[] splitInput) {
        isMarked = (Integer.parseInt(splitInput[1]) == 1);
        description = splitInput[2];
    }

    protected String saveHelper () {
        return (";" + (isMarked ? "1" : "0") + ";" + description);
    }

    /**
     * Returns a string encoding the task information to be written into a text file
     * @return the encoded task
     */
    public String save(){
        // T is reserved for todo
        return "X" + saveHelper();
    }

    /**
     * Sets the task to either marked or unmarked
     * @param isMark the mark value
     */
    public void setMarked(boolean isMark) {
        isMarked = isMark;
    }

    /**
     * returns the task description in the form of a String
     * @return the string
     */
    public String getDescription() {
        return description;
    }


    public String toString() {
        return ("[" + (isMarked ? "X] " : " ] ") + description);
    }
}
