package christmas.domain.discount;

import java.util.Calendar;

public class ChristmasEventCalender implements EventCalender {
    public static final int START_DAY = 1;
    private static final int EVENT_YEAR = 2023;
    private static final int CHRISTMAS_DAY = 25;

    private final Calendar calendar;

    public ChristmasEventCalender() {
        final Calendar cal = Calendar.getInstance();
        cal.set(EVENT_YEAR, Calendar.DECEMBER, START_DAY);
        this.calendar = cal;
    }

    public boolean isValidDate(final int date) {
        return date <= CHRISTMAS_DAY;
    }
}
