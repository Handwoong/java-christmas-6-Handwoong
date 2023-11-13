package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import christmas.domain.event.calender.ChristmasEventCalender;
import christmas.domain.event.calender.EventCalender;
import christmas.domain.order.Order;
import christmas.domain.restaurant.Restaurant;
import christmas.dto.OrderRequest;

class PresentationDiscountPolicyTest {
    private static EventCalender calender;

    @BeforeAll
    static void beforeAll() {
        calender = new ChristmasEventCalender();
    }

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(
                        1,
                        calender,
                        List.of(
                                new OrderRequest("아이스크림", 1),
                                new OrderRequest("시저샐러드", 1)
                        ),
                        0
                ),
                Arguments.of(
                        1,
                        calender,
                        List.of(
                                new OrderRequest("바비큐립", 1),
                                new OrderRequest("티본스테이크", 1),
                                new OrderRequest("초코케이크", 1)
                        ),
                        25_000
                )
        );
    }

    @DisplayName("증정 할인 금액을 계산한다.")
    @ParameterizedTest
    @MethodSource("generateData")
    void presentationDiscount(final int date,
                              final EventCalender calender,
                              final List<OrderRequest> orderRequests,
                              final int discountAmount
    ) {
        // given
        final Restaurant restaurant = new Restaurant();
        final Order order = Order.create(orderRequests, restaurant);
        final PresentationDiscountPolicy presentationDiscountPolicy = new PresentationDiscountPolicy();

        // when
        final int result = presentationDiscountPolicy.discount(date, calender, order);

        // then
        assertThat(result).isEqualTo(discountAmount);
    }
}
