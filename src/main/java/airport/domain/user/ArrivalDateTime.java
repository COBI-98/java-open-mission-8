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


    public LocalDateTime value() {
        return value;
    }
}
