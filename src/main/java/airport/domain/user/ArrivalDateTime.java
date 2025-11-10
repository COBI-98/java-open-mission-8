package airport.domain.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ArrivalDateTime {
    private static final String PATTERN = "yyyy-MM-dd HH:mm";

    private final LocalDateTime value;

    private ArrivalDateTime(LocalDateTime value) {
        this.value = value;
    }

    public static ArrivalDateTime from(String text) {
        validateNonBlank(text);

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
            LocalDateTime parsed = LocalDateTime.parse(text, formatter);
            return new ArrivalDateTime(parsed);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 날짜/시간 형식이 올바르지 않습니다.");
        }
    }

    private static void validateNonBlank(final String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 날짜/시간은 비어있을 수 없습니다.");
        }
    }

    public LocalDateTime value() {
        return value;
    }

    public LocalDate toDate() {
        return value.toLocalDate();
    }

    public String formattedToDate() {
        return value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
