package airport.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LuggageCountTest {

    @DisplayName("constructor(): 0~3 사이의 값으로 LuggageCount를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 3})
    void constructor_luggageCount_success(int input) {
        // when
        LuggageCount luggageCount = LuggageCount.of(input);

        // then
        assertThat(luggageCount.value()).isEqualTo(input);
    }

    @DisplayName("validateRange(): 0~3 사이가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 4})
    void validateRange_luggageCount_fail(final int input) {
        String message = "[ERROR] 캐리어 개수는 0~3 사이의 정수만 가능합니다.";

        assertThatThrownBy(() -> LuggageCount.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
}