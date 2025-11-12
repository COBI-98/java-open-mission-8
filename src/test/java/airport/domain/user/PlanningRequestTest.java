package airport.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.EnumSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlanningRequestTest {

    @DisplayName("constructor(): 모든 필수 값으로 PlanningRequest를 생성한다.")
    @Test
    void constructor_planningRequest_success() {
        // given
        Set<RequiredAirportTask> tasks = EnumSet.of(RequiredAirportTask.USIM, RequiredAirportTask.EXCHANGE);

        // when
        PlanningRequest request = PlanningRequest.of(
                ArrivalDateTime.from("2025-12-24 18:30"),
                Terminal.from("T1"),
                DestinationArea.fromLabel("명동/시청"),
                LuggageCount.of(1),
                CompanionType.from("ALONE"),
                TravelPreference.from("TIME"),
                LanguageProfile.from("ENGLISH,BASIC"),
                tasks
        );

        // then
        assertThat(request.requiredTasks()).hasSize(2);
        assertThat(request.travelPreference()).isEqualTo(TravelPreference.TIME);
    }
}