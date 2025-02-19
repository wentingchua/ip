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
        if (getDetails().isEmpty()) {
            return showErrorMessage(ui);
        }
        saveUnmarkIndex();
        tasks.markTaskAsNotDone(unmarkIndex);
        super.saveTasks(tasks, storage);
        return ui.showTaskUnmarked(tasks.getTask(unmarkIndex));
    }

    /**
     * Method to return error message
     * @param ui
     * @return error message
     */
    public String showErrorMessage(Ui ui) {
        return ui.showError("OOPS!!! unmark should be followed by task number");
    }

    public void saveUnmarkIndex() {
        this.unmarkIndex = Integer.parseInt(getDetails()) - 1;
    }
}
