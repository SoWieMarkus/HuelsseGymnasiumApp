package markus.wieland.huelssegymnasiumapp.time_table;

import androidx.room.Embedded;
import androidx.room.Relation;

import markus.wieland.huelssegymnasiumapp.subjects.Subject;

public class TimeTableSlotWithSubject {

    @Embedded
    private TimeTableSlot timeTableSlot;

    @Relation(parentColumn = "subjectId", entityColumn = "subjectId")
    private Subject subject;

}
