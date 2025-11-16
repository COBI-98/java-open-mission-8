package airport.controller;

import airport.domain.calendar.FixedHolidayTravelCalendar;
import airport.domain.calendar.TravelCalendar;
import airport.domain.itinerary.ItineraryPlan;
import airport.domain.planner.ItineraryPlanner;
import airport.domain.user.ArrivalDateTime;
import airport.domain.user.CompanionType;
import airport.domain.user.DestinationArea;
import airport.domain.user.LanguageProfile;
import airport.domain.user.LuggageCount;
import airport.domain.user.PlanningRequest;
import airport.domain.transport.TransportSelectionPolicy;
import airport.domain.user.RequiredAirportTask;
import airport.domain.user.Terminal;
import airport.domain.user.TravelPreference;
import airport.view.InputView;
import airport.view.OutputView;
import java.time.LocalDate;
import java.util.Set;

public class AirportPlannerController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ItineraryPlanner itineraryPlanner;

    public AirportPlannerController(
            final InputView inputView,
            final OutputView outputView,
            final ItineraryPlanner itineraryPlanner
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.itineraryPlanner = itineraryPlanner;
    }

    public void start() {
        outputView.printGreeting();

        try {
            PlanningRequest request = createRequestFromUserInput();
            outputView.printInputSummary(request);

            ItineraryPlan plan = itineraryPlanner.createPlan(request);

            outputView.printPlan(plan);
            outputView.printSummary(plan, request);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private PlanningRequest createRequestFromUserInput() {
        ArrivalDateTime arrivalDateTime = readArrivalDateTimeWithRetry();
        Terminal terminal = readTerminalWithRetry();
        DestinationArea destinationArea = readDestinationAreaWithRetry();
        LuggageCount luggageCount = readLuggageCountWithRetry();
        CompanionType companionType = readCompanionTypeWithRetry();
        TravelPreference travelPreference = readTravelPreferenceWithRetry();
        LanguageProfile languageProfile = readLanguageProfileWithRetry();
        Set<RequiredAirportTask> tasks = readRequiredTasksWithRetry();

        return PlanningRequest.of(
                arrivalDateTime,
                terminal,
                destinationArea,
                luggageCount,
                companionType,
                travelPreference,
                languageProfile,
                tasks
        );
    }

    private ArrivalDateTime readArrivalDateTimeWithRetry() {
        while (true) {
            try {
                return inputView.readArrivalDateTime();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Terminal readTerminalWithRetry() {
        while (true) {
            try {
                return inputView.readTerminal();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private DestinationArea readDestinationAreaWithRetry() {
        while (true) {
            try {
                return inputView.readDestinationArea();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private LuggageCount readLuggageCountWithRetry() {
        while (true) {
            try {
                return inputView.readLuggageCount();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private CompanionType readCompanionTypeWithRetry() {
        while (true) {
            try {
                return inputView.readCompanionType();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private TravelPreference readTravelPreferenceWithRetry() {
        while (true) {
            try {
                return inputView.readTravelPreference();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private LanguageProfile readLanguageProfileWithRetry() {
        while (true) {
            try {
                return inputView.readLanguageProfile();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Set<RequiredAirportTask> readRequiredTasksWithRetry() {
        while (true) {
            try {
                return inputView.readRequiredTasks();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
