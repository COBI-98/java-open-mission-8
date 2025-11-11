// src/main/java/airportplanner/domain/request/LanguageProfile.java
package airport.domain.user;

import java.util.Objects;

public final class LanguageProfile {

    private final Language language;
    private final KoreanSignLevel koreanSignLevel;

   private LanguageProfile(
            final Language language,
            final KoreanSignLevel koreanSignLevel
    ) {
        this.language = Objects.requireNonNull(language);
        this.koreanSignLevel = Objects.requireNonNull(koreanSignLevel);
    }

    public static LanguageProfile of(
            final Language language,
            final KoreanSignLevel koreanSignLevel
    ) {
        return new LanguageProfile(language, koreanSignLevel);
    }

    public Language language() {
        return language;
    }

    public KoreanSignLevel koreanSignLevel() {
        return koreanSignLevel;
    }
}
