package lotto.controller;

import lotto.domain.*;
import lotto.dto.*;
import lotto.service.LottoService;
import lotto.utils.InputRetryHandler;
import lotto.utils.Parser;
import lotto.view.*;

import java.util.List;

public class LottoController {
    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(LottoService lottoService, InputView inputView, OutputView outputView) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<LottoResponseDto> purchasedLottos = InputRetryHandler.get(() -> {
            int money = inputView.readPurchaseAmount();
            return lottoService.buyLottos(money);
        });
        outputView.printPurchasedLottos(purchasedLottos);

        Lotto winningNumbers = InputRetryHandler.get(() -> {
            String input = inputView.readWinningNumbers();
            return new Lotto(Parser.parse(input));
        });

        WinningLotto winningLotto = InputRetryHandler.get(() -> {
            int bonus = inputView.readBonusNumber();
            return new WinningLotto(winningNumbers, bonus);
        });

        int money = lottoService.getTotalMoneySpent(purchasedLottos);
        ResultResponseDto result = lottoService.calculateResult(
                purchasedLottos.stream().map(dto -> new Lotto(dto.getNumbers())).toList(),
                winningLotto,
                money
        );

        outputView.printResult(result);
    }
}
