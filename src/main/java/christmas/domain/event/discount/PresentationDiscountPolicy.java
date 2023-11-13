package christmas.domain.event.discount;

import christmas.domain.event.calender.EventCalender;
import christmas.domain.order.Order;

public class PresentationDiscountPolicy implements DiscountPolicy {
    public static final String PRESENTATION_MENU_NAME = "샴페인";
    private static final int PRESENTATION_MENU_PRICE = 25_000;
    private static final int EVENT_ORDER_PRICE = 120_000;

    @Override
    public boolean support(final int date, final EventCalender calender, final Order order) {
        return order.isTotalPriceGreaterThanOrEqual(EVENT_ORDER_PRICE);
    }

    @Override
    public int discount(final int date, final EventCalender calender, final Order order) {
        if (support(date, calender, order)) {
            return PRESENTATION_MENU_PRICE;
        }
        return 0;
    }
}
