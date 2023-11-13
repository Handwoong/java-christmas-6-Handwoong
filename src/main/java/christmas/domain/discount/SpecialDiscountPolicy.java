package christmas.domain.discount;

import static christmas.domain.discount.ChristmasEventCalender.SUNDAY;

import christmas.domain.order.Order;

public class SpecialDiscountPolicy implements DiscountPolicy {
    private static final int SPECIAL_DAY = 25;
    private static final int DISCOUNT_AMOUNT = 1_000;

    @Override
    public boolean support(final int date, final EventCalender calender, final Order order) {
        return (order.isTotalPriceGreaterThanOrEqual(MIN_ORDER_PRICE)) && isSpecialDay(date, calender);

    }

    private boolean isSpecialDay(final int date, final EventCalender calender) {
        return (calender.isEventDayOfWeek(date, SUNDAY, SUNDAY)) || (date == SPECIAL_DAY);
    }

    @Override
    public int discount(final int date, final EventCalender calender, final Order order) {
        if (support(date, calender, order)) {
            return DISCOUNT_AMOUNT;
        }
        return 0;
    }
}
