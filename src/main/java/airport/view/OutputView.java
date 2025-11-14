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


    public void printGreeting() {
        System.out.println(GREETING_LINE_1);
        System.out.println(GREETING_LINE_2);
        System.out.println();
    }

}
