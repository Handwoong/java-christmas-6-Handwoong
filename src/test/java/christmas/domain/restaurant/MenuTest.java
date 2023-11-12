package christmas.domain.restaurant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuTest {
    @DisplayName("메뉴의 가격과 수량을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"1:6000", "2:12000", "3:18000", "10:60000"}, delimiter = ':')
    void calculateMenuPriceByQuantity(final int quantity, final int totalPrice) {
        // given
        final Menu menu = new Menu("양송이수프", 6_000, "애피타이저");

        // when
        final int result = menu.price(quantity);

        // then
        assertThat(result).isEqualTo(totalPrice);
    }
}
