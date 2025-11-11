package airport.domain.user;

import java.util.Arrays;

public enum TravelPreference {

    TIME("시간 우선"),
    COMFORT("편안함 우선"),
    COST("비용 우선"),
    EXPERIENCE("경험 우선");

    private final String description;

    TravelPreference(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}