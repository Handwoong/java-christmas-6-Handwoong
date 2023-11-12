package christmas.domain.restaurant;

import static christmas.exception.ExceptionMessage.NOT_FOUND_MENU;

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
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MENU.format(name)));
    }

    public List<Menu> getMenus() {
        return List.copyOf(menus);
    }
}
