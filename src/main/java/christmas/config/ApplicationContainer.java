package christmas.config;

import christmas.controller.OrderController;
import christmas.domain.event.ChristmasEventProvider;
import christmas.domain.event.EventProvider;
import christmas.domain.event.calender.ChristmasEventCalender;
import christmas.domain.event.calender.EventCalender;
import christmas.domain.restaurant.Restaurant;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ApplicationContainer {
    public OrderController getOrderController() {
        return new OrderController(
                getInputView(),
                getOutputView(),
                getEventProvider(),
                getRestaurant()
        );
    }

    private InputView getInputView() {
        return InputView.getInstance();
    }

    private OutputView getOutputView() {
        return OutputView.getInstance();
    }

    private EventProvider getEventProvider() {
        return new ChristmasEventProvider(getEventCalender());
    }

    private EventCalender getEventCalender() {
        return new ChristmasEventCalender();
    }

    private Restaurant getRestaurant() {
        return new Restaurant();
    }
}
