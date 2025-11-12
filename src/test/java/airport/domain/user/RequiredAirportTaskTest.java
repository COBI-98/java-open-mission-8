package airport.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RequiredAirportTaskTest {

    @DisplayName("constructor(): 대소문자 무관한 이름으로 RequiredAirportTask를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"USIM", "exchange", "  MEAL  "})
    void constructor_requiredAirportTask_success(String input) {
        // when
        RequiredAirportTask task = RequiredAirportTask.from(input);

        // then
        assertThat(task).isNotNull();
    }

    @DisplayName("validateNonBlank(): 비어있거나 공백만 있는 문자열이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void validateNonBlank_requiredAirportTask_fail(String input) {
        // when && then
        assertThatThrownBy(() -> RequiredAirportTask.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 공항 작업 이름은 비어있을 수 없습니다.");
    }

    @DisplayName("from(): 공항에서 지원하지 않는 서비스시 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"DAISO", "STORES"})
    void from_requiredAirportTask_fail(String input) {
        assertThatThrownBy(() -> RequiredAirportTask.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 지원하지 않는 공항 작업입니다.");
    }
}