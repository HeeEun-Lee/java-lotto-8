package lotto.domain;

import lotto.exception.ErrorMessage;

public class WinningLotto {
    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(Lotto winningLotto, int bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank match(Lotto lotto) {
        long matchCount = lotto.getNumbers().stream()
                .filter(winningLotto.getNumbers()::contains)
                .count();

        boolean bonusMatched = lotto.getNumbers().contains(bonusNumber);
        return Rank.valueOf((int) matchCount, bonusMatched);
    }


    private void validate(Lotto lotto, int bonusNumber) {
        if (lotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_BONUS_NUMBER.getMessage());
        }
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        }
    }
}
