package tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Event extends Task{
    protected LocalDateTime from = null;
    protected LocalDateTime to = null;

    public Event() {}
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void load(String[] splitInput) throws DateTimeParseException {
        super.load(splitInput);
        from = LocalDateTime.parse(splitInput[3]);
        to = LocalDateTime.parse(splitInput[4]);
    }

    @Override
    public String save() {
        return "E" + super.saveHelper() + ";" + from + ";" + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"))
                + "H to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"))
                + "H)";
    }
}
