package airport.domain.itinerary;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public final class ItineraryStep {

    private static final String ERROR_INVALID_TIME_RANGE =
            "[ERROR] 타임라인 단계의 종료 시간이 시작 시간보다 빠를 수 없습니다.";

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
        validateTimeRange(start, end);
        this.start = Objects.requireNonNull(start);
        this.end = Objects.requireNonNull(end);
        this.stepType = Objects.requireNonNull(stepType);
        this.description = Objects.requireNonNull(description);
    }

    private void validateTimeRange(
            final LocalDateTime start,
            final LocalDateTime end
    ) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException(ERROR_INVALID_TIME_RANGE);
        }
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
