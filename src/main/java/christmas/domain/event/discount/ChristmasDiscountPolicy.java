package christmas.domain.event.discount;

import static christmas.domain.event.calender.ChristmasEventCalender.START_DAY;

import christmas.domain.event.ChristmasEventType;
import christmas.domain.event.calender.EventCalender;
import christmas.domain.order.Order;
import christmas.dto.DiscountResponse;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private static final int MIN_DISCOUNT_AMOUNT = 1000;
    private static final int DISCOUNT_INCREASE_AMOUNT_UNIT = 100;

    @Override
    public boolean support(final int date, final EventCalender calender, final Order order) {
        return (order.isTotalPriceGreaterThanOrEqual(MIN_ORDER_PRICE)) && calender.isValidDate(date);
    }

    @Override
    public DiscountResponse discount(final int date, final EventCalender calender, final Order order) {
        if (support(date, calender, order)) {
            final int discountAmount = calculateDiscountAmount(date);
            return DiscountResponse.of(ChristmasEventType.D_DAY, discountAmount);
        }
        return DiscountResponse.of(ChristmasEventType.NONE, 0);
    }

    private int calculateDiscountAmount(final int date) {
        return MIN_DISCOUNT_AMOUNT + ((date - START_DAY) * DISCOUNT_INCREASE_AMOUNT_UNIT);
    }
}
