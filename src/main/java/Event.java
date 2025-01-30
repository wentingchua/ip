import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeParser.formatDateTime(from) + " to: " + DateTimeParser.formatDateTime(to) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + getStatusIcon() + " | " + description + " | " + from + " | " + to;
    }
}