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
        ArrayList<String> store = new ArrayList<String>();
        ArrayList<String> check = new ArrayList<String>();
        Scanner scan = new Scanner(System.in); // create scanner object
        System.out.println("Hello! I'm Dusty\nHow can I help you?\n_______________\n");
        while (true) {
            String input = scan.nextLine(); // read user input
            if (input.equals("bye")) {
                System.out.println("    " + "Bye! See you next time!\n");
                break;
            } else if (input.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 1; i < store.size() + 1; i++) {
                    System.out.println("    " + i + "." + check.get(i - 1) + store.get(i - 1));
                }
            } else {
                store.add(input);
                String checkbox = "[ ] ";
                check.add(checkbox);
                System.out.println("    added: "+ input);
            }
        }
    }
}
