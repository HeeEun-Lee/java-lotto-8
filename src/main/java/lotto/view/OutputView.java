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
        System.out.println(MessageConstant.RESULT_TITLE);
        System.out.println(MessageConstant.RESULT_SEPARATOR);

        result.getResultSummary()
                .forEach((key, value) -> System.out.println(key + " - " + value + "개"));


        printProfitRate(result.getProfitRate());
    }

    public void printProfitRate(double profitRate) {
        System.out.printf(MessageConstant.RESULT_PROFIT + "%n", profitRate);
    }

    public void printLine() {
        System.out.println();
    }

}
