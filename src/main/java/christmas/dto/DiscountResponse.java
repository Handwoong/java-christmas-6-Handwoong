package christmas.dto;

import christmas.domain.event.ChristmasEventType;

public record DiscountResponse(
        String type,
        int amount
) {
    public static DiscountResponse of(final ChristmasEventType type, final int amount) {
        return new DiscountResponse(type.getType(), amount);
    }
}
