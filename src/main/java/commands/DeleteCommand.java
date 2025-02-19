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
        if (hasEmptyError()) {
            return handleEmptyError(ui);
        }
        if (hasOutOfBoundsError(tasks)) {
            return handleOutOfBoundsError(ui);
        }
        deleteTaskFromList(tasks);
        saveTaskList(tasks, storage);
        return showDeletionMessage(ui, tasks);
    }

    /**
     * Method to check if input has task number
     * @return boolean true if input is empty
     */
    public boolean hasEmptyError() {
        return getDetails().isEmpty();
    }

    /**
     * Method to check if task number is within bounds
     * @param tasks
     * @return true if input is out of bound
     */
    public boolean hasOutOfBoundsError(TaskList tasks) {
        saveDeleteIndex();
        return deleteIndex < 1 || deleteIndex > tasks.getSize();
    }

    /**
     * Method to show error message when input is empty
     * @param ui
     * @return error message
     */
    public String handleEmptyError(Ui ui) {
        return ui.showError("OOPS!!!! delete command must be followed by task number");
    }

    /**
     * Method to show error message when input is out of bounds
     * @param ui
     * @return error message
     */
    public String handleOutOfBoundsError(Ui ui) {
        return ui.showError("OOPS!!! The task number you have given is out of bounds :(");
    }


    /**
     * Method to save delete index
     */
    public void saveDeleteIndex() {
        deleteIndex = Integer.parseInt(getDetails()) - 1;
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
