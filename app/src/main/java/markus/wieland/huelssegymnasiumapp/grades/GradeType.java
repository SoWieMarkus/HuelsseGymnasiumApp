package markus.wieland.huelssegymnasiumapp.grades;

import androidx.room.TypeConverter;

import java.util.Objects;

import lombok.Getter;

@Getter
public enum GradeType {

    TEST(0, false),
    PRESENTATION(1, false),
    PROJECT(2, false),
    OTHERS(3, false),
    EXAM(4, true);

    private final int id;
    private final boolean isExam;

    GradeType(int id, boolean isExam) {
        this.id = id;
        this.isExam = isExam;
    }

    @TypeConverter
    public static int toInt(GradeType gradeType) {
        return gradeType.getId();
    }

    @TypeConverter
    public static GradeType toGradeType(int id) {
        for (GradeType gradeType : Objects.requireNonNull(GradeType.class.getEnumConstants())){
            if (gradeType.getId() == id) return gradeType;
        }
        return null;
    }

}
