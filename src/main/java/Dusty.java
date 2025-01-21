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
        Scanner scan = new Scanner(System.in); // create scanner object
        System.out.println("Hello! I'm Dusty\nHow can I help you?\n_______________\n");
        while (true) {
            String input = scan.nextLine(); // read user input
            if (input.equals("bye")) {
                System.out.println("    " + "Bye! See you next time!\n");
                break;
            } else {
                store.add(input);
                System.out.println("    added: "+ input);
            }
        }
    }
}
