package commands;

import store.Storage;
import store.Task;
import store.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Class representing the Mark command
 */
public class MarkCommand extends Command {

    private int markIndex;

    /**
     * Constructor for MarkCommand
     * @param details of command
     */
    public MarkCommand(String details) {
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
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        if (getDetails().isEmpty()) {
            return showErrorMessage(ui);
        }
        saveMarkIndex();
        tasks.markTaskAsDone(markIndex);
        super.saveTasks(tasks, storage);
        return ui.showTaskMarked(tasks.getTask(markIndex));
    }

    /**
     * Method to return error message
     * @param ui
     * @return error message
     */
    public String showErrorMessage(Ui ui) {
        return ui.showError("OOPS!!! mark should be followed by task number");
    }

    /**
     * Method to save the index of task to be marked
     */
    public void saveMarkIndex() {
        this.markIndex = Integer.parseInt(getDetails()) - 1;
    }
}
