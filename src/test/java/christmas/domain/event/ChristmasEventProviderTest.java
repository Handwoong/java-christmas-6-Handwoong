package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import christmas.dto.BenefitResponse;

class ChristmasEventProviderTest {
    @DisplayName("혜택 금액에 따라 이벤트 배지를 부여한다.")
    @ParameterizedTest
    @CsvSource(value = {"0:없음", "5000:별", "10000:트리", "20000:산타"}, delimiter = ':')
    void issuedBadgeByTotalBenefitAmount(final int amount, final String badge) {
        // given
        final ChristmasEventProvider provider = new ChristmasEventProvider();

        // when
        final BenefitResponse result = provider.benefit(amount);

        // then
        assertThat(result.name()).isEqualTo(badge);
    }
}
