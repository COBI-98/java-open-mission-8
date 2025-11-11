package airport.domain.user;

public enum KoreanSignLevel {
    NONE("읽을 줄 모름"),
    BASIC("기본"),
    INTERMEDIATE("중급");

    private final String description;

    KoreanSignLevel(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
