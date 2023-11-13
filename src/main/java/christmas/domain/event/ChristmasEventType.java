package christmas.domain.event;

public enum ChristmasEventType {
    NONE("없음"),
    D_DAY("크리스마스 디데이 할인"),
    DAY_OF_WEEK("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    PRESENTATION("증정 이벤트");

    private final String type;

    ChristmasEventType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
