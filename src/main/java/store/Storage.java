package store;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import backend.Parser;

/**
 * The Storage class handles loading and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath the path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return an ArrayList of tasks loaded from the file.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Check if file exists, if not create it
        if (!file.exists()) {
            System.out.println("File does not exist. Creating it...");
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                boolean isDirectoryCreated = file.getParentFile().mkdirs();
                System.out.println("Created directories: " + isDirectoryCreated);
            }

            boolean fileCreated = file.createNewFile();
            System.out.println("File created: " + fileCreated);
        }

        // Try reading the file
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    Task task = Parser.parseTaskFromFile(line);
                    if (task != null) {
                        tasks.add(task);
                    } else {
                        System.out.println("Parser returned null for line: " + line);
                    }
                } catch (Exception e) {
                    System.out.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return tasks;
    }

    /**
     * Saves the specified tasks to the file specified by the file path.
     *
     * @param tasks an ArrayList of tasks to save.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        // Ensure the directory exists
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // Write tasks to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + "\n"); // Use a consistent save format
            }
        }
    }
}
