package ui;

import java.util.List;

import store.Task;
import store.TaskList;

/**
 * The Ui class handles interactions with the user, displaying messages
 * and information to the console.
 */
public class Ui {

    /**
     * Returns and prints the welcome message to the user.
     */
    public String showWelcomeMessage() {
        String message = "Hello! I'm Dusty\nHow can I help you?\n_______________";
        System.out.println(message);
        return message;
    }

    /**
     * Returns and prints the exit message to the user.
     */
    public String showExitMessage() {
        String message = "Bye! See you next time!";
        System.out.println(message);
        return message;
    }

    /**
     * Returns and prints an error message indicating that loading tasks from the file failed.
     */
    public String showLoadingError() {
        String message = "Error loading tasks from file.";
        System.out.println(message);
        return message;
    }

    /**
     * Returns and prints an error message to the user.
     *
     * @param message The error message to return.
     */
    public String showError(String message) {
        System.out.println(message);
        return message;
    }

    /**
     * Returns and prints a message confirming that a task has been added.
     *
     * @param task The added Task object.
     * @param size The current number of tasks in the list.
     */
    public String showTaskAdded(Task task, int size) {
        String message = "Got it. I've added this task:\n  " + task + "\nNow you have " + size + " tasks in the list.";
        System.out.println(message);
        return message;
    }

    /**
     * Returns and prints a message confirming that a task has been deleted.
     *
     * @param task The deleted Task object.
     * @param size The current number of tasks in the list.
     */
    public String showTaskDeleted(Task task, int size) {
        String message = "Okay. I've removed this task:\n  " + task + "\nNow you have " + size + " tasks in the list.";
        System.out.println(message);
        return message;
    }

    /**
     * Returns and prints a message confirming that a task has been marked as done.
     *
     * @param task The Task object that was marked as done.
     */
    public String showTaskMarked(Task task) {
        String message = "Nice! I've marked this task as done:\n  " + task;
        System.out.println(message);
        return message;
    }

    /**
     * Returns and prints a message confirming that a task has been marked as not done.
     *
     * @param task The Task object that was marked as not done.
     */
    public String showTaskUnmarked(Task task) {
        String message = "OK, I've marked this task as not done yet:\n  " + task;
        System.out.println(message);
        return message;
    }

    /**
     * Returns and prints the tasks that match a given search keyword.
     *
     * @param taskList The list of matching tasks to be displayed.
     */
    public String showMatchedTasks(TaskList taskList) {
        String message = "Here are the matching tasks in your list:\n" + taskList.listTasksAsString();
        System.out.println(message);
        return message;
    }

    /**
     * Returns and prints a message indicating that no tasks match the given keyword.
     *
     * @param str The search keyword that yielded no results.
     */
    public String showNoMatchMessage(String str) {
        String message = "There are no results matching the keyword (" + str + ") in your list.";
        System.out.println(message);
        return message;
    }

    /**
     * Returns and prints a message indicating that no tasks match given tag.
     * @param str The search tag that yielded no results.
     * @return Message indicating that no tasks match given tag.
     */
    public String showNoMatchTags(String str) {
        String message = "No tasks found with tag (" + str + ").";
        System.out.println(message);
        return message;
    }

    /**
     * Method to return message if there are matched tags
     * @param str
     * @param matchedTasks
     * @return
     */
    public String showMatchedTags(String str, List<Task> matchedTasks) {
        String message = "Tasks matching tags: " + str;
        StringBuilder output = new StringBuilder(message + "\n");
        for (int i = 0; i < matchedTasks.size(); i++) {
            output.append(i + 1).append(". ").append(matchedTasks.get(i)).append("\n");
        }
        return output.toString();
    }

    /**
     * method to return generic messages
     * @param str
     * @return message
     */
    public String showMessage(String str) {
        System.out.println(str);
        return str;
    }

}
