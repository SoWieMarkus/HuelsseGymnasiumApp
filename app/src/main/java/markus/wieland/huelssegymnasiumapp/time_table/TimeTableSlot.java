package markus.wieland.huelssegymnasiumapp.time_table;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.databases.DatabaseEntity;

@Getter
@Setter
@Entity
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
}
