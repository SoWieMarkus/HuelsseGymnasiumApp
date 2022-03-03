package markus.wieland.huelssegymnasiumapp.modules.time_table.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
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
        if (startTime == null || endTime == null)
            throw new IllegalStateException("Time can't be null!");
        return startTime.toString() + " - " + endTime.toString();
    }

    public TimePeriod() {
        this.startTime = new Time(8, 0);
        this.endTime = new Time(9, 0);
    }

    public boolean overlaps(TimePeriod timePeriod) {
        return !(timePeriod.getEndTime().isBefore(this.startTime) || timePeriod.getStartTime().isAfter(this.endTime));
    }

    public TimePeriod(List<TimeTableSlotWithSubject> timeTableSlotWithSubjects) {
        this.startTime = new Time(7, 30);
        this.endTime = new Time(13, 0);

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

    public int getMinutes() {
        return getMinutes(startTime, endTime);
    }

    public static int getMinutes(Time startTime, Time endTime) {
        int minutesFirstHour = 60 - startTime.getMinute();
        int minutesLastHour = endTime.getMinute();

        int startHour = startTime.getHour() + 1;
        if (startHour > Time.MAX_HOUR)
            startHour = Time.MAX_HOUR;
        int endHour = endTime.getHour();

        return minutesFirstHour + minutesLastHour + ((endHour - startHour) * Time.MINUTES_PER_HOUR);
    }


}
