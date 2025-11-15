package airport;

import airport.controller.AirportPlannerController;
import airport.domain.calendar.FixedHolidayTravelCalendar;
import airport.domain.calendar.TravelCalendar;
import airport.domain.planner.ItineraryPlanner;
import airport.domain.transport.TransportSelectionPolicy;
import airport.view.InputView;
import airport.view.OutputView;
import java.time.LocalDate;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        TravelCalendar travelCalendar = new FixedHolidayTravelCalendar(
                Set.of(LocalDate.of(2025, 12, 25))
        );

        TransportSelectionPolicy policy = new TransportSelectionPolicy();
        ItineraryPlanner planner = new ItineraryPlanner(travelCalendar, policy);

        AirportPlannerController controller = new AirportPlannerController(new InputView(), new OutputView(), planner);
        controller.start();
    }
}
