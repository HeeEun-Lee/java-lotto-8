package lotto.dto;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;
import lotto.domain.Rank;
import java.util.Map;

public class ResultResponseDto {
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("#,###");
    private final Map<Rank, Integer> rankCount;
    private final Map<String, Integer> resultSummary;

    private final double profitRate;

    public ResultResponseDto(Map<Rank, Integer> rankCount, double profitRate) {
        // 외부에서 수정 불가능하게 불변 복사
        this.rankCount = Map.copyOf(rankCount);
        this.resultSummary = buildResultSummary(rankCount);
        this.profitRate = profitRate;
    }

    private static Map<String, Integer> buildResultSummary(Map<Rank, Integer> rankCount) {
        Map<String, Integer> summary = new LinkedHashMap<>();

        List<Rank> orderedRanks = List.of(
                Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST
        );

        orderedRanks.forEach(rank ->
                summary.put(formatRankMessage(rank), rankCount.getOrDefault(rank, 0))
        );


        return summary;
    }

    private static String formatRankMessage(Rank rank) {
        String prize = MONEY_FORMAT.format(rank.getPrize()) + "원";
        if (rank == Rank.SECOND) {
            return "5개 일치, 보너스 볼 일치 (" + prize + ")";
        }
        return rank.getMatchCount() + "개 일치 (" + prize + ")";
    }

    public Map<Rank, Integer> getRankCount() {
        return rankCount;
    }

    public double getProfitRate() {
        return profitRate;
    }
    public Map<String, Integer> getResultSummary() {
        return resultSummary;
    }
}
