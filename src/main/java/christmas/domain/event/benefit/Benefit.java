package christmas.domain.event.benefit;

import christmas.dto.BenefitResponse;

public interface Benefit {
    BenefitResponse createBenefit(final int amount);
}
