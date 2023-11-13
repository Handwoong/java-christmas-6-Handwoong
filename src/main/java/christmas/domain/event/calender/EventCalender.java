package christmas.domain.event.calender;

public interface EventCalender {
    boolean isValidDate(final int date);

    boolean isEventDayOfWeek(final int date, final int start, final int end);
}
