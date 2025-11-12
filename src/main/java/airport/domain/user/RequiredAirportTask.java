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

    public String getDescription() {
        return description;
    }
}
