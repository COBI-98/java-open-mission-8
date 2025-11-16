package airport.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ConsoleLineParser {

    private static final String DELIMITER = ",";
    private static final String SLASH = "/";

    public static List<String> splitByComma(final String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
    }

    public static List<String> splitBySlash(final String input) {
        return Arrays.stream(input.split(SLASH))
                .map(String::trim)
                .toList();
    }
}
