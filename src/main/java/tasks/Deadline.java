package tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected LocalDateTime by = null;

    public Deadline() {}
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public void load(String[] splitInput) throws DateTimeParseException {
        super.load(splitInput);
        by = LocalDateTime.parse(splitInput[3]);
    }

    @Override
    public String save() {
        return "D" + super.saveHelper() + ";" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm")) + "H)";
    }
}