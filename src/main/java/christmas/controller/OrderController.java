package christmas.controller;

import java.util.List;

import christmas.domain.event.EventProvider;
import christmas.domain.order.Order;
import christmas.domain.order.OrderDiscount;
import christmas.domain.restaurant.Restaurant;
import christmas.dto.DiscountResponse;
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
        final int date = inputView.readVisitDate();
        final List<OrderRequest> orderRequests = inputView.readOrderMenu();
        final Order order = Order.create(orderRequests, restaurant);
        outputView.printNotice(date);
        outputView.printOrderMenu(orderRequests);

        final OrderDiscount orderDiscount = new OrderDiscount(date, order, eventProvider);
        final int totalDiscount = orderDiscount.totalDiscount();
        final List<DiscountResponse> discountResponses = eventProvider.discount(date, order);
        outputView.printResult(order, orderDiscount);
    }
}
