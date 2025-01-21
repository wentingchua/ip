import java.util.ArrayList;
import java.util.Scanner;
public class Dusty {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        ArrayList<String> store = new ArrayList<String>(); //store tasks
        ArrayList<String> check = new ArrayList<String>(); //store check boxes
        ArrayList<String> type = new ArrayList<String>(); //store types of tasks
        Scanner scan = new Scanner(System.in); // create scanner object
        System.out.println("Hello! I'm Dusty\nHow can I help you?\n_______________\n");
        while (true) {
            String input = scan.nextLine(); // read user input
            String[] parts = input.split(" ", 2); // split based on spaces
            if (input.equals("bye")) {
                System.out.println("    " + "Bye! See you next time!\n");
                break;
            } else if (input.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 1; i < store.size() + 1; i++) {
                    System.out.println("    " + i + "." + type.get(i - 1) + check.get(i - 1) + " " + store.get(i - 1));
                }
            } else if (parts[0].equals("mark")) {
                System.out.println("    Nice! I've marked this task as done:");
                int index = Integer.parseInt(parts[1]) - 1;
                String done = "[X]";
                check.set(index, done);
                System.out.println("      " + check.get(index) + " " + store.get(index));
            } else if (parts[0].equals("unmark")) {
                System.out.println("    OK, I've marked this task as not done yet:");
                int index = Integer.parseInt(parts[1]) - 1;
                String undone = "[ ]";
                check.set(index, undone);
                System.out.println("      " + check.get(index) + " " + store.get(index));
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(parts[1]) - 1;
                System.out.println("    Okay. I've removed this task: \n" + "      " + type.get(index) + check.get(index) + " " + store.get(index) + "\n Now you have " + store.size() + " tasks in the list.");
                store.remove(index);
                check.remove(index);
                type.remove(index);
            } else if (parts[0].equals("todo") || parts[0].equals("deadline") || parts[0].equals("event")) {
                String checkbox = "[ ] ";
                check.add(checkbox);
                if (input.equals("todo")) {
                    System.out.println("OOPS!!! The description of " + input + " cannot be empty.");
                } else if (input.startsWith("deadline") && !input.contains("/")) {
                    System.out.println("OOPS!!! Please follow this format: deadline task /due date");
                } else if (input.startsWith("event") && (!input.contains("/from") || !input.contains("/to"))) {
                    System.out.println("OOPS!!! Please follow this format: event task /from date /to date");
                } else {
                    if (parts[0].equals("todo")) {
                        type.add("[T]");
                        store.add(parts[1]);
                    } else if (parts[0].equals("deadline")) {
                        type.add("[D]");
                        int firstSlash = parts[1].indexOf("/");
                        String task = parts[1].substring(0, firstSlash - 1);
                        String ddl = parts[1].substring(firstSlash + 1);
                        store.add(task + " (" + ddl + ")");
                    } else {
                        type.add("[E]");
                        int slashFrom = parts[1].indexOf("/from");
                        int slashTo = parts[1].indexOf("/to");
                        String task = parts[1].substring(0, slashFrom - 1);
                        String from = parts[1].substring(slashFrom + 6, slashTo - 1);
                        String to = parts[1].substring(slashTo + 4);
                        store.add(task + " (from: " + from + " to: " + to + ")");
                    }
                    System.out.println("    Got it. I've added this task:\n" + "      "
                            + type.get(type.size() - 1) + "[ ] " + store.get(store.size() - 1) + "\nNow you have " + store.size() + " tasks in the list.");
                }
            }
            else{
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :( \n");
            }
        }
    }
}
