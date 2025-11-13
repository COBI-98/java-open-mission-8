package airport.domain.itinerary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItineraryPlanTest {

    @DisplayName("constructor(): 여러 Step으로 ItineraryPlan을 생성하고 총 소요 시간을 계산한다.")
    @Test
    void constructor_itineraryPlan_success() {
        // given
        ItineraryStep step1 = new ItineraryStep(
                LocalDateTime.of(2025, 12, 24, 18, 0),
                LocalDateTime.of(2025, 12, 24, 18, 15),
                StepType.TASK,
                "입국 심사"
        );
        ItineraryStep step2 = new ItineraryStep(
                LocalDateTime.of(2025, 12, 24, 18, 20),
                LocalDateTime.of(2025, 12, 24, 19, 20),
                StepType.MOVE,
                "공항버스 이동"
        );

        // when
        ItineraryPlan plan = new ItineraryPlan(List.of(step1, step2));

        // then
        assertThat(plan.totalDuration()).isEqualTo(Duration.ofMinutes(80));
    }

    @DisplayName("constructor(): Step이 비어있으면 예외가 발생한다.")
    @Test
    void constructor_itineraryPlan_fail() {
        // when && then
        assertThatThrownBy(() -> new ItineraryPlan(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 최소 한 개 이상의 타임라인 단계를 가져야 합니다.");
    }
}