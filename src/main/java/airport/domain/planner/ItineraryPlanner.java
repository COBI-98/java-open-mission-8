package airport.domain.planner;

import airport.domain.calendar.DayType;
import airport.domain.calendar.TravelCalendar;
import airport.domain.itinerary.ItineraryPlan;
import airport.domain.itinerary.ItineraryStep;
import airport.domain.itinerary.StepType;
import airport.domain.transport.TransportOption;
import airport.domain.transport.TransportSelectionPolicy;
import airport.domain.user.PlanningRequest;
import airport.domain.user.RequiredAirportTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItineraryPlanner {

    private static final int ARRIVAL_BASE_MINUTES = 30;
    private static final int CHECKIN_BUFFER_MINUTES = 15;

    private static final String ARRIVAL_DESCRIPTION = "입국 심사 · 수하물 수령";
    private static final String HOLIDAY_SUFFIX = " (연휴 혼잡 버퍼 반영)";

    private static final String TRANSPORT_DESCRIPTION_PREFIX = "인천공항에서 목적지까지 이동 (";
    private static final String TRANSPORT_DESCRIPTION_SUFFIX = ")";

    private static final String TRANSFER_DESCRIPTION_PREFIX = " / 환승: ";
    private static final String TRANSFER_DESCRIPTION_SUFFIX = "회";

    private static final String COST_DESCRIPTION_PREFIX = " / 대략 요금: ";
    private static final String COST_DESCRIPTION_SUFFIX = "원";

    private static final String CHECKIN_BUFFER_DESCRIPTION = "호텔 도보 이동 + 체크인 여유 시간";

    private final TravelCalendar travelCalendar;
    private final TransportSelectionPolicy transportSelectionPolicy;

    public ItineraryPlanner(
            final TravelCalendar travelCalendar,
            final TransportSelectionPolicy transportSelectionPolicy
    ) {
        this.travelCalendar = travelCalendar;
        this.transportSelectionPolicy = transportSelectionPolicy;
    }

    public ItineraryPlan createPlan(final PlanningRequest request) {
        LocalDateTime cursor = request.arrivalDateTime().value();
        DayType dayType = travelCalendar.resolveDayType(cursor.toLocalDate());

        List<ItineraryStep> steps = new ArrayList<>();

        cursor = addArrivalProcessStep(steps, cursor, dayType);

        cursor = addRequiredAirportTasks(steps, cursor, dayType, request.requiredTasks());

        TransportOption chosenTransport = transportSelectionPolicy.selectPriority(request, dayType);
        cursor = addTransportStep(steps, cursor, dayType, chosenTransport);

        cursor = addBufferStep(steps, cursor, dayType);

        return new ItineraryPlan(steps);
    }

    private LocalDateTime addArrivalProcessStep(
            final List<ItineraryStep> steps,
            final LocalDateTime start,
            final DayType dayType
    ) {
        int minutes = (int) Math.round(ARRIVAL_BASE_MINUTES * dayType.taskDelayFactor());
        LocalDateTime end = start.plusMinutes(minutes);

        String description = ARRIVAL_DESCRIPTION;
        if (dayType == DayType.HOLIDAY) {
            description += HOLIDAY_SUFFIX;
        }

        steps.add(new ItineraryStep(start, end, StepType.ARRIVAL, description));
        return end;
    }

    private LocalDateTime addRequiredAirportTasks(
            final List<ItineraryStep> steps,
            final LocalDateTime start,
            final DayType dayType,
            final Set<RequiredAirportTask> tasks
    ) {
        LocalDateTime cursor = start;

        for (RequiredAirportTask task : tasks) {
            int minutes = task.durationMinutes(dayType);
            LocalDateTime end = cursor.plusMinutes(minutes);

            steps.add(new ItineraryStep(cursor, end, StepType.TASK, task.getDescription()));
            cursor = end;
        }

        return cursor;
    }

    private LocalDateTime addTransportStep(
            final List<ItineraryStep> steps,
            final LocalDateTime start,
            final DayType dayType,
            final TransportOption option
    ) {
        int minutes = (int) Math.round(option.getEtaMinutes() * dayType.transportDelayFactor());
        LocalDateTime end = start.plusMinutes(minutes);

        String description = buildTransportDescription(option);

        steps.add(new ItineraryStep(start, end, StepType.MOVE, description));
        return end;
    }

    private String buildTransportDescription(final TransportOption option) {
        StringBuilder sb = new StringBuilder();
        sb.append(TRANSPORT_DESCRIPTION_PREFIX)
                .append(option.getType())
                .append(TRANSPORT_DESCRIPTION_SUFFIX);

        sb.append(TRANSFER_DESCRIPTION_PREFIX)
                .append(option.getTransfers())
                .append(TRANSFER_DESCRIPTION_SUFFIX);

        sb.append(COST_DESCRIPTION_PREFIX)
                .append(option.getCost())
                .append(COST_DESCRIPTION_SUFFIX);

        return sb.toString();
    }

    private LocalDateTime addBufferStep(
            final List<ItineraryStep> steps,
            final LocalDateTime start,
            final DayType dayType
    ) {
        int minutes = (int) Math.round(CHECKIN_BUFFER_MINUTES * dayType.taskDelayFactor());
        LocalDateTime end = start.plusMinutes(minutes);

        steps.add(new ItineraryStep(start, end, StepType.BUFFER, CHECKIN_BUFFER_DESCRIPTION));
        return end;
    }
}
