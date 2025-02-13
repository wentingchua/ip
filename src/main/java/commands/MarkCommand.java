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
        this.markIndex = Integer.parseInt(details) - 1;
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
        tasks.markTaskAsDone(markIndex);
        super.saveTasks(tasks, storage);
        return ui.showTaskMarked(tasks.getTask(markIndex));
    }
}
