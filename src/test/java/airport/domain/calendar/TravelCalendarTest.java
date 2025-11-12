package airport.domain.calendar;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TravelCalendarTest {

    @DisplayName("constructor(): 주말, 평일, 공휴일에 따라 DayType을 판별한다.")
    @Test
    void constructor_travelCalendar_success() {
        // given
        LocalDate holiday = LocalDate.of(2025, 12, 25);
        TravelCalendar calendar =
                new FixedHolidayTravelCalendar(Set.of(holiday));

        // when && then
        assertThat(calendar.dayTypeOf(LocalDate.of(2025, 12, 24)))
                .isEqualTo(DayType.WEEKDAY);
        assertThat(calendar.dayTypeOf(LocalDate.of(2025, 12, 27)))
                .isEqualTo(DayType.WEEKEND);
        assertThat(calendar.dayTypeOf(holiday))
                .isEqualTo(DayType.HOLIDAY);
    }

}