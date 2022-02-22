package markus.wieland.huelssegymnasiumapp.calendar;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.databases.DatabaseEntity;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;

@Entity(foreignKeys = @ForeignKey(entity = Subject.class, parentColumns = "subjectId",
        childColumns = "subjectId", onDelete = ForeignKey.CASCADE))
@Getter
@Setter
public class CalendarEntry implements DatabaseEntity {

    @PrimaryKey(autoGenerate = true)
    private long eventId;

    @Nullable
    @ColumnInfo(index = true)
    private Long subjectId;

    @TypeConverters(CalendarEntryType.class)
    private CalendarEntryType calendarEntryType;

    private String title;

    @TypeConverters(LocalDateConverter.class)
    private LocalDate localDate;

    @Ignore
    @Override
    public long getUniqueId() {
        return getEventId();
    }
}
