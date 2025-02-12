package commands;

import store.Storage;
import store.TaskList;
import store.Todo;
import ui.Ui;

import java.io.IOException;

/**
 * Class representing the Todo command
 */
public class TodoCommand extends Command {

    private String detailsWithNoSpace;
    private Todo todoTask;
    /**
     * Constructor for todo command
     * @param details of command
     */
    public TodoCommand(String details) {
        super(details);
        detailsWithNoSpace = details.trim();
        todoTask = new Todo(details);
    }

    /**
     * Method to execute command
     * @param tasks The TaskList to work with.
     * @param storage The Storage to save or load tasks
     * @param ui The messages the user will see
     * @return system message
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        if (detailsWithNoSpace.isEmpty()) {
            return showErrorMessage(ui);
        }
        addTodoTask(tasks);
        saveTaskList(tasks, storage);
        return showTodoTaskMessage(ui, tasks);
    }

    /**
     * Method to show error message from empty details
     * @param ui
     * @return system message
     */
    public String showErrorMessage(Ui ui) {
        return ui.showError("OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * Method to add todo task
     * @param tasks
     */
    public void addTodoTask(TaskList tasks) {
        tasks.addTask(todoTask);
    }

    /**
     * Method to save tasks
     * @param tasks
     * @param storage
     * @throws IOException
     */
    public void saveTaskList(TaskList tasks, Storage storage) throws IOException {
        super.saveTasks(tasks, storage);
    }

    /**
     * Method to return system message
     * @param ui
     * @param tasks
     * @return system message
     */
    public String showTodoTaskMessage(Ui ui, TaskList tasks) {
        return ui.showTaskAdded(todoTask, tasks.getSize());
    }
}
