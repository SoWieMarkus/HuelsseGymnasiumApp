package markus.wieland.huelssegymnasiumapp.subjects;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.grades.Grade;

@Getter
@Setter
public class SubjectWithGradesAndCalendar {

    @Embedded
    private Subject subject;

    @Relation(parentColumn = "subjectId", entityColumn = "subjectId")
    private List<Grade> grades;

    @Relation(parentColumn = "subjectId", entityColumn = "subjectId")
    private List<CalendarEntry> calendarEntries;

}
