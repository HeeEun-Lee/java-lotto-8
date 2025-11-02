package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @DisplayName("각 Rank별 당첨 개수가 올바르게 집계된다.")
    @Test
    void Rank별_당첨개수_집계_테스트() {
        List<Rank> ranks = List.of(
                Rank.FIRST, Rank.FIRST,
                Rank.SECOND, Rank.FIFTH, Rank.FIFTH, Rank.MISS
        );
        int purchaseAmount = 6000;

        Result result = new Result(ranks, purchaseAmount);

        assertThat(result.getRankCount().get(Rank.FIRST)).isEqualTo(2);
        assertThat(result.getRankCount().get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.getRankCount().get(Rank.FIFTH)).isEqualTo(2);
        assertThat(result.getRankCount().get(Rank.MISS)).isEqualTo(1);
    }

    @DisplayName("수익률이 올바르게 계산된다.")
    @Test
    void 수익률_계산_테스트() {
        List<Rank> ranks = List.of(Rank.FIRST, Rank.FIFTH, Rank.MISS);
        int purchaseAmount = 3000;

        Result result = new Result(ranks, purchaseAmount);
        double profitRate = result.calculateProfitRate();

        double rawRate = ((2000000000 + 5000.0) / 3000) * 100; // (총 상금 / 구입금액) * 100
        double expectedRate = Math.round(rawRate * 100.0) / 100.0; // 소수점 둘째 자리 반올림

        assertThat(profitRate).isEqualTo(expectedRate);
    }

    @DisplayName("수익률은 소수점 둘째 자리에서 반올림된다.")
    @Test
    void 수익률_소수점_반올림_테스트() {
        List<Rank> ranks = List.of(Rank.FIFTH); // 5000원 상금
        int purchaseAmount = 8000;

        Result result = new Result(ranks, purchaseAmount);
        double profitRate = result.calculateProfitRate();

        // 5000 / 8000 * 100 = 62.5 → 반올림 확인
        assertThat(profitRate).isEqualTo(62.5);
    }
}
