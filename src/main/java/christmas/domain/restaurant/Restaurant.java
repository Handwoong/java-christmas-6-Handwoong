package christmas.domain.restaurant;

import java.util.List;

public class Restaurant {
    private final List<Menu> menus;

    public Restaurant() {
        this.menus = RestaurantSeedMenu.initMenus();
    }

    public List<Menu> getMenus() {
        return List.copyOf(menus);
    }
}
