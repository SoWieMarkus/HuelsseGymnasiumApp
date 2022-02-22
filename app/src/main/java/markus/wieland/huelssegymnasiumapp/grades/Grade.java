package markus.wieland.huelssegymnasiumapp.grades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.databases.DatabaseEntity;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;

@Entity(foreignKeys = @ForeignKey(entity = Subject.class, parentColumns = "subjectId", childColumns = "subjectId", onDelete = ForeignKey.CASCADE))
@Getter
@Setter
public class Grade implements DatabaseEntity {

    @PrimaryKey(autoGenerate = true)
    private long gradeId;

    @ColumnInfo(index = true)
    private long subjectId;
    private int value;

    @TypeConverters(GradeType.class)
    private GradeType gradeType;

    @Ignore
    @Override
    public long getUniqueId() {
        return getGradeId();
    }

}
