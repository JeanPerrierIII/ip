package tasks;

public class Deadline extends Task {

    protected String by = null;

    public Deadline() {}
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void load(String[] splitInput) {
        super.load(splitInput);
        by = splitInput[3];
    }

    @Override
    public String save() {
        return "D" + super.saveHelper() + ";" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}