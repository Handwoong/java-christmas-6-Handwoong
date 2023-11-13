package christmas.domain.discount;

public interface EventCalender {
    boolean isValidDate(final int date);

    boolean isEventDayOfWeek(final int date, final int start, final int end);
}
