package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("카테고리가 일치하면 주문 수량을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void categoryMenuQuantity(final int quantity) {
        // given
        final String category = "메인";
        final Menu menu = new Menu("티본스테이크", 55_000, category);
        final OrderMenu orderMenu = new OrderMenu(menu, quantity);

        // when
        final int result = orderMenu.categoryMenuQuantity(category);

        // then
        assertThat(result).isEqualTo(quantity);
    }

    @DisplayName("카테고리가 다르면 0을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1:애피타이저:메인", "2:메인:디저트", "3:디저트:음료", "10:메인:음료"}, delimiter = ':')
    void categoryMenuQuantityZero(final int quantity, final String category, final String targetCategory) {
        // given
        final Menu menu = new Menu("티본스테이크", 55_000, category);
        final OrderMenu orderMenu = new OrderMenu(menu, quantity);

        // when
        final int result = orderMenu.categoryMenuQuantity(targetCategory);

        // then
        assertThat(result).isZero();
    }
}
