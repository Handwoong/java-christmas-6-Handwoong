package christmas.domain.discount;

import static christmas.domain.discount.ChristmasEventCalender.SUNDAY;
import static christmas.domain.discount.ChristmasEventCalender.THURSDAY;

import christmas.domain.order.Order;

public class WeekDayDiscountPolicy implements DiscountPolicy {
    private static final String DISCOUNT_CATEGORY = "디저트";
    private static final int DISCOUNT_AMOUNT = 2_023;

    @Override
    public boolean support(final int date, final EventCalender calender, final Order order) {
        return (order.isTotalPriceGreaterThanOrEqual(MIN_ORDER_PRICE)) && (calender.isEventDayOfWeek(date, SUNDAY,
                THURSDAY));
    }

    @Override
    public int discount(final int date, final EventCalender calender, final Order order) {
        if (support(date, calender, order)) {
            return calculateDiscountAmount(order);
        }
        return 0;
    }

    private int calculateDiscountAmount(final Order order) {
        return DISCOUNT_AMOUNT * (order.calculateCategoryMenuQuantity(DISCOUNT_CATEGORY));
    }
}
