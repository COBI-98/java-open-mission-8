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

    @DisplayName("congestionFactorOf(): 주말/공휴일은 평일보다 혼잡도 계수가 크다.")
    @Test
    void congestionFactorOf_travelCalendar_fail() {
        // given
        LocalDate holiday = LocalDate.of(2025, 12, 25);
        TravelCalendar calendar =
                new FixedHolidayTravelCalendar(Set.of(holiday));

        // when
        double weekday = calendar.congestionFactorOf(LocalDate.of(2025, 12, 24));
        double weekend = calendar.congestionFactorOf(LocalDate.of(2025, 12, 27));
        double holi = calendar.congestionFactorOf(holiday);

        // then
        assertThat(weekend).isGreaterThanOrEqualTo(weekday);
        assertThat(holi).isGreaterThanOrEqualTo(weekend);
    }
}