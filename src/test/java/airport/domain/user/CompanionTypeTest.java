package airport.domain.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CompanionTypeTest {

    @DisplayName("constructor(): 동행자 enum을 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"ALONE", "family"})
    void constructor_companion_success(String input) {
        CompanionType type = CompanionType.from(input);

        assertThat(type.name()).isEqualToIgnoringCase(input);
    }

    @DisplayName("from(): 잘못된 동행자 타입이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"COUPLE", "TEAM"})
    void from_companion_fail(String input) {
        assertThatThrownBy(() -> CompanionType.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 지원하지 않는 동행자 유형입니다");
    }

}