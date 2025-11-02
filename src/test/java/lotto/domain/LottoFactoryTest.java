package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFactoryTest {

    @DisplayName("로또는 6개의 숫자를 가져야 한다.")
    @RepeatedTest(5)
    void lottoHasSixNumbers() {
        Lotto lotto = LottoFactory.createRandomLotto();
        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @DisplayName("로또 숫자는 1 이상 45 이하의 범위여야 한다.")
    @RepeatedTest(5)
    void lottoNumbersShouldBeWithinRange() {
        Lotto lotto = LottoFactory.createRandomLotto();
        assertThat(lotto.getNumbers()).allMatch(n -> n >= 1 && n <= 45);
    }

    @DisplayName("로또 숫자는 중복되지 않아야 한다.")
    @RepeatedTest(5)
    void lottoNumbersShouldBeUnique() {
        Lotto lotto = LottoFactory.createRandomLotto();
        List<Integer> numbers = lotto.getNumbers();
        Set<Integer> unique = new HashSet<>(numbers);
        assertThat(unique).hasSize(6);
    }

    @DisplayName("로또 숫자는 오름차순으로 정렬되어야 한다.")
    @Test
    void lottoNumbersShouldBeSorted() {
        Lotto lotto = LottoFactory.createRandomLotto();
        assertThat(lotto.getNumbers()).isSorted();
    }
}
