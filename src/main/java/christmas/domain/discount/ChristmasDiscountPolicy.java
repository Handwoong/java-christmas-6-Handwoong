package christmas.domain.discount;

import static christmas.domain.discount.ChristmasEventCalender.START_DAY;

import christmas.domain.order.Order;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private static final int MIN_DISCOUNT_AMOUNT = 1000;
    private static final int DISCOUNT_INCREASE_AMOUNT_UNIT = 100;

    @Override
    public boolean support(final int date, final EventCalender calender, final Order order) {
        return (order.isTotalPriceGreaterThan(MIN_ORDER_PRICE)) && calender.isValidDate(date);
    }

    @Override
    public int discount(final int date, final EventCalender calender, final Order order) {
        if (!support(date, calender, order)) {
            return 0;
        }
        return calculateDiscountAmount(date);
    }

    private int calculateDiscountAmount(final int date) {
        return MIN_DISCOUNT_AMOUNT + ((date - START_DAY) * DISCOUNT_INCREASE_AMOUNT_UNIT);
    }
}
