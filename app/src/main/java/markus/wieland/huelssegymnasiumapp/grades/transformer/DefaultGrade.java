package markus.wieland.huelssegymnasiumapp.grades.transformer;

import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DefaultGrade {

    private boolean isExam;
    private int value;

    public DefaultGrade(boolean isExam, int value) {
        this.isExam = isExam;
        this.value = value;
    }

    public DefaultGrade(boolean isExam) {
        this.isExam = isExam;
        this.value = 0;
    }

    @NonNull
    public abstract String toString();

    public abstract float getValueToCalculateAverage();

    public abstract int getDatabaseValue();

    public abstract DefaultGrade setUpFromDatabaseValue(int databaseValue);

    public abstract Value getRawGradeValue();

    @Getter
    public enum Value {

        VERY_GOOD(Color.rgb(0, 200, 50)),
        GOOD(Color.rgb(0, 200, 50)),
        SATISFACTORY(Color.rgb(200,255,50)),
        SUFFICIENT(Color.rgb(255, 200, 50)),
        POOR(Color.rgb(255,100,50)),
        DEFICIENT(Color.rgb(255,50,50));

        @ColorInt
        private final int color;

        Value(int color) {
            this.color = color;
        }
    }

}
