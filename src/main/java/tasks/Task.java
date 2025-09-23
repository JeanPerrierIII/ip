package tasks;

public class Task implements TaskIO {
    private String description = null;
    private boolean isMarked = false;

    public Task() {}

    public Task(String description) {
        this.description = description;
    }

    public void load(String[] splitInput) {
        isMarked = (Integer.parseInt(splitInput[1]) == 1);
        description = splitInput[2];
    }

    protected String saveHelper () {
        return (";" + (isMarked ? "1" : "0") + ";" + description);
    }

    public String save(){
        // T is reserved for todo
        return "X" + saveHelper();
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return ("[" + (isMarked ? "X] " : " ] ") + description);
    }
}
