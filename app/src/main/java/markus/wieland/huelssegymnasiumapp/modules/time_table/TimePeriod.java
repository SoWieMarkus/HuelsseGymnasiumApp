package markus.wieland.huelssegymnasiumapp.time_table;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TimePeriod {

    private Time startTime;
    private Time endTime;

    @NonNull
    @Override
    public String toString() {
        if (startTime == null || endTime == null) throw new IllegalStateException("Time can't be null!");
        return startTime.toString() + " - " + endTime.toString();
    }

    public TimePeriod(){
        this.startTime = new Time(8,0);
        this.endTime = new Time(9,0);
    }

    public boolean isCurrently() {
        LocalDateTime currentTime = LocalDateTime.now();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        return startTime.getHour() >= hour
                && endTime.getHour() <= hour
                && startTime.getMinute() >= minute
                && endTime.getMinute() <= minute;
    }

    public boolean overlaps(TimePeriod timePeriod) {
        return !(timePeriod.getEndTime().isBefore(this.startTime) || timePeriod.getStartTime().isAfter(this.endTime));
    }

    public TimePeriod(List<TimeTableSlotWithSubject> timeTableSlotWithSubjects) {
        this.startTime = new Time(7,0);
        this.endTime = new Time(14,0);

        for (TimeTableSlotWithSubject timeTableSlotWithSubject : timeTableSlotWithSubjects) {
            Time startTimeCompare = timeTableSlotWithSubject.getTimeTableSlot().getTimePeriod().getStartTime();
            Time endTimeCompare = timeTableSlotWithSubject.getTimeTableSlot().getTimePeriod().getEndTime();

            if (startTimeCompare.isBefore(this.startTime)) {
                this.startTime.setHour(startTimeCompare.getHour());
                this.startTime.setMinute(startTimeCompare.getMinute());
            }

            if (endTimeCompare.isAfter(this.endTime)) {
                this.endTime.setHour(endTimeCompare.getHour());
                this.endTime.setMinute(endTimeCompare.getMinute());
            }
        }
    }

    public int getMinutes(){
        return getMinutes(startTime, endTime);
    }

    public static int getMinutes(Time startTime, Time endTime){
        int minutesFirstHour = 60 - startTime.getMinute();
        int minutesLastHour = endTime.getMinute();

        int startHour = startTime.getHour() + 1;
        if (startHour > 24)
            startHour = 24;
        int endHour = endTime.getHour();

        return  minutesFirstHour + minutesLastHour + ((endHour-startHour) * 60);
    }


}
