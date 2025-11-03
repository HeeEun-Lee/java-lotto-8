package lotto.domain;

import java.util.List;

public class LottoResultCalculator {
    public Result calculate(List<Lotto> purchasedLottos, WinningLotto winningLotto, int purchaseAmount) {
        List<Rank> ranks = purchasedLottos.stream()
                .map(winningLotto::match)
                .toList();
        return new Result(ranks, purchaseAmount);
    }
}
