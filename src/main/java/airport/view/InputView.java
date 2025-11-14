package airport.view;

import airport.domain.user.ArrivalDateTime;
import airport.domain.user.CompanionType;
import airport.domain.user.DestinationArea;
import airport.domain.user.LanguageProfile;
import airport.domain.user.LuggageCount;
import airport.domain.user.RequiredAirportTask;
import airport.domain.user.Terminal;
import airport.domain.user.TravelPreference;
import airport.util.ConsoleLineParser;
import camp.nextstep.edu.missionutils.Console;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class InputView {

    private static final String PROMPT_ARRIVAL_DATETIME =
            "도착 일시를 입력해 주세요. (예: 2025-12-24 19:30)";
    private static final String PROMPT_TERMINAL =
            "도착 터미널을 입력해 주세요. (T1 또는 T2)";
    private static final String PROMPT_DESTINATION_AREA =
            "목적지 근처 구역을 입력해 주세요. (예: 명동/시청, 홍대/마포, 강남/잠실, 인천/부천)";
    private static final String PROMPT_LUGGAGE_COUNT =
            "캐리어 개수를 입력해 주세요. (0~3 사이 정수)";
    private static final String PROMPT_COMPANION_TYPE =
            "동행자 유형을 입력해 주세요. (ALONE, FAMILY, WITH_CHILD)";
    private static final String PROMPT_TRAVEL_PREFERENCE =
            "이동 우선순위를 입력해 주세요. (TIME, COST, COMFORT, EXPERIENCE 중 택1)";
    private static final String PROMPT_LANGUAGE_PROFILE =
            "선호 언어와 한국어 이해 수준을 입력해 주세요. (예: ENGLISH,BASIC)";



}
