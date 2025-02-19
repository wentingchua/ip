package store;

import java.util.List;

import tag.Tag;

/**
 * Represents a todo
 */
public class Todo extends Task {

    /**
     * Constructor for Todo
     * @param description of task
     */
    public Todo(String description, List<Tag> tags) {
        super(description, tags);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString() + " ";
    }

    @Override
    public String toSaveFormat() {
        return "T | " + getStatusIcon() + " | " + description + " ";
    }
}
