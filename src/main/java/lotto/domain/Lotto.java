package lotto.domain;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream().sorted().toList();

    }

    private void validate(List<Integer> numbers) {
        LottoValidator.validate(numbers);
    }

    // TODO: 추가 기능 구현
    public List<Integer> getNumbers() {
        return numbers;
    }
}
