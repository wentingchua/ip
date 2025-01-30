package ui;

import backend.Parser;
import store.Storage;
import store.TaskList;

import java.util.Scanner;

public class Dusty {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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
