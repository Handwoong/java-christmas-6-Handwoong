package christmas.domain.event.calender;

import java.util.Calendar;

public class ChristmasEventCalender implements EventCalender {
    public static final int START_DAY = 1;
    public static final int SUNDAY = 1;
    public static final int THURSDAY = 5;
    public static final int FRIDAY = 6;
    public static final int SATURDAY = 7;
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = Calendar.DECEMBER;
    private static final int CHRISTMAS_DAY = 25;

    private final Calendar calendar;

    public ChristmasEventCalender() {
        final Calendar cal = Calendar.getInstance();
        cal.set(EVENT_YEAR, EVENT_MONTH, START_DAY);
        this.calendar = cal;
    }

    @Override
    public boolean isValidDate(final int date) {
        return date <= CHRISTMAS_DAY;
    }

    @Override
    public boolean isEventDayOfWeek(final int date, final int start, final int end) {
        final int dayOfWeek = getDayOfWeek(date);
        return (start <= dayOfWeek) && (dayOfWeek <= end);
    }

    private int getDayOfWeek(final int date) {
        calendar.set(EVENT_YEAR, EVENT_MONTH, date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
