package christmas.domain.restaurant;

public class Menu {
    private final String name;
    private final int price;
    private final String category;

    public Menu(final String name, final int price, final String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
