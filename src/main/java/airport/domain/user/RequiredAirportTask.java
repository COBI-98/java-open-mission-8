package airport.domain.user;

import airport.domain.calendar.DayType;
import java.util.Arrays;

public enum RequiredAirportTask {

    USIM(15, "USIM/eSIM 구매"),
    EXCHANGE(10, "환전"),
    TRANSPORT_CARD(10, "교통카드 충전"),
    MEAL(20, "간단한 식사"),
    SHOWER(25, "샤워");

    private final int baseMinutes;
    private final String description;

    RequiredAirportTask(final int baseMinutes, final String description) {
        this.baseMinutes = baseMinutes;
        this.description = description;
    }

    public static RequiredAirportTask from(final String name) {
        validateNonBlank(name);

        return Arrays.stream(values())
                .filter(task -> task.name().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "[ERROR] 지원하지 않는 공항 작업입니다."
                ));
    }

    private static void validateNonBlank(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "[ERROR] 공항 작업 이름은 비어있을 수 없습니다."
            );
        }
    }

    public String getDescription() {
        return description;
    }

    public int durationMinutes(final DayType dayType) {
        return (int) Math.round(baseMinutes * dayType.taskDelayFactor());
    }
}
