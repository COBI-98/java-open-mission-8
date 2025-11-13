package airport.domain.itinerary;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public final class ItineraryStep {


    private final LocalDateTime start;
    private final LocalDateTime end;
    private final StepType stepType;
    private final String description;

    public ItineraryStep(
            final LocalDateTime start,
            final LocalDateTime end,
            final StepType stepType,
            final String description
    ) {
        this.start = Objects.requireNonNull(start);
        this.end = Objects.requireNonNull(end);
        this.stepType = Objects.requireNonNull(stepType);
        this.description = Objects.requireNonNull(description);
    }


    public Duration duration() {
        return Duration.between(start, end);
    }

    public LocalDateTime start() {
        return start;
    }

    public LocalDateTime end() {
        return end;
    }

    public StepType stepType() {
        return stepType;
    }

    public String description() {
        return description;
    }
}
