package lotto.dto;

import java.util.List;

public class LottoResponseDto {

    private final List<Integer> numbers;

    public LottoResponseDto(List<Integer> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
