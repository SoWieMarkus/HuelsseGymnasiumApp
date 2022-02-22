package markus.wieland.huelssegymnasiumapp.calendar;

import androidx.room.Embedded;
import androidx.room.Relation;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;

@Getter
@Setter
public class CalendarWithSubject {

    @Embedded
    private CalendarEntry entry;

    @Relation(parentColumn = "subjectId", entityColumn = "subjectId")
    private Subject subject;


}
