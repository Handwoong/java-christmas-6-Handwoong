package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.restaurant.Restaurant;
import christmas.dto.OrderRequest;

class OrderTest {
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
}
