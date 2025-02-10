package ui;

import store.Task;
import store.TaskList;

/**
 * The Ui class handles interactions with the user, displaying messages
 * and information to the console.
 */
public class Ui {

    /**
     * Returns the welcome message to the user.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Dusty\nHow can I help you?\n_______________";
    }

    /**
     * Returns the exit message to the user.
     */
    public String showExitMessage() {
        return "Bye! See you next time!";
    }

    /**
     * Returns an error message indicating that loading tasks from the file failed.
     */
    public String showLoadingError() {
        return "Error loading tasks from file.";
    }

    /**
     * Returns an error message to the user.
     *
     * @param message The error message to return.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Returns a message confirming that a task has been added.
     *
     * @param task The added Task object.
     * @param size The current number of tasks in the list.
     */
    public String showTaskAdded(Task task, int size) {
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message confirming that a task has been deleted.
     *
     * @param task The deleted Task object.
     * @param size The current number of tasks in the list.
     */
    public String showTaskDeleted(Task task, int size) {
        return "Okay. I've removed this task:\n  " + task + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message confirming that a task has been marked as done.
     *
     * @param task The Task object that was marked as done.
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Returns a message confirming that a task has been marked as not done.
     *
     * @param task The Task object that was marked as not done.
     */
    public String showTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Returns the tasks that match a given search keyword.
     *
     * @param taskList The list of matching tasks to be displayed.
     */
    public String showMatchedTasks(TaskList taskList) {
        return "Here are the matching tasks in your list:\n" + taskList.listTasksAsString();
    }

    /**
     * Returns a message indicating that no tasks match the given keyword.
     *
     * @param str The search keyword that yielded no results.
     */
    public String showNoMatchMessage(String str) {
        return "There are no results matching the keyword (" + str + ") in your list.";
    }
}
