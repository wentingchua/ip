package commands;

import backend.DateTimeParser;
import store.Storage;
import store.TaskList;
import store.Deadline;
import tag.Tag;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class representing the Deadline command
 */
public class DeadlineCommand extends Command {
    private String deadlineTask;
    private String by;
    private LocalDateTime formattedBy;

    private Deadline deadlineTaskObject;
    private final List<Tag> tags;

    /**
     * Constructor for deadline command
     * @param details
     */
    public DeadlineCommand(String details, List<Tag> tags) {
        super(details);
        breakDownTask(details);
        this.tags = tags;
    }

    /**
     * Method to break down task description into task and due date
     * @param details of task
     */
    public void breakDownTask(String details) {
        String[] deadlineParts = details.split("/by", 2);
        deadlineTask = deadlineParts[0].trim();
        by = deadlineParts[1].trim();
    }

    /**
     * Method to format deadline in dd/mm/yyyy tt/mm format
     * @param by
     * @return formatted deadline
     */
    public void formatDeadline(String by) {
        formattedBy = DateTimeParser.parseDateTime(by);
    }

    /**
     * Method to check if command was formatted correctly
     * @return False if formatted correctly
     */
    public boolean isWrongFormat() {
        return !super.getDetails().contains("/by");
    }

    /**
     * Method to show error message from wrong deadline format
     * @param ui
     * @return system message
     */
    public String showErrorMessage(Ui ui) {
        return ui.showError("OOPS!!! Please follow this format: deadline task /by date");
    }

    /**
     * Method to add deadline task
     * @param tasks
     */
    public void addDeadlineTask(TaskList tasks) {
        deadlineTaskObject = new Deadline(deadlineTask, tags, formattedBy);
        tasks.addTask(deadlineTaskObject);
    }

    /**
     * Method to show deadline added message
     * @param ui
     * @param tasks
     * @return system message
     */
    public String showDeadlineMessage(Ui ui, TaskList tasks) {
        return ui.showTaskAdded(deadlineTaskObject, tasks.getSize());
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
        formatDeadline(by);
        addDeadlineTask(tasks);
        super.saveTasks(tasks, storage);
        return showDeadlineMessage(ui, tasks);
    }
}
