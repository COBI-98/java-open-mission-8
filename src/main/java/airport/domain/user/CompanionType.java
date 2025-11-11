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

    public static CompanionType from(final String name) {
        return Arrays.stream(values())
                .filter(type -> type.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "[ERROR] 지원하지 않는 동행자 유형입니다"
                ));
    }

    public String getDescription() {
        return description;
    }
}
