package airport.domain.transport;

import airport.domain.calendar.DayType;
import airport.domain.user.PlanningRequest;
import airport.domain.user.TravelPreference;
import java.util.Comparator;
import java.util.List;

public final class TransportSelectionPolicy {

    private static final String ERROR_EMPTY_OPTIONS =
            "[ERROR] 교통수단 후보가 비어있습니다.";

    public TransportOption selectPriority(final PlanningRequest request, final DayType dayType) {

        List<TransportOption> candidates = List.of(
                new TransportOption(TransportType.AIRPORT_BUS, 70, 17000, 0),
                new TransportOption(TransportType.AREX_SUBWAY, 60, 10000, 0),
                new TransportOption(TransportType.SUBWAY, 80, 5000, 2),
                new TransportOption(TransportType.TAXI, 65, 60000, 0)
        );

        TravelPreference preference = request.travelPreference();

        if (preference == TravelPreference.TIME) {
            return candidates.stream()
                    .min((a, b) -> Integer.compare(a.getEtaMinutes(), b.getEtaMinutes()))
                    .orElseThrow();
        }

        if (preference == TravelPreference.COST) {
            return candidates.stream()
                    .min((a, b) -> Integer.compare(a.getCost(), b.getCost()))
                    .orElseThrow();
        }

        if (preference == TravelPreference.COMFORT) {
            return candidates.stream()
                    .filter(option -> option.getTransfers() == 0)
                    .findFirst()
                    .orElse(candidates.get(0));
        }

        return candidates.stream()
                .filter(option -> option.getType() == TransportType.AIRPORT_BUS || option.getType() == TransportType.SUBWAY)
                .findFirst()
                .orElse(candidates.get(0));
    }


    public TransportOption selectBest(
            final List<TransportOption> options,
            final TravelPreference preference
    ) {
        validateOptions(options);

        if (preference == TravelPreference.TIME) {
            return options.stream()
                    .min(Comparator.comparingInt(TransportOption::getEtaMinutes))
                    .orElseThrow();
        }
        if (preference == TravelPreference.COMFORT) {
            return options.stream()
                    .min(Comparator.comparingInt(TransportOption::getTransfers))
                    .orElseThrow();
        }

        return options.get(0);
    }

    private void validateOptions(final List<TransportOption> options) {
        if (options == null || options.isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY_OPTIONS);
        }
    }

}
