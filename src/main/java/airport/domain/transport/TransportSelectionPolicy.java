package airport.domain.transport;

import airport.domain.user.TravelPreference;
import java.util.Comparator;
import java.util.List;

public final class TransportSelectionPolicy {

    public TransportOption selectBest(
            final List<TransportOption> options,
            final TravelPreference preference
    ) {
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


}
