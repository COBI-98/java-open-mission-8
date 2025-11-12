// src/main/java/airportplanner/domain/request/LanguageProfile.java
package airport.domain.user;

import java.util.Objects;

public final class LanguageProfile {

    private static final String ERROR_BLANK_INPUT =
            "[ERROR] 언어 정보는 비어있을 수 없습니다.";
    private static final String ERROR_INVALID_FORMAT =
            "[ERROR] 언어 정보 형식이 올바르지 않습니다. (예: ENGLISH,BASIC)";
    private static final String ERROR_INVALID_LANGUAGE =
            "[ERROR] 지원하지 않는 언어입니다.";
    private static final String ERROR_INVALID_LEVEL =
            "[ERROR] 지원하지 않는 한국어 이해 수준입니다.";


    private static final String DELIMITER = ",";
    private static final int LANGUAGE_INDEX = 0;
    private static final int LEVEL_INDEX = 1;
    private static final int EXPECTED_PARTS_LENGTH = 2;

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

    public static LanguageProfile from(final String input) {
        String trimmedInput = validateNonBlank(input);
        String[] parts = splitInput(trimmedInput);

        Language Language = parseLanguage(parts[LANGUAGE_INDEX]);
        KoreanSignLevel readingLevel = parseLevel(parts[LEVEL_INDEX]);

        return new LanguageProfile(Language, readingLevel);
    }

    private static String validateNonBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ERROR_BLANK_INPUT);
        }
        return input.strip();
    }

    private static String[] splitInput(final String input) {
        String[] parts = input.split(DELIMITER);
        if (parts.length != EXPECTED_PARTS_LENGTH) {
            throw new IllegalArgumentException(ERROR_INVALID_FORMAT);
        }
        return parts;
    }

    private static Language parseLanguage(final String rawLanguage) {
        String trimmed = rawLanguage.strip().toUpperCase();
        for (Language type : Language.values()) {
            if (type.name().equals(trimmed)) {
                return type;
            }
        }
        throw new IllegalArgumentException(ERROR_INVALID_LANGUAGE);
    }

    private static KoreanSignLevel parseLevel(final String rawLevel) {
        String trimmed = rawLevel.strip().toUpperCase();
        for (KoreanSignLevel level : KoreanSignLevel.values()) {
            if (level.name().equals(trimmed)) {
                return level;
            }
        }
        throw new IllegalArgumentException(ERROR_INVALID_LEVEL);
    }

    public Language language() {
        return language;
    }

    public KoreanSignLevel koreanSignLevel() {
        return koreanSignLevel;
    }
}
