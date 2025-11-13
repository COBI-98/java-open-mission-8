package airport.domain.itinerary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItineraryStepTest {

    @DisplayName("constructor(): 시작~종료 시간으로 ItineraryStep을 생성하고 Duration을 계산한다.")
    @Test
    void constructor_itineraryStep_success() {
        // given
        LocalDateTime start = LocalDateTime.of(2025, 12, 24, 18, 0);
        LocalDateTime end = LocalDateTime.of(2025, 12, 24, 18, 30);

        // when
        ItineraryStep step = new ItineraryStep(start, end, StepType.TASK, "유심 구매");

        // then
        assertThat(step.duration()).isEqualTo(Duration.ofMinutes(30));
    }

    @DisplayName("constructor(): 종료 시간이 시작보다 빠르면 예외가 발생한다.")
    @Test
    void constructor_itineraryStep_fail() {
        // given
        LocalDateTime start = LocalDateTime.of(2025, 12, 24, 18, 30);
        LocalDateTime end = LocalDateTime.of(2025, 12, 24, 18, 0);

        // when && then
        assertThatThrownBy(() -> new ItineraryStep(
                start, end, StepType.TASK, "유심 구매"
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 타임라인 단계의 종료 시간이 시작 시간보다 빠를 수 없습니다.");
    }
}