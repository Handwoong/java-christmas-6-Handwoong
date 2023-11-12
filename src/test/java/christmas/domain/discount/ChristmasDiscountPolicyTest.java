package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import christmas.domain.order.Order;
import christmas.domain.restaurant.Restaurant;
import christmas.dto.OrderRequest;

class ChristmasDiscountPolicyTest {
    private static EventCalender calender;

    @BeforeAll
    static void beforeAll() {
        calender = new ChristmasEventCalender();
    }

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(
                        25,
                        calender,
                        List.of(new OrderRequest("아이스크림", 1)),
                        0
                ),
                Arguments.of(
                        27,
                        calender,
                        List.of(new OrderRequest("바비큐립", 1)),
                        0
                ),
                Arguments.of(
                        20,
                        calender,
                        List.of(new OrderRequest("바비큐립", 1)),
                        2900
                ),
                Arguments.of(
                        2,
                        calender,
                        List.of(new OrderRequest("바비큐립", 1)),
                        1100
                )
        );
    }

    @DisplayName("크리스마스 디데이 할인 금액을 계산한다.")
    @ParameterizedTest
    @MethodSource("generateData")
    void christmasDiscount(final int date,
                           final EventCalender calender,
                           final List<OrderRequest> orderRequests,
                           final int discountAmount
    ) {
        // given
        final Restaurant restaurant = new Restaurant();
        final Order order = Order.create(orderRequests, restaurant);
        final ChristmasDiscountPolicy christmasDiscountPolicy = new ChristmasDiscountPolicy();

        // when
        final int result = christmasDiscountPolicy.discount(date, calender, order);

        // then
        assertThat(result).isEqualTo(discountAmount);
    }
}
