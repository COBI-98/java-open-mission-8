package airport.domain.user;

public enum Language {
    KOREAN("한국어"),
    ENGLISH("영어"),
    JAPANESE("일본어"),
    CHINESE("중국어");

    private final String description;

    Language(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
