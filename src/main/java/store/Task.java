package store;

import java.util.List;
import java.util.stream.Collectors;

import tag.Tag;

/**
 * Represents a Task
 * @author Wen Ting
 * @version 1.0
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected List<Tag> tags;

    /**
     * Constructor for Task
     * @param description of task
     */
    public Task(String description, List<Tag> tags) {
        this.description = description;
        this.isDone = false;
        this.tags = tags;
    }


    /**
     * Returns String status icon that corresponds to status of task.
     * Can be printed as output.
     * @return The String that represents status of task
     */

    public String getDescription() {
        return this.description;
    }


    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Sets boolean isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Sets boolean isDone to false
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description + formatTags();
    }

    private String formatTags() {
        if (tags.isEmpty()) {
            return "";
        }
        return " | " + tags.stream().map(Tag::getName).collect(Collectors.joining(","));
    }

    /**
     * method to add tags related to task
     * @param tagString of tags
     */
    public void addTags(String tagString) {
        if (tagString.isEmpty()) {
            return;
        }
        String[] tagArray = tagString.split(",");
        for (String tagName : tagArray) {
            tags.add(new Tag(tagName));
        }
    }

    public List<Tag> getTags() {
        return tags;
    }

    public boolean hasTag(String tagName) {
        return tags.contains(new Tag(tagName));
    }

}
