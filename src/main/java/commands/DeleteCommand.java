package commands;

import store.Storage;
import store.TaskList;
import store.Task;
import ui.Ui;

import java.io.IOException;

/**
 * Class representing the Delete command
 */
public class DeleteCommand extends Command {

    private int deleteIndex;
    private Task deletedTask;

    /**
     * Constructor for Delete command
     * @param details of command
     */
    public DeleteCommand(String details) {
        super(details);
        deleteIndex = Integer.parseInt(details) - 1;
    }

    /**
     * Method to execute command
     * @param tasks The TaskList to work with.
     * @param storage The Storage to save or load tasks
     * @param ui The messages the user will see
     * @return system message
     * @throws IOException
     */
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        deleteTaskFromList(tasks);
        saveTaskList(tasks, storage);
        return showDeletionMessage(ui, tasks);
    }

    /**
     * Method to delete task
     * @param tasks
     */
    public void deleteTaskFromList(TaskList tasks) {
        deletedTask = tasks.getTask(deleteIndex);
        tasks.deleteTask(deleteIndex);
    }

    /**
     * Method to save task list
     * @param tasks
     * @param storage
     * @throws IOException
     */
    public void saveTaskList(TaskList tasks, Storage storage) throws IOException {
        super.saveTasks(tasks, storage);
    }

    /**
     * Method to return delete message
     * @param ui
     * @param tasks
     * @return system message
     */
    public String showDeletionMessage(Ui ui, TaskList tasks) {
        return ui.showTaskDeleted(deletedTask, tasks.getSize());
    }

}
