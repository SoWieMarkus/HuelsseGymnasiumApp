package markus.wieland.huelssegymnasiumapp.time_table;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimePeriod {

    private Time startTime;
    private Time endTime;

    public boolean isCurrently() {
        LocalDateTime currentTime = LocalDateTime.now();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        return startTime.getHour() >= hour
                && endTime.getHour() <= hour
                && startTime.getMinute() >= minute
                && endTime.getMinute() <= minute;
    }

}
