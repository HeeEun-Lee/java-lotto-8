package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultCalculatorTest {

    private final LottoResultCalculator calculator = new LottoResultCalculator();

    @DisplayName("6개 번호가 모두 일치하면 1등이 반환된다.")
    @Test
    void 일치_6개면_1등() {
        Lotto purchased = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        Result result = calculator.calculate(List.of(purchased), winningLotto, 1000);

        Rank rank = result.getRankCount().keySet().stream()
                .filter(r -> result.getRankCount().get(r) > 0)
                .findFirst()
                .orElse(Rank.MISS);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("5개 번호와 보너스 번호가 일치하면 2등이 반환된다.")
    @Test
    void 일치_5개_보너스_일치면_2등() {
        Lotto purchased = new Lotto(List.of(1, 2, 3, 4, 5, 7)); // 5개 + 보너스(7)
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        Result result = calculator.calculate(List.of(purchased), winningLotto, 1000);

        Rank rank = result.getRankCount().keySet().stream()
                .filter(r -> result.getRankCount().get(r) > 0)
                .findFirst()
                .orElse(Rank.MISS);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("5개 번호만 일치하면 3등이 반환된다.")
    @Test
    void 일치_5개면_3등() {
        Lotto purchased = new Lotto(List.of(1, 2, 3, 4, 5, 10)); // 5개만 일치
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        Result result = calculator.calculate(List.of(purchased), winningLotto, 1000);

        Rank rank = result.getRankCount().keySet().stream()
                .filter(r -> result.getRankCount().get(r) > 0)
                .findFirst()
                .orElse(Rank.MISS);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("3개 번호만 일치하면 5등이 반환된다.")
    @Test
    void 일치_3개면_5등() {
        Lotto purchased = new Lotto(List.of(1, 2, 3, 7, 8, 9));
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        Result result = calculator.calculate(List.of(purchased), winningLotto, 1000);

        Rank rank = result.getRankCount().keySet().stream()
                .filter(r -> result.getRankCount().get(r) > 0)
                .findFirst()
                .orElse(Rank.MISS);

        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("일치하는 번호가 3개 미만이면 MISS가 반환된다.")
    @Test
    void 일치_3개_미만이면_낙첨() {
        Lotto purchased = new Lotto(List.of(10, 20, 30, 40, 41, 42));
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        Result result = calculator.calculate(List.of(purchased), winningLotto, 1000);

        Rank rank = result.getRankCount().keySet().stream()
                .filter(r -> result.getRankCount().get(r) > 0)
                .findFirst()
                .orElse(Rank.MISS);

        assertThat(rank).isEqualTo(Rank.MISS);
    }
}
