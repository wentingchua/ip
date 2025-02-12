package commands;

import store.Storage;
import store.TaskList;
import ui.Ui;

/**
 * Class representing the List command
 */
public class ListCommand extends Command {
    /**
     * Constructor for ListCommand
     */
    public ListCommand() {
        super("");
    }

    /**
     *
     * @param tasks The TaskList to work with.
     * @param storage The Storage to save or load tasks
     * @param ui The messages the user will see
     * @return String of list of tasks
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return tasks.listTasksAsString();
    }
}
