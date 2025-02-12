package backend;
import java.time.LocalDateTime;

import commands.Command;
import commands.ByeCommand;
import commands.ListCommand;
import commands.MarkCommand;
import store.Deadline;
import store.Event;
import store.Storage;
import store.Task;
import store.TaskList;
import store.Todo;
import ui.Ui;


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
    public static String handleCommand(String input, TaskList tasks, Storage storage, Ui ui) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String details = parts.length > 1 ? parts[1] : "";

        try {
            switch (command) {
            case "bye":
                Command bye = new ByeCommand();
                return bye.execute(tasks, storage, ui);
            case "list":
                Command list = new ListCommand();
                return list.execute(tasks, storage, ui);
            case "mark":
                Command mark = new MarkCommand(details);
                return mark.execute(tasks, storage, ui);
            case "unmark":
                int unmarkIndex = Integer.parseInt(details) - 1;
                tasks.markTaskAsNotDone(unmarkIndex);
                storage.saveTasks(tasks.getTasks());
                return ui.showTaskUnmarked(tasks.getTask(unmarkIndex));
            case "delete":
                int deleteIndex = Integer.parseInt(details) - 1;
                Task deletedTask = tasks.getTask(deleteIndex);
                tasks.deleteTask(deleteIndex);
                storage.saveTasks(tasks.getTasks());
                return ui.showTaskDeleted(deletedTask, tasks.getSize());
            case "todo":
                if (details.trim().isEmpty()) {
                    return ui.showError("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    Task todo = new Todo(details);
                    tasks.addTask(todo);
                    storage.saveTasks(tasks.getTasks());
                    return ui.showTaskAdded(todo, tasks.getSize());
                }
            case "deadline":
                if (!details.contains("/by")) {
                    return ui.showError("OOPS!!! Please follow this format: deadline task /by date");
                } else {
                    String[] deadlineParts = details.split("/by", 2);
                    String task = deadlineParts[0].trim();
                    String by = deadlineParts[1].trim();
                    LocalDateTime deadline = DateTimeParser.parseDateTime(by);
                    Task deadlineTask = new Deadline(task, deadline);
                    tasks.addTask(deadlineTask);
                    storage.saveTasks(tasks.getTasks());
                    return ui.showTaskAdded(deadlineTask, tasks.getSize());
                }
            case "event":
                if (!details.contains("/from") || !details.contains("/to")) {
                    return ui.showError("OOPS!!! Please follow this format: event task /from time /to time");
                } else {
                    String[] eventParts = details.split("/from|/to");
                    String task = eventParts[0].trim();
                    String from = eventParts[1].trim();
                    String to = eventParts[2].trim();
                    LocalDateTime start = DateTimeParser.parseDateTime(from);
                    LocalDateTime end = DateTimeParser.parseDateTime(to);
                    Task eventTask = new Event(task, start, end);
                    tasks.addTask(eventTask);
                    storage.saveTasks(tasks.getTasks());
                    return ui.showTaskAdded(eventTask, tasks.getSize());
                }
            case "find":
                if (details.trim().isEmpty()) {
                    ui.showError("OOPS!!! The description of a find cannot be empty.");
                } else {
                    TaskList relatedTasks = new TaskList();
                    for (int i = 0; i < tasks.getSize(); i++) {
                        Task task = tasks.getTask(i);
                        if (task.getDescription().contains(details)) {
                            relatedTasks.addTask(task);
                        }
                    }
                    if (relatedTasks.getSize() > 0) {
                        return ui.showMatchedTasks(relatedTasks);
                    } else {
                        return ui.showNoMatchMessage(details);
                    }
                }
                break;
            default:
                return ui.showError("OOPS!!! I'm sorry, but I don't know what that means :(");
            }
        } catch (Exception e) {
            return ui.showError("Invalid input or error processing command. "
                    + "Note that date and time should be in dd/mm/yyyy tt/mm format");
        }
        return "OOPS!!! I'm sorry, but I don't know what that means";
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
                task = new Todo(description);
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
            default:
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
