package airport.domain.itinerary;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public final class ItineraryPlan {


    private final List<ItineraryStep> steps;

    public ItineraryPlan(final List<ItineraryStep> steps) {
        this.steps = List.copyOf(steps);
    }


}
