package airport.domain.calendar;

public enum DayType {
    WEEKDAY(1.0),
    WEEKEND(1.2),
    HOLIDAY(1.3),
    PEAK_SEASON(1.4);

    private final double baseCongestionFactor;

    DayType(final double baseCongestionFactor) {
        this.baseCongestionFactor = baseCongestionFactor;
    }

    public double baseCongestionFactor() {
        return baseCongestionFactor;
    }

}
