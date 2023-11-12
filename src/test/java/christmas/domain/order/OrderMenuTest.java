package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import christmas.domain.restaurant.Menu;

class OrderMenuTest {
    @DisplayName("총 주문 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"1:55000", "2:110000", "3:165000", "10:550000"}, delimiter = ':')
    void calculateTotalPrice(final int quantity, final int totalPrice) {
        // given
        final Menu menu = new Menu("티본스테이크", 55_000, "메인");
        final OrderMenu orderMenu = new OrderMenu(menu, quantity);

        // when
        final int result = orderMenu.totalPrice();

        // then
        assertThat(result).isEqualTo(totalPrice);
    }
}
