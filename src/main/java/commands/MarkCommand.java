package commands;

import store.Storage;
import store.Task;
import store.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class MarkCommand extends Command {

    private int markIndex;

    public MarkCommand(String details) {
        super(details);
        this.markIndex = Integer.parseInt(details) - 1;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        tasks.markTaskAsDone(markIndex);
        super.saveTasks(tasks, storage);
        return ui.showTaskMarked(tasks.getTask(markIndex));
    }
}
