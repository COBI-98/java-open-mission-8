package airport.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TravelPreferenceTest {

    @DisplayName("constructor(): 이동 우선순위 enum을 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"TIME", "comfort"})
    void constructor_travelPreference_success(String input) {
        TravelPreference preference = TravelPreference.from(input);

        assertThat(preference.name()).isEqualToIgnoringCase(input);
    }

    @DisplayName("from(): 잘못된 우선순위면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"FAST", "CHEAP"})
    void from_travelPreference_fail(String input) {
        assertThatThrownBy(() -> TravelPreference.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 지원하지 않는 이동 우선순위입니다");
    }
}