package airport.domain.user;

import java.util.Arrays;

public enum CompanionType {

    ALONE("혼자"),
    FAMILY("가족"),
    WITH_CHILD("아이 동반");

    private final String description;

    CompanionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
