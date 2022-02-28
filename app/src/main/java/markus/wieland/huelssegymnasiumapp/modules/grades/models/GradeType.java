package markus.wieland.huelssegymnasiumapp.modules.grades.models;

import android.content.Context;

import androidx.room.TypeConverter;

import java.util.Objects;

import lombok.Getter;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.helper.Type;

@Getter
public enum GradeType implements Type {

    TEST(0, R.string.grade_type_test, false),
    PRESENTATION(1, R.string.grade_type_presentation, false),
    PROJECT(2, R.string.grade_type_project, false),
    OTHERS(3, R.string.grade_type_others, false),
    EXAM(4, R.string.grade_type_exam, true);

    private final int id;
    private final int displayName;
    private final boolean isExam;

    GradeType(int id, int displayName, boolean isExam) {
        this.id = id;
        this.displayName = displayName;
        this.isExam = isExam;
    }

    @TypeConverter
    public static int toInt(GradeType gradeType) {
        return gradeType.getId();
    }

    @TypeConverter
    public static GradeType toGradeType(int id) {
        for (GradeType gradeType : Objects.requireNonNull(GradeType.class.getEnumConstants())) {
            if (gradeType.getId() == id) return gradeType;
        }
        return null;
    }

    @Override
    public String getDisplayNameAsString(Context context) {
        return context.getString(getDisplayName());
    }

}
