package store;

import tag.Tag;

import java.util.ArrayList;
import java.util.List;

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
        return "[T]" + super.toString() + " " + super.tagsToString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + getStatusIcon() + " | " + description + " " + super.tagsToString();
    }
    
}
