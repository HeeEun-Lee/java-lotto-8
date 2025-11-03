package lotto.utils;

import lotto.exception.ErrorMessage;

public class Validator {
    public static int validateIntegerInput(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_TYPE.getMessage());
        }
    }

    private Validator() {}

}
