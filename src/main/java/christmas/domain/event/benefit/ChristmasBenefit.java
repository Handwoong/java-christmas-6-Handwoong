package christmas.domain.event.benefit;

import christmas.dto.BenefitResponse;

public enum ChristmasBenefit implements Benefit {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String badge;
    private final int amount;

    ChristmasBenefit(final String badge, final int amount) {
        this.badge = badge;
        this.amount = amount;
    }

    @Override
    public BenefitResponse createBenefit(final int amount) {
        if (this.amount <= amount) {
            return new BenefitResponse(badge);
        }
        return new BenefitResponse(NONE.getBadge());
    }

    public String getBadge() {
        return badge;
    }

    public int getAmount() {
        return amount;
    }
}
