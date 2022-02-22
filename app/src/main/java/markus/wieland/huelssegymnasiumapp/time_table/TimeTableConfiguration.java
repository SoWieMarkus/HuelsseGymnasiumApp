package markus.wieland.huelssegymnasiumapp.time_table;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeTableConfiguration {

    private List<TimePeriod> lessons;

    public static final int NO_LESSON = -1;

    public int getCurrentTimeSlot(){
        int currentLesson = 0;
        for (TimePeriod lesson : lessons) {
            if (lesson.isCurrently()) return currentLesson;
            currentLesson++;
        }
        return NO_LESSON;
    }

}
