package commands;

import store.Storage;
import store.Task;
import store.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Class representing the Unmark command
 */
public class UnmarkCommand extends Command {
    private int unmarkIndex;

    /**
     * Constructor for UnmarkCommand
     * @param details of task
     */
    public UnmarkCommand(String details) {
        super(details);
        this.unmarkIndex = Integer.parseInt(details) - 1;
    }

    /**
     *
     * @param tasks The TaskList to work with.
     * @param storage The Storage to save or load tasks
     * @param ui The messages the user will see
     * @return message from system
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Storage storage,Ui ui) throws IOException {
        tasks.markTaskAsNotDone(unmarkIndex);
        super.saveTasks(tasks, storage);
        return ui.showTaskUnmarked(tasks.getTask(unmarkIndex));
    }
}
