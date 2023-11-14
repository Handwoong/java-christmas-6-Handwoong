package christmas.domain.event.discount;

public enum PresentationItem {
    NONE("없음"),
    CHAMPAGNE("샴페인");

    private final String item;

    PresentationItem(final String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}
