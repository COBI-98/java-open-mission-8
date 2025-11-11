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
    private static void validateRange(final int value) {
        if (value < MIN_LUGGAGE_COUNT || value > MAX_LUGGAGE_COUNT) {
            throw new IllegalArgumentException(
                    "[ERROR] 캐리어 개수는 0~3 사이의 정수만 가능합니다."
            );
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
