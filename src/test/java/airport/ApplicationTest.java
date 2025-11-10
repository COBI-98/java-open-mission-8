package airport;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {

    @Test
    void 기능_테스트() {
        assertSimpleTest(
                () -> {
                    run("2025-12-24 18:30",
                            "T1",
                            "명동/시청",
                            "1",
                            "ALONE",
                            "TIME",
                            "ENGLISH,BASIC",
                            "USIM,EXCHANGE,TRANSPORT_CARD"
                    );
                    assertThat(output()).contains(
                            "안녕하세요! 인천공항 3시간 생존 플래너입니다.",
                            "<여행자 입력 요약>",
                            "<추천 타임라인>",
                            "<요약 정보>",
                            "- 도착 시각: 2025-12-24 18:30",
                            "- 도착 터미널: T1",
                            "- 목적지 구역: 명동/시청",
                            "- 짐 개수: 1",
                            "- 동행자 유형: ALONE",
                            "- 이동 우선순위: TIME",
                            "- 언어/한국어 이해: ENGLISH / BASIC",
                            "3시간 이내 도착",
                            "우선순위 TIME 기준으로 가장 빠른 루트를 선택했습니다."
                    );
                }
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException(
                    "2025/12/24 18:30",
                    "T1",
                    "명동/시청",
                    "1",
                    "ALONE",
                    "TIME",
                    "ENGLISH,BASIC",
                    "USIM"
            );

            assertThat(output())
                    .contains("날짜/시간 형식이 올바르지 않습니다");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
