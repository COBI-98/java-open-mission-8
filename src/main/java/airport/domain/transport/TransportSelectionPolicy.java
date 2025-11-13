package airport.domain.transport;

import airport.domain.user.TravelPreference;
import java.util.Comparator;
import java.util.List;

public final class TransportSelectionPolicy {

    private static final String ERROR_EMPTY_OPTIONS =
            "[ERROR] 교통수단 후보가 비어있습니다.";

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
