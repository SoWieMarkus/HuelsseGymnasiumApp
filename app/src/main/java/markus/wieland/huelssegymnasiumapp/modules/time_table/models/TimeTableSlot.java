package markus.wieland.huelssegymnasiumapp.modules.time_table.models;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.databases.DatabaseEntity;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;

@Getter
@Setter
@Entity(foreignKeys = @ForeignKey(entity = Subject.class, parentColumns = "subjectId",
        childColumns = "subjectId", onDelete = ForeignKey.CASCADE))
public class TimeTableSlot implements DatabaseEntity, Serializable {

    @PrimaryKey(autoGenerate = true)
    private long timeTableSlotId;

    private int week;
    private int day;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;

    private String room;

    @Nullable
    @ColumnInfo(index = true)
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
        this.startHour = timePeriod.getStartTime().getHour();
    }

    @Ignore
    public TimePeriod getTimePeriod() {
        return new TimePeriod(new Time(startHour, startMinute), new Time(endHour, endMinute));
    }

    @Ignore
    public String roomToString(Context context) {
        return room == null ? context.getString(R.string.time_table_slot_no_room) : room;
    }
}
