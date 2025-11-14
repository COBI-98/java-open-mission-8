package airport.view;

import airport.domain.itinerary.ItineraryPlan;
import airport.domain.itinerary.ItineraryStep;
import airport.domain.user.PlanningRequest;
import java.time.Duration;

public class OutputView {

    private static final String GREETING_LINE_1 = "안녕하세요! 인천공항 3시간 생존 플래너입니다.";
    private static final String GREETING_LINE_2 = "도착 정보를 몇 가지 입력하면, 공항에서의 동선과 이동 루트를 제안해 드릴게요.";

    private static final String SECTION_INPUT_SUMMARY_TITLE = "<여행자 입력 요약>";
    private static final String SECTION_TIMELINE_TITLE = "<추천 타임라인>";
    private static final String SECTION_PLAN_SUMMARY_TITLE = "<요약 정보>";

    private static final String FORMAT_INPUT_ARRIVAL_DATETIME = "- 도착 시각: %s";
    private static final String FORMAT_INPUT_TERMINAL = "- 도착 터미널: %s";
    private static final String FORMAT_INPUT_DESTINATION_AREA = "- 목적지 구역: %s";
    private static final String FORMAT_INPUT_LUGGAGE_COUNT = "- 짐 개수: %d";
    private static final String FORMAT_INPUT_COMPANION_TYPE = "- 동행자 유형: %s";
    private static final String FORMAT_INPUT_TRAVEL_PREFERENCE = "- 이동 우선순위: %s";
    private static final String FORMAT_INPUT_LANGUAGE_PROFILE = "- 언어/한국어 이해: %s / %s";
    private static final String FORMAT_INPUT_REQUIRED_TASKS = "- 공항 내 작업: %s";

    public void printGreeting() {
        System.out.println(GREETING_LINE_1);
        System.out.println(GREETING_LINE_2);
        System.out.println();
    }

    public void printInputSummary(final PlanningRequest request) {
        System.out.println();
        System.out.println(SECTION_INPUT_SUMMARY_TITLE);
        System.out.printf((FORMAT_INPUT_ARRIVAL_DATETIME) + "%n",
                request.arrivalDateTime().formattedToDate());
        System.out.printf((FORMAT_INPUT_TERMINAL) + "%n",
                request.terminal().name());
        System.out.printf((FORMAT_INPUT_DESTINATION_AREA) + "%n",
                request.destinationArea().getLabel());
        System.out.printf((FORMAT_INPUT_LUGGAGE_COUNT) + "%n",
                request.luggageCount().value());
        System.out.printf((FORMAT_INPUT_COMPANION_TYPE) + "%n",
                request.companionType().name());
        System.out.printf((FORMAT_INPUT_TRAVEL_PREFERENCE) + "%n",
                request.travelPreference().name());
        System.out.printf((FORMAT_INPUT_LANGUAGE_PROFILE) + "%n",
                request.languageProfile().language().name(),
                request.languageProfile().koreanSignLevel().name());
        System.out.printf((FORMAT_INPUT_REQUIRED_TASKS) + "%n",
                request.requiredTasks());
        System.out.println();
    }

    public void printPlan(final ItineraryPlan plan) {
        System.out.println(SECTION_TIMELINE_TITLE);
        for (ItineraryStep step : plan.steps()) {
            System.out.printf((FORMAT_TIMELINE_STEP) + "%n",
                    step.start().toLocalTime(),
                    step.end().toLocalTime(),
                    step.stepType().name(),
                    step.description()
            );
        }
        System.out.println();
    }
}
