package airport.domain.transport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import airport.domain.user.TravelPreference;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TransportSelectionPolicyTest {

    @DisplayName("constructor(): TIME 우선순위일 때 예상 소요 시간이 더 짧은 교통수단을 선택한다.")
    @Test
    void constructor_transportSelectionPolicy_success() {
        // given
        TransportOption bus = new TransportOption(TransportType.AIRPORT_BUS, 80, 0, 15000);
        TransportOption arex = new TransportOption(TransportType.AREX_SUBWAY, 60, 1, 9000);

        TransportSelectionPolicy policy = new TransportSelectionPolicy();

        // when
        TransportOption best = policy.selectBest(
                List.of(bus, arex),
                TravelPreference.TIME
        );

        // then
        assertThat(best.getType()).isEqualTo(TransportType.AREX_SUBWAY);
    }

    @DisplayName("selectBest(): 후보가 비어있으면 예외가 발생한다.")
    @Test
    void selectBest_transportSelectionPolicy_fail() {
        // given
        TransportSelectionPolicy policy = new TransportSelectionPolicy();

        // when && then
        assertThatThrownBy(() -> policy.selectBest(
                List.of(),
                TravelPreference.TIME
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 교통수단 후보가 비어있습니다.");
    }
}