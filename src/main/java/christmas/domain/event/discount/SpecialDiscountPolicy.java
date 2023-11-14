package christmas.domain.event.discount;

import static christmas.domain.event.calender.ChristmasEventCalender.SUNDAY;

import christmas.domain.event.ChristmasEventType;
import christmas.domain.event.calender.EventCalender;
import christmas.domain.order.Order;
import christmas.dto.DiscountResponse;

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
    public DiscountResponse discount(final int date, final EventCalender calender, final Order order) {
        if (support(date, calender, order)) {
            return DiscountResponse.of(ChristmasEventType.SPECIAL, DISCOUNT_AMOUNT, PresentationItem.NONE);
        }
        return DiscountResponse.of(ChristmasEventType.NONE, 0, PresentationItem.NONE);
    }
}
