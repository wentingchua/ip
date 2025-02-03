package ui;

import store.Task;
import store.TaskList;

public class Ui {
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Dusty\nHow can I help you?\n_______________");
    }

    public void showExitMessage() {
        System.out.println("Bye! See you next time!");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println("Okay. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void showMatchedTasks(TaskList taskList) {
        System.out.println("Here are the matching tasks in your list:");
        taskList.listTasks();
    }

    public void showNoMatchMessage(String str) {
        System.out.println("There are no results matching the keyword (" + str + ") in your list.");
    }
}