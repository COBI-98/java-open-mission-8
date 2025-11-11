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

    public static TravelPreference from(final String name) {
        return Arrays.stream(values())
                .filter(pref -> pref.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "[ERROR] 지원하지 않는 이동 우선순위입니다"
                ));
    }

    public String getDescription() {
        return description;
    }
}