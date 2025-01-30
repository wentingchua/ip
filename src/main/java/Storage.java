import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath; // Removed static

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        System.out.println("Checking file existence: " + file.exists());
        System.out.println("File path: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("File does not exist. Creating it...");
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                boolean dirsCreated = file.getParentFile().mkdirs();
                System.out.println("Created directories: " + dirsCreated);
            }

            boolean fileCreated = file.createNewFile();
            System.out.println("File created: " + fileCreated);
        }

        // Try reading the file
        try (Scanner scanner = new Scanner(file)) {
            System.out.println("Starting to read file...");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Read line: " + line);

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
            System.out.println("Finished reading file.");
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return tasks;
    }


    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + "\n");  // Use a consistent save format
            }
        }
    }

}
