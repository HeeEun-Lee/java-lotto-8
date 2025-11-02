package lotto.dto;

import lotto.domain.Rank;
import java.util.Map;

public class ResultResponseDto {

    private final Map<Rank, Integer> rankCount;
    private final double profitRate;

    public ResultResponseDto(Map<Rank, Integer> rankCount, double profitRate) {
        // 외부에서 수정 불가능하게 불변 복사
        this.rankCount = Map.copyOf(rankCount);
        this.profitRate = profitRate;
    }

    public Map<Rank, Integer> getRankCount() {
        return rankCount;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
