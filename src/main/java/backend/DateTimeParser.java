package backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing and formatting date and time values.
 */
public class DateTimeParser {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Parses a date and time string into a LocalDateTime object.
     * This method attempts to parse the input string in two formats:
     * 1. ISO 8601 format (e.g., "2023-11-23T23:59").
     * 2. Custom format "dd/MM/yyyy HHmm" (e.g., "23/11/2023 2359").
     * It first tries parsing with the ISO 8601 format. If that fails (throws a
     * DateTimeParseException), it attempts parsing with the custom format.
     *
     * @param input The date and time string to parse.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws DateTimeParseException If the input string cannot be parsed in either format.
     */

    public static LocalDateTime parseDateTime(String input) {
        try {
            return LocalDateTime.parse(input); // Handles "2332-11-23T23:59"
        } catch (DateTimeParseException e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(input, formatter);
        }
    }

    /**
     * Formats a LocalDateTime object into a string representation.
     * The date and time are formatted according to the pattern "MMM dd yyyy, h:mma"
     * (e.g., "Nov 23 2023, 11:59PM").
     *
     * @param dateTime The LocalDateTime object to format.
     * @return A string representation of the formatted date and time.
     */

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_FORMAT);
    }
}