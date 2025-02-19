package commands;

import store.Storage;
import store.TaskList;
import ui.Ui;

/**
 * Class representing a Bye command
 */
public class ByeCommand extends Command {
    /**
     * Constructor for Bye class
     */
    public ByeCommand() {
        super("");
    }

    /**
     * Method to execute
     * @param tasks The TaskList to work with.
     * @param storage The Storage to save or load tasks
     * @param ui The messages the user will see
     * @return ui message only
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.showExitMessage();
    }

    /**
     * Method to check if it is a bye command
     * @return true since this is a Bye class
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
