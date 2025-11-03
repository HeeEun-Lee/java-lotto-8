package lotto.utils;

import java.util.function.Supplier;

public class InputRetryHandler {

    public static <T> T get(Supplier<T> inputFunction) {
        while (true) {
            try {
                return inputFunction.get();  // 입력 시도
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
