package store;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import backend.DateTimeParser;
import tag.Tag;


/**
 * Represents a deadline
 * @author Wen Ting
 * @version 1.0
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for deadline
     * @param description description of deadline
     * @param by due date for deadline
     */
    public Deadline(String description, List<Tag> tags, LocalDateTime by) {
        super(description, tags);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.formatDateTime(by) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + getStatusIcon() + " | " + description + " | "
                + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

}
