package airport.domain.itinerary;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public final class ItineraryPlan {

    private static final String ERROR_EMPTY_STEPS =
            "[ERROR] 최소 한 개 이상의 타임라인 단계를 가져야 합니다.";

    private final List<ItineraryStep> steps;

    public ItineraryPlan(final List<ItineraryStep> steps) {
        validateSteps(steps);
        this.steps = List.copyOf(steps);
    }

    private void validateSteps(final List<ItineraryStep> steps) {
        if (steps == null || steps.isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY_STEPS);
        }
    }

    public List<ItineraryStep> steps() {
        return Collections.unmodifiableList(steps);
    }

    public Duration totalDuration() {
        ItineraryStep first = steps.get(0);
        ItineraryStep last = steps.get(steps.size() - 1);
        return Duration.between(first.start(), last.end());
    }

}
