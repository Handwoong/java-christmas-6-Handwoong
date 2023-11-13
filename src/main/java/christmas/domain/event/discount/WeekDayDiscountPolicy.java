package christmas.domain.event.discount;

import static christmas.domain.event.calender.ChristmasEventCalender.SUNDAY;
import static christmas.domain.event.calender.ChristmasEventCalender.THURSDAY;

import christmas.domain.event.ChristmasEventType;
import christmas.domain.event.calender.EventCalender;
import christmas.domain.order.Order;
import christmas.dto.DiscountResponse;

public class WeekDayDiscountPolicy implements DiscountPolicy {
    private static final String DISCOUNT_CATEGORY = "디저트";
    private static final int DISCOUNT_AMOUNT = 2_023;

    @Override
    public boolean support(final int date, final EventCalender calender, final Order order) {
        return (order.isTotalPriceGreaterThanOrEqual(MIN_ORDER_PRICE)) &&
                (calender.isEventDayOfWeek(date, SUNDAY, THURSDAY));
    }

    @Override
    public DiscountResponse discount(final int date, final EventCalender calender, final Order order) {
        if (support(date, calender, order)) {
            return DiscountResponse.of(ChristmasEventType.DAY_OF_WEEK, calculateDiscountAmount(order));
        }
        return DiscountResponse.of(ChristmasEventType.NONE, 0);
    }

    private int calculateDiscountAmount(final Order order) {
        return DISCOUNT_AMOUNT * (order.calculateCategoryMenuQuantity(DISCOUNT_CATEGORY));
    }
}
