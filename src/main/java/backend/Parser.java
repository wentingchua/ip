package backend;

import store.*;
import ui.*;

import java.time.LocalDateTime;

/**
 * The Parser class handles parsing user input and executing commands.
 * It interacts with the TaskList, Storage, and Ui classes to manage tasks.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param input   The user input string.
     * @param tasks   The TaskList object to manage tasks.
     * @param storage The Storage object to save and load tasks.
     * @param ui      The Ui object to display messages to the user.
     */
    public static void handleCommand(String input, TaskList tasks, Storage storage, Ui ui) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String details = parts.length > 1 ? parts[1] : "";

        try {
            switch (command) {
                case "list":
                    tasks.listTasks();
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(details) - 1;
                    tasks.markTaskAsDone(markIndex);
                    ui.showTaskMarked(tasks.getTask(markIndex));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(details) - 1;
                    tasks.markTaskAsNotDone(unmarkIndex);
                    ui.showTaskUnmarked(tasks.getTask(unmarkIndex));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case "delete":
                    int deleteIndex = Integer.parseInt(details) - 1;
                    Task deletedTask = tasks.getTask(deleteIndex);
                    tasks.deleteTask(deleteIndex);
                    ui.showTaskDeleted(deletedTask, tasks.getSize());
                    storage.saveTasks(tasks.getTasks());
                    break;
                case "todo":
                    if (details.trim().isEmpty()) {
                        ui.showError("OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        Task todo = new Task(details);
                        tasks.addTask(todo);
                        ui.showTaskAdded(todo, tasks.getSize());
                        storage.saveTasks(tasks.getTasks());
                    }
                    break;
                case "deadline":
                    if (!details.contains("/by")) {
                        ui.showError("OOPS!!! Please follow this format: deadline task /by date");
                    } else {
                        String[] deadlineParts = details.split("/by", 2);
                        String task = deadlineParts[0].trim();
                        String by = deadlineParts[1].trim();
                        LocalDateTime deadline = DateTimeParser.parseDateTime(by);
                        Task deadlineTask = new Deadline(task, deadline);
                        tasks.addTask(deadlineTask);
                        ui.showTaskAdded(deadlineTask, tasks.getSize());
                        storage.saveTasks(tasks.getTasks());
                    }
                    break;
                case "event":
                    if (!details.contains("/from") || !details.contains("/to")) {
                        ui.showError("OOPS!!! Please follow this format: event task /from time /to time");
                    } else {
                        String[] eventParts = details.split("/from|/to");
                        String task = eventParts[0].trim();
                        String from = eventParts[1].trim();
                        String to = eventParts[2].trim();
                        LocalDateTime start = DateTimeParser.parseDateTime(from);
                        LocalDateTime end = DateTimeParser.parseDateTime(to);
                        Task eventTask = new Event(task, start, end);
                        tasks.addTask(eventTask);
                        ui.showTaskAdded(eventTask, tasks.getSize());
                        storage.saveTasks(tasks.getTasks());
                    }
                    break;
                default:
                    ui.showError("OOPS!!! I'm sorry, but I don't know what that means :(");
                    break;
            }
        } catch (Exception e) {
            ui.showError("Invalid input or error processing command. Note that date and time should be in dd/mm/yyyy ttmm format");
        }
    }

    /**
     * Parses a task from a line read from the data file.
     * The expected format is: "T/D/E | 0/1 | description | date/time | date/time"
     * where 0 represents not done and 1 represents done.
     *
     * @param line The line read from the file.
     * @return The parsed Task object, or null if the line is invalid.
     */
    public static Task parseTaskFromFile(String line) {

        String[] parts = line.split(" \\| ");

        if (parts.length < 3) { // Ensure the expected number of parts
            System.out.println("Invalid task format! Skipping line: " + line);
            return null; // Ignore malformed lines
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task = null;

        try {
            switch (type) {
                case "T":
                    task = new Task(description);
                    break;
                case "D":
                    LocalDateTime by = DateTimeParser.parseDateTime(parts[3]);
                    task = new Deadline(description, by);
                    break;
                case "E":
                    LocalDateTime from = DateTimeParser.parseDateTime(parts[3]);
                    LocalDateTime to = DateTimeParser.parseDateTime(parts[4]);
                    task = new Event(description, from, to);
                    break;
            }

            if (task != null && isDone) {
                task.markAsDone();
            }
        } catch (Exception e) {
            System.out.println("Error parsing task: " + line);
            e.printStackTrace();
            return null;
        }

        return task;
    }

}