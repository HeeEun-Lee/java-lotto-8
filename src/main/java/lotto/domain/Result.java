package lotto.domain;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.dto.ResultResponseDto;

public class Result {
    private final Map<Rank, Integer> rankCount = new EnumMap<>(Rank.class);
    private final int purchaseAmount;

    public Result(List<Rank> ranks, int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
        for (Rank rank : Rank.values()) rankCount.put(rank, 0);
        for (Rank rank : ranks) rankCount.put(rank, rankCount.get(rank) + 1);
    }

    public Map<Rank, Integer> getRankCount() {
        return Map.copyOf(rankCount); // 불변 Map으로 반환 → 외부 수정 불가
    }

    public double calculateProfitRate() {
        if (purchaseAmount == 0) return 0.0;

        long totalPrize = rankCount.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();

        double rate = ((double) totalPrize / purchaseAmount) * 100;

        // 소수점 둘째 자리까지 반올림
        return Math.round(rate * 100) / 100.0;
    }

    public ResultResponseDto toDto() {
        return new ResultResponseDto(rankCount, calculateProfitRate());
    }
}
