package markus.wieland.huelssegymnasiumapp.modules.time_table;

import androidx.room.Embedded;
import androidx.room.Relation;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;

@Getter
@Setter
public class TimeTableSlotWithSubject {

    @Embedded
    private TimeTableSlot timeTableSlot;

    @Relation(parentColumn = "subjectId", entityColumn = "subjectId")
    private Subject subject;

}
