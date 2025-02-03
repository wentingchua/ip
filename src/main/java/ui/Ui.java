package ui;

import store.Task;

/**
 * The Ui class handles interactions with the user, displaying messages
 * and information to the console.
 */
public class Ui {

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Dusty\nHow can I help you?\n_______________");
    }

    /**
     * Displays the exit message to the user.
     */
    public void showExitMessage() {
        System.out.println("Bye! See you next time!");
    }

    /**
     * Displays an error message indicating that loading tasks from the file failed.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The added Task object.
     * @param size The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The deleted Task object.
     * @param size The current number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("Okay. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The Task object that was marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The Task object that was marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }
}