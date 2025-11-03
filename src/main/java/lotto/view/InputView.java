package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.constant.MessageConstant;

public class InputView {
    public int readPurchaseAmount() {
        System.out.println(MessageConstant.INPUT_PURCHASE_AMOUNT);
        return Integer.parseInt(Console.readLine());
    }

    public String readWinningNumbers() {
        System.out.println(MessageConstant.INPUT_WINNING_NUMBERS);
        return Console.readLine();
    }

    public int readBonusNumber() {
        System.out.println();
        System.out.println(MessageConstant.INPUT_BONUS_NUMBER);
        return Integer.parseInt(Console.readLine());
    }
}
