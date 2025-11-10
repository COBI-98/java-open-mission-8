package airport.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DestinationAreaTest {

    @DisplayName("constructor(): 사전 정의된 한글 라벨로 구역을 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"명동/시청", "홍대/마포"})
    void constructor_destination_success(String label) {
        DestinationArea area = DestinationArea.fromLabel(label);

        assertThat(area.getLabel()).isEqualTo(label);
    }

    @DisplayName("fromLabel(): 정의되지 않은 라벨이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"부산", "강릉"})
    void fromLabel_destination_fail(String label) {
        assertThatThrownBy(() -> DestinationArea.fromLabel(label))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 목적지 구역이 올바르지 않습니다");
    }
}