package markus.wieland.huelssegymnasiumapp.time_table;

import java.util.List;

import lombok.Getter;

@Getter
public class TimeTable {
    private static final int AMOUNT_DAYS_IN_WEEK = 5;

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

    public int getHoursToDisplay() {
        return getMaxHour() - getMinHour();
    }

    public int getSizePerHour(int minHeight, int heightOfView) {
        int sizePerHour = heightOfView / getHoursToDisplay();
        return Math.max(sizePerHour, minHeight);
    }

    public int getSizePerMinute(int minHeight, int heightOfView) {
        return getSizePerHour(minHeight, heightOfView) / 60;
    }

    public int getHeightNeeded(int minHeight, int heightOfView) {
        return getHoursToDisplay() * getSizePerHour(minHeight, heightOfView);
    }

}
