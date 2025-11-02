package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoResultCalculator;
import lotto.domain.Result;
import lotto.dto.LottoResponseDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.dto.ResultResponseDto;
import lotto.exception.ErrorMessage;

public class LottoService {

    private static final int PRICE_PER_TICKET = 1000;
    private final LottoResultCalculator calculator = new LottoResultCalculator();

    public List<LottoResponseDto> buyLottos(int money) {
        validateMoney(money);
        int count = money / PRICE_PER_TICKET;
        return IntStream.range(0, count)
                .mapToObj(i -> new LottoResponseDto(LottoFactory.createRandomLotto().getNumbers()))
                .toList();
    }

    private void validateMoney(int money) {
        if (money < PRICE_PER_TICKET || money % PRICE_PER_TICKET != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_UNIT.getMessage());
        }
    }

    public ResultResponseDto calculateResult(List<Lotto> purchasedLottos, Lotto winningLotto, int bonus, int money) {
        return calculator.calculate(purchasedLottos, winningLotto, bonus, money).toDto();
    }
}