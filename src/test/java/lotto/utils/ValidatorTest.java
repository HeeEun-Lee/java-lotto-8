package lotto.utils;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ValidatorTest {

    @Test
    void 정상_입력은_정수로_변환된다() {
        assertThat(Validator.validateIntegerInput("8000")).isEqualTo(8000);
    }

    @Test
    void 숫자가_아닌_입력은_예외발생() {
        assertThatThrownBy(() -> Validator.validateIntegerInput("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_INPUT_TYPE.getMessage());
    }
}
