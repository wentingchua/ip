package store;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import backend.DateTimeParser;


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
    public Deadline(String description, LocalDateTime by) {
        super(description);
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
