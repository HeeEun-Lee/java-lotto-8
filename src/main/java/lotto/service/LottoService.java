package lotto.service;

import lotto.domain.LottoFactory;
import lotto.dto.LottoResponseDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.exception.ErrorMessage;

public class LottoService {

    private static final int PRICE_PER_TICKET = 1000;

    public List<LottoResponseDto> buyLottos(int money) {
        validateMoney(money);
        int count = money / PRICE_PER_TICKET;
        return IntStream.range(0, count)
                .mapToObj(i -> new LottoResponseDto(LottoFactory.createRandomLotto().getNumbers()))
                .collect(Collectors.toList());
    }

    private void validateMoney(int money) {
        if (money < PRICE_PER_TICKET || money % PRICE_PER_TICKET != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_UNIT.getMessage());
        }
    }
}