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

    @DisplayName("validateNonBlank(): 캐리어 개수에 빈 문자열/공백이 들어가면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void validateNonBlank_luggageCount_fail(final String input) {
        String message = "[ERROR] 캐리어 개수는 비어있거나 공백일 수 없습니다.";

        assertThatThrownBy(() -> LuggageCount.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @DisplayName("validateDigits(): 캐리어 개수에 문자가 들어가면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1a", "two", ";"})
    void validateDigits_luggageCount_fail(final String input) {
        String message = "[ERROR] 캐리어 개수는 숫자만 입력할 수 있습니다.";

        assertThatThrownBy(() -> LuggageCount.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
}