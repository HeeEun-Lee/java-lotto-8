package lotto.exception;

public enum ErrorMessage {
    INVALID_MONEY_UNIT("구입 금액은 1000원 단위여야 합니다."),
    INVALID_NUMBER_RANGE("로또 번호는 1부터 45 사이여야 합니다."),
    DUPLICATED_NUMBER("로또 번호에 중복된 숫자가 있습니다."),
    DUPLICATED_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복될 수 없습니다"),
    INVALID_NUMBER_COUNT("로또 번호는 6개여야 합니다."),
    INVALID_INPUT_TYPE("숫자가 아닌 입력입니다."),
    INVALID_INPUT_FORMAT("쉼표 기준으로 구분된 숫자 6자리를 입력해야 합니다.");

    private final String message;

    // 에러 메시지 공통 접두사
    public static final String ERROR_PREFIX = "[ERROR] ";
    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
