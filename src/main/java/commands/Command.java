package commands;

import store.Storage;
import store.Task;
import store.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Abstract class representing a command.
 */
public abstract class Command {
    private String details;

    /**
     * Constructor for Command class
     * @param details following the command
     */
    public Command(String details) {
        this.details = details;
    }

    public String getDetails() {
        return this.details;
    }

    /**
     * Method that executes the tasks related to the command
     * @param tasks The TaskList to work with.
     * @param storage The Storage to save or load tasks
     * @param ui The messages the user will see
     * @return
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui) throws IOException;

    /**
     * Indicates whether the command is a Bye command
     * @return true if command is a Bye command
     */
    public boolean isBye() {
        return false;
    }

    public void saveTasks(TaskList tasks, Storage storage) throws IOException {
        storage.saveTasks(tasks.getTasks());
    }
}
