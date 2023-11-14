package christmas.dto;

import christmas.domain.event.ChristmasEventType;
import christmas.domain.event.discount.PresentationItem;

public record DiscountResponse(
        ChristmasEventType event,
        int amount,
        PresentationItem item
) {
    public static DiscountResponse of(final ChristmasEventType event, final int amount, final PresentationItem item) {
        return new DiscountResponse(event, amount, item);
    }

    public boolean isNotNoneType() {
        return event != ChristmasEventType.NONE;
    }
}
