package airport.domain.user;

import java.util.Arrays;

public enum RequiredAirportTask {

    USIM("유심/eSIM 구매"),
    EXCHANGE("환전"),
    TRANSPORT_CARD("교통카드 충전/구매"),
    MEAL("식사"),
    SHOWER("샤워");

    private final String description;

    RequiredAirportTask(String description) {
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
}
