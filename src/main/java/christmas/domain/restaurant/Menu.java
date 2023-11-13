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

    public boolean matchName(final String name) {
        return this.name.equals(name);
    }

    public boolean matchCategory(final String category) {
        return this.category.equals(category);
    }

    public int price(final int quantity) {
        return price * quantity;
    }
}
