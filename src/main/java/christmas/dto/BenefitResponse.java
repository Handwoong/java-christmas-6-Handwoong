package christmas.dto;

import christmas.domain.event.benefit.ChristmasBenefit;

public record BenefitResponse(
        String name
) {
    public boolean isNotNoneBadge() {
        return !name.equals(ChristmasBenefit.NONE.getBadge());
    }
}
