package christmas.domain.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum RestaurantSeedMenu {
    APPETIZER(
            "애피타이저",
            Map.of(
                    "양송이수프", 6_000,
                    "타파스", 5_500,
                    "시저샐러드", 8_000
            )
    ),
    MAIN(
            "메인",
            Map.of(
                    "티본스테이크", 55_000,
                    "바비큐립", 54_000,
                    "해산물파스타", 35_000,
                    "크리스마스파스타", 25_000
            )
    ),
    DESSERT(
            "디저트",
            Map.of(
                    "초코케이크", 15_000,
                    "아이스크림", 5_000
            )
    ),
    DRINK(
            "음료",
            Map.of(
                    "제로콜라", 3_000,
                    "레드와인", 60_000,
                    "샴페인", 25_000
            )
    );

    private final String category;
    private final Map<String, Integer> menus;

    RestaurantSeedMenu(final String category, final Map<String, Integer> menus) {
        this.category = category;
        this.menus = menus;
    }

    public static List<Menu> initMenus() {
        final List<Menu> menus = new ArrayList<>();
        for (final RestaurantSeedMenu seedMenu : RestaurantSeedMenu.values()) {
            seedMenu.menus
                    .entrySet()
                    .stream()
                    .map(menu -> new Menu(menu.getKey(), menu.getValue(), seedMenu.category))
                    .forEach(menus::add);
        }
        return menus;
    }
}
