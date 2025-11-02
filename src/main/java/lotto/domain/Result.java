package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public double calculateProfitRate() {
        long totalPrize = rankCount.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();

        // BigDecimal을 사용한 정밀 계산
        BigDecimal total = BigDecimal.valueOf(totalPrize);
        BigDecimal purchase = BigDecimal.valueOf(purchaseAmount);

        // (총 상금 / 구입 금액) * 100, 소수점 둘째 자리 반올림
        BigDecimal rate = total
                .divide(purchase, 10, RoundingMode.HALF_UP)  // 소수점 10자리까지 정확히 계산
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);          // 소수점 둘째 자리 반올림

        return rate.doubleValue();
    }

    public ResultResponseDto toDto() {
        return new ResultResponseDto(rankCount, calculateProfitRate());
    }
}
