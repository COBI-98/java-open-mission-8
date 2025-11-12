package airport.domain.user;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public final class PlanningRequest {

    private static final String ERROR_EMPTY_TASKS = "[ERROR] 최소 한 개 이상의 공항 작업을 선택해야 합니다.";

    private final ArrivalDateTime arrivalDateTime;
    private final Terminal terminal;
    private final DestinationArea destinationArea;
    private final LuggageCount luggageCount;
    private final CompanionType companionType;
    private final TravelPreference travelPreference;
    private final LanguageProfile languageProfile;
    private final Set<RequiredAirportTask> requiredTasks;

    private PlanningRequest(
            final ArrivalDateTime arrivalDateTime,
            final Terminal terminal,
            final DestinationArea destinationArea,
            final LuggageCount luggageCount,
            final CompanionType companionType,
            final TravelPreference travelPreference,
            final LanguageProfile languageProfile,
            final Set<RequiredAirportTask> requiredTasks
    ) {
        this.arrivalDateTime = Objects.requireNonNull(arrivalDateTime);
        this.terminal = Objects.requireNonNull(terminal);
        this.destinationArea = Objects.requireNonNull(destinationArea);
        this.luggageCount = Objects.requireNonNull(luggageCount);
        this.companionType = Objects.requireNonNull(companionType);
        this.travelPreference = Objects.requireNonNull(travelPreference);
        this.languageProfile = Objects.requireNonNull(languageProfile);
        this.requiredTasks = validateTasks(requiredTasks);
    }

    public static PlanningRequest of(
            final ArrivalDateTime arrivalDateTime,
            final Terminal terminal,
            final DestinationArea destinationArea,
            final LuggageCount luggageCount,
            final CompanionType companionType,
            final TravelPreference travelPreference,
            final LanguageProfile languageProfile,
            final Set<RequiredAirportTask> requiredTasks
    ) {
        return new PlanningRequest(
                arrivalDateTime,
                terminal,
                destinationArea,
                luggageCount,
                companionType,
                travelPreference,
                languageProfile,
                requiredTasks
        );
    }


    public ArrivalDateTime arrivalDateTime() {
        return arrivalDateTime;
    }

    public Terminal terminal() {
        return terminal;
    }

    public DestinationArea destinationArea() {
        return destinationArea;
    }

    public LuggageCount luggageCount() {
        return luggageCount;
    }

    public CompanionType companionType() {
        return companionType;
    }

    public TravelPreference travelPreference() {
        return travelPreference;
    }

    public LanguageProfile languageProfile() {
        return languageProfile;
    }

    public Set<RequiredAirportTask> requiredTasks() {
        return requiredTasks;
    }
}
