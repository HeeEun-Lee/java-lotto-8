package lotto.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import lotto.exception.ErrorMessage;

public class Parser {
    private static final Pattern VALID_PATTERN =
            Pattern.compile("^\\s*\\d{1,2}(\\s*,\\s*\\d{1,2}){5}\\s*$");

    public static List<Integer> parse(String input) {
        if (!VALID_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

}
