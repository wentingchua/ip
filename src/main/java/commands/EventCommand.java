package commands;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import backend.DateTimeParser;
import store.Event;
import store.Storage;
import store.TaskList;
import tag.Tag;
import ui.Ui;

/**
 * Class representing the Event command
 */
public class EventCommand extends Command {
    private String eventTask;
    private String from;
    private String to;
    private LocalDateTime start;
    private LocalDateTime end;
    private Event eventTaskObject;

    private final List<Tag> tags;

    /**
     * Constructor for EventCommand
     * @param details The event details string
     */
    public EventCommand(String details, List<Tag> tags) {
        super(details);
        this.tags = tags;
    }

    /**
     * Method to break down task details into task, start time, and end time
     * @param details of the task
     */
    private void breakDownTask(String details) {
        String[] eventParts = details.split("/from|/to");
        eventTask = eventParts[0].trim();
        from = eventParts[1].trim();
        to = eventParts[2].trim();
        start = DateTimeParser.parseDateTime(from);
        end = DateTimeParser.parseDateTime(to);
    }

    /**
     * Method to check if command was formatted correctly
     * @return True if incorrectly formatted
     */
    public boolean isWrongFormat() {
        if (getDetails().isEmpty()) {
            return true;
        } else if (!getDetails().contains("/from")) {
            return true;
        } else if (!getDetails().contains("/to?)")) {
            return true;
        }
        return false;
    }

    /**
     * Method to show error message for incorrect format
     * @param ui UI to display the message
     * @return system error message
     */
    public String showErrorMessage(Ui ui) {
        return ui.showError("OOPS!!! Please follow this format: "
                + "event task /from dd/mm/yyyy ttmm /to dd/mm/yyyy ttmm");
    }

    /**
     * Method to add event task to the task list
     * @param tasks TaskList to add the event
     */
    private void addEventTask(TaskList tasks) {
        eventTaskObject = new Event(eventTask, tags, start, end);
        tasks.addTask(eventTaskObject);
    }

    /**
     * Method to show event added message
     * @param ui UI to display the message
     * @param tasks TaskList to check size
     * @return system message
     */
    private String showEventMessage(Ui ui, TaskList tasks) {
        return ui.showTaskAdded(eventTaskObject, tasks.getSize());
    }

    /**
     * Method to execute command
     * @param tasks The TaskList to work with.
     * @param storage The Storage to save or load tasks
     * @param ui The messages the user will see
     * @return system message
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        if (isWrongFormat()) {
            return showErrorMessage(ui);
        }
        breakDownTask(getDetails());
        addEventTask(tasks);
        super.saveTasks(tasks, storage);
        return showEventMessage(ui, tasks);
    }
}
