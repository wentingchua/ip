package backend;
import java.time.LocalDateTime;

import commands.*;
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
                Command unmark = new UnmarkCommand(details);
                return unmark.execute(tasks, storage, ui);
            case "delete":
                Command delete = new DeleteCommand(details);
                return delete.execute(tasks, storage, ui);
            case "todo":
                Command todo = new TodoCommand(details);
                return todo.execute(tasks, storage, ui);
            case "deadline":
                Command deadline = new DeadlineCommand(details);
                return deadline.execute(tasks, storage, ui);
            case "event":
                Command event = new EventCommand(details);
                return event.execute(tasks, storage, ui);
            case "find":
                Command find = new FindCommand(details);
                return find.execute(tasks, storage, ui);
            default:
                return ui.showError("OOPS!!! I'm sorry, but I don't know what that means :(");
            }
        } catch (Exception e) {
            return ui.showError("Invalid input or error processing command. "
                    + "Note that date and time should be in dd/mm/yyyy ttmm format");
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
