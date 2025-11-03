package lotto.utils;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ParserTest {

    @Test
    @DisplayName("올바른 형식의 입력 문자열을 정수 리스트로 변환한다")
    void parse_정상입력() {
        List<Integer> result = Parser.parse("1, 2, 3, 4, 5, 6");

        assertThat(result)
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("공백이 포함된 입력도 정상적으로 처리된다")
    void parse_공백포함_정상입력() {
        List<Integer> result = Parser.parse("  7 , 8 , 9 , 10 , 11 , 12  ");

        assertThat(result)
                .containsExactly(7, 8, 9, 10, 11, 12);
    }

    @Test
    @DisplayName("쉼표 개수가 5개가 아니거나 숫자 6개가 아니면 예외 발생")
    void parse_숫자개수_부족또는초과_예외() {
        assertThatThrownBy(() -> Parser.parse("1, 2, 3, 4, 5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());

        assertThatThrownBy(() -> Parser.parse("1, 2, 3, 4, 5, 6, 7"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
    }

    @Test
    @DisplayName("숫자가 아닌 입력이 포함되면 예외 발생")
    void parse_문자입력_예외() {
        assertThatThrownBy(() -> Parser.parse("1, 2, a, 4, 5, 6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
    }

    @Test
    @DisplayName("빈 문자열 입력 시 예외 발생")
    void parse_빈문자열_예외() {
        assertThatThrownBy(() -> Parser.parse(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
    }

    @Test
    @DisplayName("공백만 있는 문자열 입력 시 예외 발생")
    void parse_공백문자열_예외() {
        assertThatThrownBy(() -> Parser.parse("     "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
    }

    @Test
    @DisplayName("쉼표가 없는 잘못된 입력 시 예외 발생")
    void parse_쉼표없는입력_예외() {
        assertThatThrownBy(() -> Parser.parse("123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
    }
}
