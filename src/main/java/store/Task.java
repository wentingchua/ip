package store;

/**
 * Represents a todo object.
 * @author Wen Ting
 * @version 1.0
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Returns String status icon that corresponds to status of task.
     * Can be printed as output.
     * @return The String that represents status of task
     */

    public String getDescription() {
        return this.description;
    }


    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Sets boolean isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Sets boolean isDone to false
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + description;
    }

    public String toSaveFormat() {
        return "T" + " | " + getStatusIcon() + " | " + description;
    }
}