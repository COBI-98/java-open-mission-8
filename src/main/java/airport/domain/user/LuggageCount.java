package airport.domain.user;

import java.util.Objects;

public final class LuggageCount {


    private final int value;

    private LuggageCount(final int value) {
        this.value = value;
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
