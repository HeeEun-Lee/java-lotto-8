package lotto.view;

import java.util.List;
import lotto.constant.MessageConstant;
import lotto.dto.LottoResponseDto;
import lotto.dto.ResultResponseDto;

public class OutputView {

    public void printPurchasedLottos(List<LottoResponseDto> lottos) {
        printLine();
        System.out.println(lottos.size() + MessageConstant.PURCHASE_COUNT_SUFFIX);
        lottos.forEach(this::printLottoNumbers);
        printLine();
    }

    private void printLottoNumbers(LottoResponseDto lotto) {
        System.out.println(lotto.getNumbers());
    }

    public void printResult(ResultResponseDto result) {
        printLine();
        System.out.println(MessageConstant.RESULT_TITLE);
        System.out.println(MessageConstant.RESULT_SEPARATOR);

        result.getResultSummary()
                .forEach((key, value) -> System.out.println(key + " - " + value + "개"));


        printProfitRate(result.getFormattedProfitRate());
    }

    public void printProfitRate(String profitRate) {
        System.out.println(MessageConstant.RESULT_PROFIT_PREFIX + profitRate + MessageConstant.RESULT_PROFIT_SUFFIX);
    }

    public void printLine() {
        System.out.println();
    }

}
