package ui;

import java.util.Scanner;

import backend.Parser;
import store.Storage;
import store.TaskList;

/**
 * The main class for Dusty
 */
public class Dusty {
    public final Storage storage;
    public final Ui ui;
    private TaskList tasks;

    /**
     * Initializes a new instance of the Dusty application.
     *
     * @param filePath The file path for storing task data.
     */
    public Dusty(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Runs the main program loop for the Dusty application.
     * It continuously reads user input, processes commands, and handles program exit.
     */
    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                ui.showExitMessage();
                break;
            }
            Parser.handleCommand(input, tasks, storage, ui);
        }
    }

    public TaskList getTasks() {
        return this.tasks;
    }
    public String getResponse(String input) {
        return "Dusty heard: " + input;
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}

