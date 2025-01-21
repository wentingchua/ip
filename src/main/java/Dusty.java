import java.sql.Array;
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
                    System.out.println("    " + i + "." + check.get(i - 1) + " " + store.get(i - 1));
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
            } else {
                store.add(input);
                String checkbox = "[ ] ";
                check.add(checkbox);
                System.out.println("    added: "+ input);
            }
        }
    }
}
