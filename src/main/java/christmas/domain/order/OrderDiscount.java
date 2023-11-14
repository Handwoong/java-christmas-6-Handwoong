package christmas.domain.order;

import java.util.List;

import christmas.domain.event.ChristmasEventType;
import christmas.domain.event.EventProvider;
import christmas.domain.event.discount.PresentationDiscountPolicy;
import christmas.dto.BenefitResponse;
import christmas.dto.DiscountResponse;

public class OrderDiscount {
    private final EventProvider eventProvider;
    private final List<DiscountResponse> discountResponses;

    public OrderDiscount(final int date, final Order order, final EventProvider eventProvider) {
        this.eventProvider = eventProvider;
        this.discountResponses = eventProvider.discount(date, order);
    }

    public int totalDiscount() {
        return discountResponses.stream()
                .map(DiscountResponse::amount)
                .reduce(0, Integer::sum);
    }

    public String presentation() {
        if (containsPresentation()) {
            return PresentationDiscountPolicy.PRESENTATION_MENU_NAME + " 1개";
        }
        return "없음";
    }

    public int paymentAmount(final Order order) {
        if (containsPresentation()) {
            return order.totalPrice() - totalDiscount() + PresentationDiscountPolicy.PRESENTATION_MENU_PRICE;
        }
        return order.totalPrice() - totalDiscount();
    }

    private boolean containsPresentation() {
        return discountResponses.stream()
                .anyMatch(response -> response.type().equals(ChristmasEventType.PRESENTATION.getType()));
    }

    public BenefitResponse benefit() {
        return eventProvider.benefit(totalDiscount());
    }

    public boolean isNoneDiscount() {
        return discountResponses.isEmpty();
    }

    public List<DiscountResponse> getDiscountResponses() {
        return List.copyOf(discountResponses);
    }
}
