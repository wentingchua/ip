import java.io.*;
import java.nio.file.*;
import java.util.List;

public class TaskStorage {

    private static final String FILE_PATH = "src/main/data/tasks.txt";

    public static void saveTasks(List<String> type, List<String> check, List<String> store) {
        try {
            Files.createDirectories(Paths.get("src/main/data")); // check if /data exists
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

            for (int i = 0; i < store.size(); i++) {
                writer.write(type.get(i) + " " + check.get(i) + " " + store.get(i));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
