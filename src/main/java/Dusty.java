import java.util.Scanner;
public class Dusty {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner scan = new Scanner(System.in); // create scanner object
        System.out.println("Hello! I'm Dusty\nHow can I help you?\n_______________\n");
        String input = scan.nextLine(); // read user input
        System.out.println(input);
    }
}
