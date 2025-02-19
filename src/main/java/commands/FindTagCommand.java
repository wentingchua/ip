package commands;

import java.util.List;
import java.util.stream.Collectors;

import store.Storage;
import store.Task;
import store.TaskList;
import tag.Tag;
import ui.Ui;

/**
 * Class representing Find tag command
 */
public class FindTagCommand extends Command {
    private final Tag tag;

    /**
     * Constructor for find tag command
     * @param tag
     */
    public FindTagCommand(String tag) {
        super(tag);
        this.tag = new Tag(tag);
    }

    /**
     * Methodd to execute command
     * @param tasks The TaskList to work with.
     * @param storage The Storage to save or load tasks
     * @param ui The messages the user will see
     * @return String of system message
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        List<Task> matchingTasks = tasks.getTasks().stream()
                .filter(task -> task.hasTag(tag.getName()))
                .toList();

        if (matchingTasks.isEmpty()) {
            return ui.showNoMatchTags(tag.toString());
        }

        return ui.showMessage("Tasks with " + tag + ":\n"
                + matchingTasks.stream().map(Task::toString).collect(Collectors.joining("\n")));
    }

}
