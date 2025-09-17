package tasks;

public class Event extends Task{
    protected String from = null;
    protected String to = null;

    public Event() {}
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void load(String[] splitInput) {
        super.load(splitInput);
        from = splitInput[3];
        to = splitInput[4];
    }

    @Override
    public String save() {
        return "E" + super.saveHelper() + ";" + from + ";" + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
