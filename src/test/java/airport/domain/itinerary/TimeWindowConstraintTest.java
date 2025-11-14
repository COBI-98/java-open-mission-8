package airport.domain.itinerary;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TimeWindowConstraintTest {

    @DisplayName("constructor(): 최대 허용 시간을 기준으로 Duration 만족 여부를 판단한다.")
    @Test
    void constructor_TimeWindowConstraint_success() {
        // given
        Duration maxDuration = Duration.ofHours(3);
        TimeWindowConstraint constraint = new TimeWindowConstraint(maxDuration);

        // when
        boolean within = constraint.isSatisfiedBy(Duration.ofHours(2));
        boolean over = constraint.isSatisfiedBy(Duration.ofHours(4));

        // then
        assertThat(within).isTrue();
        assertThat(over).isFalse();
    }
}