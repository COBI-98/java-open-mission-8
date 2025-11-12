package airport.domain.calendar;

import java.time.LocalDate;

public interface TravelCalendar {

    DayType dayTypeOf(LocalDate date);

    double congestionFactorOf(LocalDate date);
    
}
