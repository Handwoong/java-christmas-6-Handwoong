package christmas.domain.event;

import java.util.List;

import christmas.domain.order.Order;
import christmas.dto.BenefitResponse;
import christmas.dto.DiscountResponse;

public interface EventProvider {
    List<DiscountResponse> discount(final int date, Order order);

    BenefitResponse benefit(final int amount);
}
