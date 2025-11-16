package airport.domain.calendar;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public final class FixedHolidayTravelCalendar implements TravelCalendar {

    private static final double WEEKDAY_FACTOR = 1.0;

    private final Set<LocalDate> holidays;

    public FixedHolidayTravelCalendar(final Set<LocalDate> holidays) {
        this.holidays = holidays;
    }

    @Override
    public DayType dayTypeOf(final LocalDate date) {
        if (holidays.contains(date)) {
            return DayType.HOLIDAY;
        }
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (isWeekend(dayOfWeek)) {
            return DayType.WEEKEND;
        }
        return DayType.WEEKDAY;
    }

    private boolean isWeekend(final DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY
                || dayOfWeek == DayOfWeek.SUNDAY;
    }

    @Override
    public double congestionFactorOf(final LocalDate date) {
        DayType dayType = dayTypeOf(date);
        if (dayType == DayType.WEEKDAY) {
            return WEEKDAY_FACTOR;
        }
        return dayType.taskDelayFactor();
    }

    @Override
    public DayType resolveDayType(final LocalDate date) {
        if (holidays.contains(date)) {
            return DayType.HOLIDAY;
        }
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return DayType.WEEKEND;
        }
        return DayType.WEEKDAY;
    }
}
