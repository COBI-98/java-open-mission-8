package airport.domain.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TerminalTest {

    @DisplayName("constructor(): T1, T2 코드로 터미널을 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"T1", "t2"})
    void constructor_terminal_success(String input) {
        Terminal terminal = Terminal.from(input);

        assertThat(terminal.getCode()).isEqualToIgnoringCase(input);
    }

    @DisplayName("from(): 지원하지 않는 터미널 코드면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"T3", "ABC"})
    void from_terminal_fail(String input) {
        assertThatThrownBy(() -> Terminal.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 지원하지 않는 터미널입니다");
    }
}