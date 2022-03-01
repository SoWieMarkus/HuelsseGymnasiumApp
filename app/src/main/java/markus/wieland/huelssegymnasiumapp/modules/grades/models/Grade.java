package markus.wieland.huelssegymnasiumapp.modules.grades.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.databases.DatabaseEntity;
import markus.wieland.defaultappelements.uielements.adapter.QueryableEntity;
import markus.wieland.huelssegymnasiumapp.modules.calendar.database.LocalDateConverter;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;

@Entity(foreignKeys = @ForeignKey(entity = Subject.class, parentColumns = "subjectId", childColumns = "subjectId", onDelete = ForeignKey.CASCADE))
@Getter
@Setter
public class Grade implements DatabaseEntity, QueryableEntity<Long>, Serializable {

    @PrimaryKey(autoGenerate = true)
    private long gradeId;

    @ColumnInfo(index = true)
    private Long subjectId;
    private int value;

    @TypeConverters(GradeType.class)
    private GradeType gradeType;

    @TypeConverters(LocalDateConverter.class)
    private LocalDate localDate;

    @Ignore
    @Override
    public long getUniqueId() {
        return getGradeId();
    }

    @Override
    public Long getId() {
        return getGradeId();
    }

    @Override
    public String getStringToApplyQuery() {
        return "";
    }

    @Ignore
    public DefaultGrade getGrade(GradeFormat gradeFormat) {
        if (gradeFormat == GradeFormat.ABITUR) {
            return new SecondaryTwoGrade(false).setUpFromDatabaseValue(value);
        }
        return new SecondaryOneGrade(false).setUpFromDatabaseValue(value);
    }

    @Ignore
    public DefaultGrade translate(GradeFormat gradeFormat) {
        return gradeFormat == GradeFormat.ABITUR
                ? new SecondaryTwoGrade(gradeType.isExam()).setUpFromDatabaseValue(getValue())
                : new SecondaryOneGrade(gradeType.isExam()).setUpFromDatabaseValue(getValue());
    }

}
