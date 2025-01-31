package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DustyTest {

    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    private void runDusty(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Dusty d = new Dusty("data/tasks.txt"); // Ensure data/tasks.txt exists in test resources
        d.run();
    }

    @Test
    public void todoTestEmpty() {
        String input = "todo \nbye\n";
        String expectedOutput = """
            Hello! I'm Dusty
            How can I help you?
            _______________
            OOPS!!! The description of a todo cannot be empty.
            Bye! See you next time!""";

        runDusty(input);
        String actualOutput = outputStream.toString().trim();

        // 1. Print Character Codes (CRUCIAL)
        System.out.println("Expected (Character Codes):");
        for (int i = 0; i < expectedOutput.length(); i++) {
            System.out.print((int) expectedOutput.charAt(i) + " ");
        }
        System.out.println();

        System.out.println("Actual (Character Codes):");
        for (int i = 0; i < actualOutput.length(); i++) {
            System.out.print((int) actualOutput.charAt(i) + " ");
        }
        System.out.println();

        // 2. Normalize Spacing and Line Endings
        String normalizedExpected = expectedOutput.replace("\r\n", "\n").replace("\u00A0", " ").trim();
        String normalizedActual = actualOutput.replace("\r\n", "\n").replace("\u00A0", " ").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }

    @Test
    public void deadlineTestWrong() {
        String input = "deadline essay /by 2025/01/02 2359\nbye\n";
        String expectedOutput = """
                Hello! I'm Dusty
                How can I help you?
                _______________
                Invalid input or error processing command. Note that date and time should be in dd/mm/yyyy ttmm format
                Bye! See you next time!""";

        runDusty(input);
        String actualOutput = outputStream.toString().trim();

        String normalizedExpected = expectedOutput.replace("\r\n", "\n").replace("\u00A0", " ").trim();
        String normalizedActual = actualOutput.replace("\r\n", "\n").replace("\u00A0", " ").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }

    @Test
    public void eventTestWrong() {
        String input = "event meeting /from 01/01/2025 1000\nbye\n";
        String expectedOutput = """
                Hello! I'm Dusty
                How can I help you?
                _______________
                OOPS!!! Please follow this format: event task /from time /to time
                Bye! See you next time!""";

        runDusty(input);
        String actualOutput = outputStream.toString().trim();

        String normalizedExpected = expectedOutput.replace("\r\n", "\n").replace("\u00A0", " ").trim();
        String normalizedActual = actualOutput.replace("\r\n", "\n").replace("\u00A0", " ").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }

}