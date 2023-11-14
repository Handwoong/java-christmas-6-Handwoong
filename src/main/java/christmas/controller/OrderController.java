package christmas.controller;

import java.util.List;

import christmas.domain.event.EventProvider;
import christmas.domain.order.Order;
import christmas.domain.order.OrderDiscount;
import christmas.domain.restaurant.Restaurant;
import christmas.dto.OrderRequest;
import christmas.view.InputView;
import christmas.view.OutputView;

public class OrderController {
    private final InputView inputView;
    private final OutputView outputView;
    private final EventProvider eventProvider;
    private final Restaurant restaurant;

    public OrderController(final InputView inputView,
                           final OutputView outputView,
                           final EventProvider eventProvider,
                           final Restaurant restaurant
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventProvider = eventProvider;
        this.restaurant = restaurant;
    }

    public void run() {
        final int date = visitDate();
        final Order order = createOrder(date);
        discountResult(date, order);
    }

    private int visitDate() {
        return inputView.readVisitDate();
    }

    private Order createOrder(final int date) {
        final List<OrderRequest> orderRequests = inputView.readOrderMenu(restaurant);
        final Order order = Order.create(orderRequests, restaurant);
        outputView.printNotice(date);
        outputView.printOrderMenu(orderRequests);
        return order;
    }

    private void discountResult(final int date, final Order order) {
        final OrderDiscount orderDiscount = new OrderDiscount(date, order, eventProvider);
        outputView.printResult(order, orderDiscount);
    }
}
