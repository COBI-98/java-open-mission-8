package airport.domain.calendar;

public enum DayType {
    WEEKDAY(1.0, 1.0),
    WEEKEND(1.1, 1.05),
    HOLIDAY(1.2, 1.1);

    private final double taskDelayFactor;
    private final double transportDelayFactor;

    DayType(final double taskDelayFactor, final double transportDelayFactor) {
        this.taskDelayFactor = taskDelayFactor;
        this.transportDelayFactor = transportDelayFactor;
    }

    public double taskDelayFactor() {
        return taskDelayFactor;
    }

    public double transportDelayFactor() {
        return transportDelayFactor;
    }
}
