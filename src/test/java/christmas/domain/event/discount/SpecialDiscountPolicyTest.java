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

class SpecialDiscountPolicyTest {
    private static EventCalender calender;

    @BeforeAll
    static void beforeAll() {
        calender = new ChristmasEventCalender();
    }

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(
                        3,
                        calender,
                        List.of(new OrderRequest("아이스크림", 1)),
                        0
                ),
                Arguments.of(
                        10,
                        calender,
                        List.of(new OrderRequest("아이스크림", 2)),
                        1_000
                ),
                Arguments.of(
                        25,
                        calender,
                        List.of(new OrderRequest("아이스크림", 2)),
                        1_000
                ),
                Arguments.of(
                        29,
                        calender,
                        List.of(new OrderRequest("아이스크림", 2)),
                        0
                ),
                Arguments.of(
                        30,
                        calender,
                        List.of(new OrderRequest("티본스테이크", 2)),
                        0
                )
        );
    }

    @DisplayName("특별 할인 금액을 계산한다.")
    @ParameterizedTest
    @MethodSource("generateData")
    void specialDiscount(final int date,
                         final EventCalender calender,
                         final List<OrderRequest> orderRequests,
                         final int discountAmount
    ) {
        // given
        final Restaurant restaurant = new Restaurant();
        final Order order = Order.create(orderRequests, restaurant);
        final SpecialDiscountPolicy specialDiscountPolicy = new SpecialDiscountPolicy();

        // when
        final int result = specialDiscountPolicy.discount(date, calender, order);

        // then
        assertThat(result).isEqualTo(discountAmount);
    }
}
