package markus.wieland.huelssegymnasiumapp.modules.grades.models;

import androidx.annotation.NonNull;

public class SecondaryTwoGrade extends DefaultGrade {

    public SecondaryTwoGrade(int value) {
        super(false, value);
    }

    public SecondaryTwoGrade(boolean isExam) {
        super(isExam);
    }

    public SecondaryTwoGrade() {
        super(false, 15);
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

    @Override
    public float getValueToCalculateAverage() {
        return getValue();
    }

    @Override
    public int getDatabaseValue() {
        return getValue();
    }

    @Override
    public DefaultGrade setUpFromDatabaseValue(int databaseValue) {
        setValue(databaseValue);
        return this;
    }

    @Override
    public Value getRawGradeValue() {
        int valueOfGrade = getValue();
        if (valueOfGrade <= 15 && valueOfGrade >= 13)
            return Value.VERY_GOOD;
        if (valueOfGrade <= 12 && valueOfGrade >= 10)
            return Value.GOOD;
        if (valueOfGrade <= 9 && valueOfGrade >= 7)
            return Value.SATISFACTORY;
        if (valueOfGrade <= 6 && valueOfGrade >= 4)
            return Value.SUFFICIENT;
        if (valueOfGrade <= 3 && valueOfGrade >= 1)
            return Value.POOR;
        return Value.DEFICIENT;
    }
}
