package airport.domain.itinerary;

import java.time.Duration;

public final class TimeWindowConstraint {

    private final Duration maxDuration;

    public TimeWindowConstraint(final Duration maxDuration) {
        this.maxDuration = maxDuration;
    }

    public boolean isSatisfiedBy(final Duration duration) {
        return duration.compareTo(maxDuration) <= 0;
    }

    public Duration maxDuration() {
        return maxDuration;
    }
}
