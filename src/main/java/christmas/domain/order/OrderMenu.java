package christmas.domain.order;

import christmas.domain.restaurant.Menu;

public class OrderMenu {
    private final Menu menu;
    private final int quantity;

    public OrderMenu(final Menu menu, final int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public int totalPrice() {
        return menu.price(quantity);
    }
}
