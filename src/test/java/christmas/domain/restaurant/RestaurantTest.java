package christmas.domain.restaurant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import christmas.exception.ExceptionMessage;

class RestaurantTest {
    private static Stream<Arguments> generateMenus() {
        final String appetizer = "애피타이저";
        final String main = "메인";
        final String dessert = "디저트";
        final String drink = "음료";

        return Stream.of(
                Arguments.of("양송이수프", 6_000, appetizer),
                Arguments.of("타파스", 5_500, appetizer),
                Arguments.of("시저샐러드", 8_000, appetizer),
                Arguments.of("티본스테이크", 55_000, main),
                Arguments.of("바비큐립", 54_000, main),
                Arguments.of("해산물파스타", 35_000, main),
                Arguments.of("크리스마스파스타", 25_000, main),
                Arguments.of("초코케이크", 15_000, dessert),
                Arguments.of("아이스크림", 5_000, dessert),
                Arguments.of("제로콜라", 3_000, drink),
                Arguments.of("레드와인", 60_000, drink),
                Arguments.of("샴페인", 25_000, drink)
        );
    }

    @DisplayName("식당 메뉴를 초기화한다.")
    @ParameterizedTest
    @MethodSource("generateMenus")
    void initRestaurantMenus(final String name, final int price, final String category) {
        // given
        final Restaurant restaurant = new Restaurant();

        // when
        final List<Menu> menus = restaurant.getMenus();

        // then
        assertThat(menus)
                .extracting("name", "price", "category")
                .contains(
                        tuple(name, price, category)
                );
    }

    @DisplayName("메뉴 이름으로 메뉴를 찾는다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크", "바비큐립", "해산물파스타"})
    void findMenuByName(final String name) {
        // given
        final Restaurant restaurant = new Restaurant();

        // when
        final Menu findMenu = restaurant.findMenuByName(name);

        // then
        assertThat(findMenu).hasFieldOrPropertyWithValue("name", name);
    }

    @DisplayName("메뉴 이름으로 메뉴를 찾지 못하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"투움바파스타", "소고기스테이크", "토마토파스타"})
    void findMenuByNameException(final String name) {
        // given
        final Restaurant restaurant = new Restaurant();

        // when
        // then
        assertThatThrownBy(() -> restaurant.findMenuByName(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.NOT_FOUND_MENU.format(name));
    }
}
