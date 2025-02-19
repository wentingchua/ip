package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DustyTest {

    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    private void runDusty(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        // Directly instantiate Dusty, avoiding JavaFX dependencies
        Dusty dusty = new Dusty("data/tasks.txt");
        dusty.run();
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
            Invalid input or error processing command.
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
            OOPS!!! Please follow this format: event task /from dd/mm/yyyy ttmm /to dd/mm/yyyy ttmm
            Bye! See you next time!""";

        runDusty(input);
        String actualOutput = outputStream.toString().trim();

        String normalizedExpected = expectedOutput.replace("\r\n", "\n").replace("\u00A0", " ").trim();
        String normalizedActual = actualOutput.replace("\r\n", "\n").replace("\u00A0", " ").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }
}
