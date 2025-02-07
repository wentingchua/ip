package ui;

import java.util.Scanner;

import backend.Parser;
import store.Storage;
import store.TaskList;

/**
 * The main class for Dusty
 */
public class Dusty {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    /**
     * Initializes a new instance of the Dusty application.
     *
     * @param filePath The file path for storing task data.
     */
    public Dusty(String filePath) {
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

    public static void main(String[] args) {
        new Dusty("data/tasks.txt").run();
    }
}
