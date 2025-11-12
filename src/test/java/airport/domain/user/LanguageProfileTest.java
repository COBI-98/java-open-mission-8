package airport.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LanguageProfileTest {

    @DisplayName("constructor(): 언어와 한국어 이해 수준으로 LanguageProfile을 생성한다.")
    @Test
    void constructor_languageProfile_success() {
        // when
        LanguageProfile profile = LanguageProfile.of(
                Language.ENGLISH,
                KoreanSignLevel.BASIC
        );

        // then
        assertThat(profile.language()).isEqualTo(Language.ENGLISH);
        assertThat(profile.koreanSignLevel()).isEqualTo(KoreanSignLevel.BASIC);
    }

    @DisplayName("from(): 'ENGLISH,BASIC' 형식의 문자열로 LanguageProfile을 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"ENGLISH,BASIC", "english,basic", " ENGLISH , BASIC "})
    void from_languageProfile_success(String input) {
        // when
        LanguageProfile profile = LanguageProfile.from(input);

        // then
        assertThat(profile.language()).isEqualTo(Language.ENGLISH);
        assertThat(profile.koreanSignLevel()).isEqualTo(KoreanSignLevel.BASIC);
    }

    @DisplayName("validateNonBlank(): 입력이 비어있으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void validateNonBlank_languageProfile_fail(String input) {
        // when && then
        assertThatThrownBy(() -> LanguageProfile.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 언어 정보는 비어있을 수 없습니다.");
    }

    @DisplayName("splitInput(): 쉼표로 나눈 값이 2개가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"ENGLISH", "ENGLISH,BASIC,EXTRA"})
    void splitInput_languageProfile_fail(String input) {
        // when && then
        assertThatThrownBy(() -> LanguageProfile.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 언어 정보 형식이 올바르지 않습니다. (예: ENGLISH,BASIC)");
    }

    @DisplayName("from(): 지원하지 않는 언어라면 예외가 발생한다.")
    @Test
    void parseLanguage_languageProfile_fail() {
        // when && then
        assertThatThrownBy(() -> LanguageProfile.from("SPANISH,BASIC"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 지원하지 않는 언어입니다.");
    }

    @DisplayName("from(): 지원하지 않는 한국어 수준이라면 예외가 발생한다.")
    @Test
    void parseLevel_LanguageProfile_fail() {
        // when && then
        assertThatThrownBy(() -> LanguageProfile.from("ENGLISH,ADVANCED"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 지원하지 않는 한국어 이해 수준입니다.");
    }

}