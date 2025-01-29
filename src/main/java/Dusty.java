import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Dusty {
    public static void main(String[] args) {

        ArrayList<String> store = new ArrayList<>(); // Store tasks
        ArrayList<String> check = new ArrayList<>(); // Store checkboxes
        ArrayList<String> type = new ArrayList<>();  // Store task types
        ArrayList<LocalDateTime> deadlines = new ArrayList<>();
        Scanner scan = new Scanner(System.in);       // Create scanner object

        TaskStorage.loadTasks(type, check, store);
        System.out.println("Hello! I'm Dusty\nHow can I help you?\n_______________");

        while (true) {
            String input = scan.nextLine();          // Read user input
            String[] parts = input.split(" ", 2);    // Split based on spaces
            if (input.equals("bye")) {
                System.out.println("Bye! See you next time!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= store.size(); i++) {
                    String formattedDate = (type.get(i).equals("[D]") && i < deadlines.size())
                            ? " (by " + DateTimeParser.formatDateTime(deadlines.get(i)) + ")"
                            : "";
                    System.out.println((i + 1) + "." + type.get(i) + check.get(i) + store.get(i) + formattedDate);
                }
            } else if (parts[0].equals("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                int index = Integer.parseInt(parts[1]) - 1;
                check.set(index, "[X]");
                System.out.println("  " + type.get(index) + check.get(index) + store.get(index));
                TaskStorage.saveTasks(type, check, store);
            } else if (parts[0].equals("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int index = Integer.parseInt(parts[1]) - 1;
                check.set(index, "[ ]");
                System.out.println("  " + type.get(index) + check.get(index) + store.get(index));
                TaskStorage.saveTasks(type, check, store);
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(parts[1]) - 1;
                System.out.println("Okay. I've removed this task:");
                System.out.println("  " + type.get(index) + check.get(index) + store.get(index));
                store.remove(index);
                check.remove(index);
                type.remove(index);
                if (index < deadlines.size()) {
                    deadlines.remove(index);
                }
                System.out.println("Now you have " + store.size() + " tasks in the list.");
                TaskStorage.saveTasks(type, check, store);
            } else if (parts[0].equals("todo") || parts[0].equals("deadline") || parts[0].equals("event")) {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    System.out.println("OOPS!!! The description of a " + parts[0] + " cannot be empty.");
                } else if (parts[0].equals("deadline") && !parts[1].contains("/by")) {
                    System.out.println("OOPS!!! Please follow this format: deadline task /by date");
                } else if (parts[0].equals("event") && (!parts[1].contains("/from") || !parts[1].contains("/to"))) {
                    System.out.println("OOPS!!! Please follow this format: event task /from time /to time");
                } else {
                    check.add("[ ]");
                    if (parts[0].equals("todo")) {
                        type.add("[T]");
                        store.add(parts[1]);
                        TaskStorage.saveTasks(type, check, store);
                    } else if (parts[0].equals("deadline")) {
                        type.add("[D]");
                        int slashIndex = parts[1].indexOf("/by");
                        String task = parts[1].substring(0, slashIndex - 1);
                        String by = parts[1].substring(slashIndex + 4).trim();
                        try {
                            LocalDateTime deadline = DateTimeParser.parseDateTime(by);
                            deadlines.add(deadline);
                            store.add(task);
                            System.out.println("Got it. I've added this deadline:");
                            System.out.println("  " + "[D]" + "[ ]" + task + " (by " + DateTimeParser.formatDateTime(deadline) + ")");
                        } catch (Exception e) {
                            System.out.println("Invalid date format! Please use dd/mm/yyyy HHmm (24 hr).");
                            continue;
                        }
                        TaskStorage.saveTasks(type, check, store);
                    } else {
                        type.add("[E]");
                        int slashFrom = parts[1].indexOf("/from");
                        int slashTo = parts[1].indexOf("/to");
                        String task = parts[1].substring(0, slashFrom - 1);
                        String from = parts[1].substring(slashFrom + 6, slashTo - 1);
                        String to = parts[1].substring(slashTo + 4);
                        store.add(task + " (from: " + from + " to: " + to + ")");
                        TaskStorage.saveTasks(type, check, store);
                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + type.get(type.size() - 1) + check.get(check.size() - 1) + store.get(store.size() - 1));
                    System.out.println("Now you have " + store.size() + " tasks in the list.");
                }
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :(");
            }
        }
    }
}
