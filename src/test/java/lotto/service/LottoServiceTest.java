package lotto.service;

import lotto.dto.LottoResponseDto;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoServiceTest {
    @Test
    void 구입금액에_따라_로또가_여러장_생성된다() {
        LottoService service = new LottoService();
        List<LottoResponseDto> result = service.buyLottos(3000);
        assertThat(result).hasSize(3);
    }

    @Test
    void 구입금액이_1000원_단위가_아니면_예외_발생() {
        LottoService service = new LottoService();

        assertThatThrownBy(() -> service.buyLottos(2500))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입금액이_0원_이하면_예외발생() {
        LottoService service = new LottoService();

        assertThatThrownBy(() -> service.buyLottos(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
