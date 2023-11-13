package christmas.domain.event;

import christmas.dto.BenefitResponse;

public interface EventProvider {
    BenefitResponse benefit(final int amount);
}
