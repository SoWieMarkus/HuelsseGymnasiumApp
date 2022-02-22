package markus.wieland.huelssegymnasiumapp.time_table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import markus.wieland.huelssegymnasiumapp.helper.Matrix;

public class TimeTable {

    private final Matrix<TimeTableSlot> timeTableMatrix;
    private final TimeTableConfiguration timeTableConfiguration;

    public TimeTable(TimeTableConfiguration timeTableConfiguration, List<TimeTableSlot> timeTableSlots) {
        timeTableMatrix = new Matrix<>(timeTableConfiguration.getLessons().size(), 5);
        this.timeTableConfiguration = timeTableConfiguration;
        for (TimeTableSlot timeTableSlot : timeTableSlots) {
            timeTableMatrix.set(timeTableSlot.getLesson(), timeTableSlot.getDay(), timeTableSlot);
        }
    }

    public List<TimeTableSlot> getTodayLessons() {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.getDayOfWeek().getValue() >= 5) return new ArrayList<>();
        return timeTableMatrix.getColumn(localDateTime.getDayOfWeek().getValue());
    }

    public int getCurrentLesson(){
        return timeTableConfiguration.getCurrentTimeSlot();
    }


}
