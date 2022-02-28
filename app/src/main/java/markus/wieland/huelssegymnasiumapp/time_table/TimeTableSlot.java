package markus.wieland.huelssegymnasiumapp.time_table;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.databases.DatabaseEntity;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;

@Getter
@Setter
@Entity(foreignKeys = @ForeignKey(entity = Subject.class, parentColumns = "subjectId",
        childColumns = "subjectId", onDelete = ForeignKey.CASCADE))
public class TimeTableSlot implements DatabaseEntity {

    @PrimaryKey(autoGenerate = true)
    private long timeTableSlotId;

    private int week;
    private int day;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;

    @Nullable
    private Long subjectId;

    @Override
    public long getUniqueId() {
        return getTimeTableSlotId();
    }

    @Ignore
    public void setTimePeriod(TimePeriod timePeriod) {
        this.endMinute = timePeriod.getEndTime().getMinute();
        this.endHour = timePeriod.getEndTime().getHour();
        this.startMinute = timePeriod.getStartTime().getMinute();
        this.startHour= timePeriod.getStartTime().getHour();
    }

    @Ignore
    public TimePeriod getTimePeriod() {
        return new TimePeriod(new Time(startHour, startMinute), new Time(endHour, endMinute));
    }
}
