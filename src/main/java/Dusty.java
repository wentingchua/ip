import java.util.ArrayList;
import java.util.Scanner;

public class Dusty {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        ArrayList<String> store = new ArrayList<>(); // Store tasks
        ArrayList<String> check = new ArrayList<>(); // Store checkboxes
        ArrayList<String> type = new ArrayList<>();  // Store task types
        Scanner scan = new Scanner(System.in);       // Create scanner object

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
                    System.out.println(i + "." + type.get(i - 1) + check.get(i - 1) + store.get(i - 1));
                }
            } else if (parts[0].equals("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                int index = Integer.parseInt(parts[1]) - 1;
                check.set(index, "[X]");
                System.out.println("  " + type.get(index) + check.get(index) + store.get(index));
            } else if (parts[0].equals("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int index = Integer.parseInt(parts[1]) - 1;
                check.set(index, "[ ]");
                System.out.println("  " + type.get(index) + check.get(index) + store.get(index));
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(parts[1]) - 1;
                System.out.println("Okay. I've removed this task:");
                System.out.println("  " + type.get(index) + check.get(index) + store.get(index));
                store.remove(index);
                check.remove(index);
                type.remove(index);
                System.out.println("Now you have " + store.size() + " tasks in the list.");
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
                    } else if (parts[0].equals("deadline")) {
                        type.add("[D]");
                        int slashIndex = parts[1].indexOf("/by");
                        String task = parts[1].substring(0, slashIndex - 1);
                        String by = parts[1].substring(slashIndex + 4);
                        store.add(task + " (by " + by + ")");
                    } else {
                        type.add("[E]");
                        int slashFrom = parts[1].indexOf("/from");
                        int slashTo = parts[1].indexOf("/to");
                        String task = parts[1].substring(0, slashFrom - 1);
                        String from = parts[1].substring(slashFrom + 6, slashTo - 1);
                        String to = parts[1].substring(slashTo + 4);
                        store.add(task + " (from: " + from + " to: " + to + ")");
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
