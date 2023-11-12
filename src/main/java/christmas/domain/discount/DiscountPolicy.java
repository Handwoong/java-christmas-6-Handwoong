package christmas.domain.discount;

import christmas.domain.order.Order;

public interface DiscountPolicy {
    public static final int MIN_ORDER_PRICE = 10_000;

    boolean support(final int date, final EventCalender calender, final Order order);

    int discount(final int date, final EventCalender calender, final Order order);
}
