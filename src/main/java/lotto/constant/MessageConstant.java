package lotto.constant;

public final class MessageConstant {

    private MessageConstant() {
    }

    // 입력 메시지
    public static final String INPUT_PURCHASE_AMOUNT = "구입 금액을 입력해 주세요.";
    public static final String INPUT_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    public static final String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    // 출력 메시지
    public static final String PURCHASE_COUNT_SUFFIX = "개를 구매했습니다.";
    public static final String RESULT_TITLE = "당첨 통계";
    public static final String RESULT_SEPARATOR = "---";
    public static final String RESULT_PROFIT = "총 수익률은 %.1f%%입니다.";

    // 에러 메시지 공통 접두사
    public static final String ERROR_PREFIX = "[ERROR]";
}
