package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외 발생")
    @Test
    void 보너스번호_중복_예외() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() -> new WinningLotto(lotto, 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외 발생")
    @Test
    void 보너스번호_범위_예외() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() -> new WinningLotto(lotto, 46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("일반 로또와의 비교 결과가 올바르게 계산된다")
    @Test
    void 로또_비교_테스트() {
        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winning, 7);

        Lotto userLotto1 = new Lotto(List.of(1, 2, 3, 8, 9, 10)); // 3개 일치
        Lotto userLotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7)); // 5개 + 보너스

        Rank rank1 = winningLotto.match(userLotto1);
        Rank rank2 = winningLotto.match(userLotto2);

        assertThat(rank1).isEqualTo(Rank.FIFTH);
        assertThat(rank2).isEqualTo(Rank.SECOND);
    }
}
