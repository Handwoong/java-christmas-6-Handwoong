package christmas.domain.restaurant;

import static christmas.exception.ExceptionMessage.IN_VALID_ORDER;

import java.util.List;

public class Restaurant {
    private final List<Menu> menus;

    public Restaurant() {
        this.menus = RestaurantSeedMenu.initMenus();
    }

    public Menu findMenuByName(final String name) {
        return menus.stream()
                .filter(menu -> menu.matchName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(IN_VALID_ORDER.format(name)));
    }

    public List<Menu> getMenus() {
        return List.copyOf(menus);
    }
}
