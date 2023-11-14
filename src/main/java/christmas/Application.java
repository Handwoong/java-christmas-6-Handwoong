package christmas;

import christmas.config.ApplicationContainer;
import christmas.controller.OrderController;

public class Application {
    public static void main(final String[] args) {
        final ApplicationContainer container = new ApplicationContainer();
        final OrderController orderController = container.getOrderController();
        orderController.run();
    }
}
