package commands;

import java.io.IOException;

import store.Storage;
import store.Task;
import store.TaskList;
import ui.Ui;

/**
 * Class representing the Find command
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand
     * @param details The keyword to search for
     */
    public FindCommand(String details) {
        super(details);
        this.keyword = details.trim();
    }

    /**
     * Method to check if search keyword is empty
     * @return True if keyword is empty
     */
    public boolean isEmptyKeyword() {
        return keyword.isEmpty();
    }

    /**
     * Method to search for matching tasks
     * @param tasks TaskList to search
     * @return TaskList of matching tasks
     */
    private TaskList getMatchingTasks(TaskList tasks) {
        TaskList relatedTasks = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                relatedTasks.addTask(task);
            }
        }
        return relatedTasks;
    }

    /**
     * Method to execute command
     * @param tasks The TaskList to work with.
     * @param storage The Storage to save or load tasks (not used here)
     * @param ui The messages the user will see
     * @return system message
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        if (isEmptyKeyword()) {
            return ui.showError("OOPS!!! The description of a find cannot be empty.");
        }
        TaskList relatedTasks = getMatchingTasks(tasks);
        if (relatedTasks.getSize() > 0) {
            return ui.showMatchedTasks(relatedTasks);
        } else {
            return ui.showNoMatchMessage(keyword);
        }
    }
}
