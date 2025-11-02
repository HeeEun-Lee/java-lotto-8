package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoResultCalculator {
    public Result calculate(List<Lotto> purchasedLottos, Lotto winningLotto, int bonusNumber, int purchaseAmount) {
        List<Rank> ranks = purchasedLottos.stream()
                .map(lotto -> {
                    long matchCount = lotto.getNumbers().stream()
                            .filter(winningLotto.getNumbers()::contains)
                            .count();
                    boolean bonusMatched = lotto.getNumbers().contains(bonusNumber);
                    return Rank.valueOf((int) matchCount, bonusMatched);
                })
                .collect(Collectors.toList());

        return new Result(ranks, purchaseAmount);
    }
}
