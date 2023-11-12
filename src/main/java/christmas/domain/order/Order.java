package christmas.domain.order;

import java.util.ArrayList;
import java.util.List;

import christmas.domain.restaurant.Menu;
import christmas.domain.restaurant.Restaurant;
import christmas.dto.OrderRequest;

public class Order {
    private final List<OrderMenu> orderMenus;

    private Order(final List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public static Order create(final List<OrderRequest> orderRequests, final Restaurant restaurant) {
        final List<OrderMenu> orderMenus = new ArrayList<>();
        for (final OrderRequest orderRequest : orderRequests) {
            final Menu findMenu = restaurant.findMenuByName(orderRequest.name());
            final OrderMenu orderMenu = new OrderMenu(findMenu, orderRequest.quantity());
            orderMenus.add(orderMenu);
        }
        return new Order(orderMenus);
    }

    public boolean isTotalPriceGreaterThan(final int price) {
        return totalPrice() > price;
    }

    public int totalPrice() {
        return orderMenus.stream()
                .map(OrderMenu::totalPrice)
                .reduce(0, Integer::sum);
    }

    public List<OrderMenu> getOrderMenus() {
        return List.copyOf(orderMenus);
    }
}
