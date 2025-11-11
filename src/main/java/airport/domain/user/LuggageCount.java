package airport.domain.user;

import java.util.Objects;

public final class LuggageCount {

    private static final int MIN_LUGGAGE_COUNT = 0;
    private static final int MAX_LUGGAGE_COUNT = 3;

    private final int value;

    private LuggageCount(final int value) {
        validateRange(value);
        this.value = value;
    }

    public static LuggageCount of(final int value) {
        return new LuggageCount(value);
    }

    public static LuggageCount parse(final String input) {
        String trimmedCount = validateNonBlank(input);
        validateDigits(trimmedCount);
        return of(parseInt(trimmedCount));
    }



    private static void validateRange(final int value) {
        if (value < MIN_LUGGAGE_COUNT || value > MAX_LUGGAGE_COUNT) {
            throw new IllegalArgumentException(
                    "[ERROR] 캐리어 개수는 0~3 사이의 정수만 가능합니다."
            );
        }
    }

    private static String validateNonBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 캐리어 개수는 비어있거나 공백일 수 없습니다.");
        }
        return input.strip();
    }

    private static void validateDigits(final String input) {
        if (!input.chars()
                .allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 캐리어 개수는 숫자만 입력할 수 있습니다.");
        }
    }

    private static int parseInt(String amount) {
        return Integer.parseInt(amount);
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LuggageCount)) return false;
        LuggageCount that = (LuggageCount) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
