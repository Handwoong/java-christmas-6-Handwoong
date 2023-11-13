package christmas.domain.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import christmas.domain.event.benefit.ChristmasBenefit;
import christmas.domain.event.calender.EventCalender;
import christmas.domain.event.discount.ChristmasDiscountPolicy;
import christmas.domain.event.discount.DiscountPolicy;
import christmas.domain.event.discount.PresentationDiscountPolicy;
import christmas.domain.event.discount.SpecialDiscountPolicy;
import christmas.domain.event.discount.WeekDayDiscountPolicy;
import christmas.domain.event.discount.WeekendDiscountPolicy;
import christmas.domain.order.Order;
import christmas.dto.BenefitResponse;
import christmas.dto.DiscountResponse;

public class ChristmasEventProvider implements EventProvider {
    private final List<DiscountPolicy> discountPolicies;

    public ChristmasEventProvider() {
        this.discountPolicies = List.of(
                new ChristmasDiscountPolicy(),
                new WeekDayDiscountPolicy(),
                new WeekendDiscountPolicy(),
                new SpecialDiscountPolicy(),
                new PresentationDiscountPolicy()
        );
    }

    @Override
    public List<DiscountResponse> discount(final int date, final EventCalender calender, final Order order) {
        final List<DiscountResponse> discountResponses = new ArrayList<>();
        for (final DiscountPolicy discountPolicy : discountPolicies) {
            final DiscountResponse discountResponse = discountPolicy.discount(date, calender, order);
            discountResponses.add(discountResponse);
        }
        return discountResponses.stream()
                .filter(DiscountResponse::isNotNoneType)
                .toList();
    }

    @Override
    public BenefitResponse benefit(final int amount) {
        return Arrays.stream(ChristmasBenefit.values())
                .sorted(Comparator.comparing(ChristmasBenefit::getAmount).reversed())
                .map(christmasBenefit -> christmasBenefit.createBenefit(amount))
                .filter(BenefitResponse::isNotNoneBadge)
                .findFirst()
                .orElse(new BenefitResponse(ChristmasBenefit.NONE.getBadge()));
    }
}
