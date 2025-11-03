package lotto.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InputRetryHandlerTest {

    @Test
    @DisplayName("정상적인 입력 시 한 번만 실행된다")
    void 정상입력_한번만실행() {
        final int[] count = {0};
        int result = InputRetryHandler.get(() -> {
            count[0]++;
            return 42;
        });

        assertThat(result).isEqualTo(42);
        assertThat(count[0]).isEqualTo(1);
    }

    @Test
    @DisplayName("예외 발생 후 재시도 시 정상 입력을 반환한다")
    void 예외발생후_재시도() {
        final int[] attempts = {0};

        int result = InputRetryHandler.get(() -> {
            if (attempts[0]++ == 0) {
                throw new IllegalArgumentException("첫 시도 실패");
            }
            return 99;
        });

        assertThat(result).isEqualTo(99);
        assertThat(attempts[0]).isEqualTo(2);
    }

    @Test
    @DisplayName("연속된 예외 발생 후 정상 입력 시도")
    void 연속예외후_정상입력() {
        final int[] tries = {0};

        int result = InputRetryHandler.get(() -> {
            if (tries[0]++ < 2) {
                throw new IllegalArgumentException("두 번 실패");
            }
            return 777;
        });

        assertThat(result).isEqualTo(777);
        assertThat(tries[0]).isEqualTo(3);
    }
}
