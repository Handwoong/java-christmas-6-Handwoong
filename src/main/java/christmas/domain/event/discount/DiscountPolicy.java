package christmas.domain.event.discount;

import christmas.domain.event.calender.EventCalender;
import christmas.domain.order.Order;
import christmas.dto.DiscountResponse;

public interface DiscountPolicy {
    int MIN_ORDER_PRICE = 10_000;

    boolean support(final int date, final EventCalender calender, final Order order);

    DiscountResponse discount(final int date, final EventCalender calender, final Order order);
}
