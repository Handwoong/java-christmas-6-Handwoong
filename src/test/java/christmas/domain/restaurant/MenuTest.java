package christmas.domain.restaurant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {
    @DisplayName("메뉴의 가격과 수량을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"1:6000", "2:12000", "3:18000", "10:60000"}, delimiter = ':')
    void calculateMenuPriceByQuantity(final int quantity, final int totalPrice) {
        // given
        final Menu menu = new Menu("양송이수프", 6_000, "애피타이저");

        // when
        final int result = menu.price(quantity);

        // then
        assertThat(result).isEqualTo(totalPrice);
    }

    @DisplayName("전달받은 이름과 메뉴의 이름이 같다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드"})
    void matchNameTrue(final String name) {
        // given
        final Menu menu = new Menu(name, 10_000, "애피타이저");

        // when
        final boolean result = menu.matchName(name);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("전달받은 이름과 메뉴의 이름이 다르다.")
    @ParameterizedTest
    @CsvSource(value = {"양송이수프:티본스테이크", "타파스:초코케이크", "시저샐러드:제로콜라"}, delimiter = ':')
    void matchNameFalse(final String menuName, final String targetName) {
        // given
        final Menu menu = new Menu(menuName, 10_000, "애피타이저");

        // when
        final boolean result = menu.matchName(targetName);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("전달받은 카테고리 이름과 메뉴의 카테고리 이름이 같다.")
    @ParameterizedTest
    @ValueSource(strings = {"애피타이저", "메인", "디저트", "음료"})
    void matchCategoryTrue(final String category) {
        // given
        final Menu menu = new Menu("토마토파스타", 10_000, category);

        // when
        final boolean result = menu.matchCategory(category);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("전달받은 카테고리 이름과 메뉴의 카테고리 이름이 다르다.")
    @ParameterizedTest
    @CsvSource(value = {"애피타이저:메인", "메인:디저트", "디저트:음료"}, delimiter = ':')
    void matchCategoryFalse(final String menuCategory, final String targetCategory) {
        // given
        final Menu menu = new Menu("토마토파스타", 10_000, menuCategory);

        // when
        final boolean result = menu.matchCategory(targetCategory);

        // then
        assertThat(result).isFalse();
    }
}
