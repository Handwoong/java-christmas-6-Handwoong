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
import christmas.dto.DiscountResponse;
import christmas.dto.OrderRequest;

class WeekDayDiscountPolicyTest {
    private static EventCalender calender;

    @BeforeAll
    static void beforeAll() {
        calender = new ChristmasEventCalender();
    }

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(
                        16,
                        calender,
                        List.of(new OrderRequest("아이스크림", 1)),
                        0
                ),
                Arguments.of(
                        18,
                        calender,
                        List.of(new OrderRequest("아이스크림", 2)),
                        4_046
                ),
                Arguments.of(
                        29,
                        calender,
                        List.of(new OrderRequest("아이스크림", 5)),
                        0
                ),
                Arguments.of(
                        31,
                        calender,
                        List.of(
                                new OrderRequest("아이스크림", 2),
                                new OrderRequest("초코케이크", 2)
                        ),
                        8_092
                )
        );
    }

    @DisplayName("평일 할인 금액을 계산한다.")
    @ParameterizedTest
    @MethodSource("generateData")
    void weekDayDiscount(final int date,
                         final EventCalender calender,
                         final List<OrderRequest> orderRequests,
                         final int discountAmount
    ) {
        // given
        final Restaurant restaurant = new Restaurant();
        final Order order = Order.create(orderRequests, restaurant);
        final WeekDayDiscountPolicy weekDayDiscountPolicy = new WeekDayDiscountPolicy();

        // when
        final DiscountResponse result = weekDayDiscountPolicy.discount(date, calender, order);

        // then
        assertThat(result.amount()).isEqualTo(discountAmount);
    }
}
