package tasks;

public class Todo extends Task{
    public Todo() {}
    public Todo(String description) {
        super(description);
    }

    @Override
    public String save() {

        return "T" + super.saveHelper();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
