package store;

/**
 * Represents a todo
 */
public class Todo extends Task {
    /**
     * Constructor for Todo
     * @param description of task
     */
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + getStatusIcon() + " | " + description;
    }
}
