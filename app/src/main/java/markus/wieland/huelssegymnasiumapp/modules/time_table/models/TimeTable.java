package markus.wieland.huelssegymnasiumapp.modules.time_table.models;

import java.util.List;

import lombok.Getter;

@Getter
public class TimeTable {
    private static final int AMOUNT_DAYS_IN_WEEK = 5;

    private static final int MIN_HEIGHT_MINUTE = 3;

    private final TimeTableDay[] days;
    private final TimePeriod range;

    public TimeTable(List<TimeTableSlotWithSubject> slots) {
        this.days = new TimeTableDay[AMOUNT_DAYS_IN_WEEK];
        for (int day = 0; day < AMOUNT_DAYS_IN_WEEK; day++) {
            this.days[day] = new TimeTableDay(day);
        }

        for (TimeTableSlotWithSubject timeTableSlotWithSubject : slots) {
            int day = timeTableSlotWithSubject.getTimeTableSlot().getDay();
            this.days[day].add(timeTableSlotWithSubject);
        }

        this.range = new TimePeriod(slots);
    }

    public int getMaxHour() {
        int maxHour = getRange().getEndTime().getHour() + 1;
        return Math.min(maxHour, 24);
    }

    public int getMinHour() {
        return getRange().getStartTime().getHour();
    }

    public int getHeightNeeded(int heightOfView) {
        return totalMinutes() * sizePerMinute(heightOfView) + 50;
    }

    public int totalMinutes() {
        return range.getMinutes();
    }

    public int sizePerMinute(int height) {
        int heightOfMinute = height / totalMinutes();
        return Math.max(heightOfMinute, MIN_HEIGHT_MINUTE);
    }

    public static int getMargin(int sizePerMinute, Time startTime, Time endTime) {
        TimePeriod period = new TimePeriod(startTime, endTime);
        return sizePerMinute * period.getMinutes();
    }

}
