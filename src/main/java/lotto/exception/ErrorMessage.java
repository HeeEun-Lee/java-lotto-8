package lotto.exception;

public enum ErrorMessage {
    INVALID_MONEY_UNIT("[ERROR] 구입 금액은 1000원 단위여야 합니다."),
    INVALID_NUMBER_RANGE("[ERROR] 로또 번호는 1부터 45 사이여야 합니다."),
    DUPLICATED_NUMBER("[ERROR] 로또 번호에 중복된 숫자가 있습니다."),
    INVALID_NUMBER_COUNT("[ERROR] 로또 번호는 6개여야 합니다."),
    INVALID_INPUT_TYPE("[ERROR] 숫자가 아닌 입력입니다."),
    INVALID_INPUT_FORMAT("[ERROR] 쉼표 기준으로 구분된 숫자 6자리를 입력해야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
