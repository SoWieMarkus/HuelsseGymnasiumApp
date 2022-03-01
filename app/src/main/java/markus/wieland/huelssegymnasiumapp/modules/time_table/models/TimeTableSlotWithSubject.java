package markus.wieland.huelssegymnasiumapp.modules.time_table.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;

@Getter
@Setter
public class TimeTableSlotWithSubject implements Serializable {

    @Embedded
    private TimeTableSlot timeTableSlot;

    @Relation(parentColumn = "subjectId", entityColumn = "subjectId")
    private Subject subject;

}
