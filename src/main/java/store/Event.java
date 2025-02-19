package store;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import backend.DateTimeParser;
import tag.Tag;

/**
 * Represents an event
 * @author Wen Ting
 * @version 1.0
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    /**
     * Constructor for Event
     * @param description description of event
     * @param from start of event
     * @param to end of event
     */
    public Event(String description, List<Tag> tags, LocalDateTime from, LocalDateTime to) {
        super(description, tags);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeParser.formatDateTime(from)
                + " to: " + DateTimeParser.formatDateTime(to) + ")";
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from.format(formatter) + " | " + to.format(formatter);
    }
}
