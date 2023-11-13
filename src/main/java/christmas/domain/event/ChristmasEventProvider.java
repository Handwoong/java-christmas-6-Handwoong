package christmas.domain.event;

import java.util.Arrays;
import java.util.Comparator;

import christmas.domain.event.benefit.ChristmasBenefit;
import christmas.dto.BenefitResponse;

public class ChristmasEventProvider implements EventProvider {
    @Override
    public BenefitResponse benefit(final int amount) {
        final String noneBadge = ChristmasBenefit.NONE.getBadge();
        return Arrays.stream(ChristmasBenefit.values())
                .sorted(Comparator.comparing(ChristmasBenefit::getAmount).reversed())
                .map(christmasBenefit -> christmasBenefit.createBenefit(amount))
                .filter(response -> !response.name().equals(noneBadge))
                .findFirst()
                .orElse(new BenefitResponse(noneBadge));
    }
}
