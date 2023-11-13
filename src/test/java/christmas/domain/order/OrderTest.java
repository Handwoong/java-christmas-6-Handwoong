package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import christmas.domain.restaurant.Restaurant;
import christmas.dto.OrderRequest;

class OrderTest {
    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new OrderRequest("양송이수프", 3),
                                new OrderRequest("바비큐립", 2)
                        ),
                        126_000
                ),
                Arguments.of(
                        List.of(
                                new OrderRequest("해산물파스타", 5),
                                new OrderRequest("레드와인", 1),
                                new OrderRequest("샴페인", 1)
                        ),
                        260_000
                ),
                Arguments.of(
                        List.of(
                                new OrderRequest("시저샐러드", 1)
                        ),
                        8_000
                )
        );
    }

    @Test
    @DisplayName("주문을 생성한다.")
    void createOrder() {
        // given
        final Restaurant restaurant = new Restaurant();
        final List<OrderRequest> orderRequests = List.of(
                new OrderRequest("양송이수프", 3),
                new OrderRequest("바비큐립", 2)
        );

        // when
        final Order order = Order.create(orderRequests, restaurant);
        final List<OrderMenu> orderMenus = order.getOrderMenus();

        // then
        assertThat(orderMenus)
                .extracting("menu", "quantity")
                .contains(
                        tuple(restaurant.findMenuByName("양송이수프"), 3),
                        tuple(restaurant.findMenuByName("바비큐립"), 2)
                );
    }

    @DisplayName("총 주문 금액을 계산한다.")
    @ParameterizedTest
    @MethodSource("generateData")
    void calculateTotalPrice(final List<OrderRequest> orderRequests, final int totalPrice) {
        // given
        final Restaurant restaurant = new Restaurant();
        final Order order = Order.create(orderRequests, restaurant);

        // when
        final int result = order.totalPrice();

        // then
        assertThat(result).isEqualTo(totalPrice);
    }

    @DisplayName("총 주문 금액이 최소 주문 금액보다 큰지 비교한다.")
    @ParameterizedTest
    @MethodSource("generateData")
    void isTotalPriceGreaterThan(final List<OrderRequest> orderRequests, final int totalPrice) {
        // given
        final int minPrice = 10_000;
        final Restaurant restaurant = new Restaurant();
        final Order order = Order.create(orderRequests, restaurant);

        // when
        final boolean result = order.isTotalPriceGreaterThanOrEqual(minPrice);

        // then
        assertThat(result).isEqualTo(totalPrice > minPrice);
    }

    @Test
    @DisplayName("주문한 메뉴들의 일치하는 카테고리의 메뉴 수량을 계산한다.")
    void calculateCategoryMenuQuantity() {
        // given
        final Restaurant restaurant = new Restaurant();
        final List<OrderRequest> orderRequests = List.of(
                new OrderRequest("티본스테이크", 3),
                new OrderRequest("바비큐립", 2)
        );
        final Order order = Order.create(orderRequests, restaurant);

        // when
        final int result = order.calculateCategoryMenuQuantity("메인");

        // then
        assertThat(result).isEqualTo(5);
    }
}
