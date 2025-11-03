package lotto.controller;

import lotto.domain.*;
import lotto.dto.*;
import lotto.service.LottoService;
import lotto.utils.InputRetryHandler;
import lotto.utils.Parser;
import lotto.utils.Validator;
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
        List<LottoResponseDto> purchasedLotto = purchaseLotto();
        Lotto winningNumbers = inputWinningNumbers();
        WinningLotto winningLotto = inputBonusNumber(winningNumbers);

        ResultResponseDto result = calculateResult(purchasedLotto, winningLotto);
        outputView.printResult(result);

    }

    private List<LottoResponseDto> purchaseLotto() {
        List<LottoResponseDto> purchasedLotto = InputRetryHandler.get(() -> {
            int money = Validator.validateIntegerInput(inputView.readPurchaseAmount());
            return lottoService.buyLottos(money);
        });
        outputView.printPurchasedLottos(purchasedLotto);
        return purchasedLotto;
    }

    private Lotto inputWinningNumbers() {
        return InputRetryHandler.get(() -> {
            String input = inputView.readWinningNumbers();
            return new Lotto(Parser.parse(input));
        });
    }

    private WinningLotto inputBonusNumber(Lotto winningNumbers) {
        return InputRetryHandler.get(() -> {
            int bonus = Validator.validateIntegerInput(inputView.readBonusNumber());
            return new WinningLotto(winningNumbers, bonus);
        });
    }

    private ResultResponseDto calculateResult(List<LottoResponseDto> purchasedLottos, WinningLotto winningLotto) {
        int money = lottoService.getTotalMoneySpent(purchasedLottos);
        return lottoService.calculateResult(
                purchasedLottos.stream().map(dto -> new Lotto(dto.getNumbers())).toList(),
                winningLotto,
                money
        );
    }
}
