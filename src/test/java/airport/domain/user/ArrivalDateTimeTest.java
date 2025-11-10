package airport.domain.user;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ArrivalDateTimeTest {

    @DisplayName("constructor(): 올바른 형식의 날짜/시간 문자열로 ArrivalDateTime을 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"2025-12-24 18:30", "2025-01-01 00:00"})
    void constructor_arrivalDateTime_success(String input) {
        // when
        ArrivalDateTime arrivalDateTime = ArrivalDateTime.from(input);

        // then
        assertThat(arrivalDateTime.toDate()).isInstanceOf(LocalDate.class);
    }

    @DisplayName("from(): 비어있거나 공백만 있는 문자열이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void blank_arrivalDateTime_fail(String input) {
        assertThatThrownBy(() -> ArrivalDateTime.from(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 날짜/시간은 비어있을 수 없습니다.");
    }

    @DisplayName("pattern(): 패턴과 맞지 않는 형식이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"2025/12/24 18:30", "2025-13-01 10:00"})
    void pattern_arrivalDateTime_fail(String input) {
        assertThatThrownBy(() -> ArrivalDateTime.from(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 날짜/시간 형식이 올바르지 않습니다.");
    }
}