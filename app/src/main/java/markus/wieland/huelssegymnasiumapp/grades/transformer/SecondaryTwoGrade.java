package markus.wieland.huelssegymnasiumapp.grades.transformer;

import androidx.annotation.NonNull;

public class SecondaryTwoGrade extends DefaultGrade {

    public SecondaryTwoGrade(int value) {
        super(false, value);
    }

    public SecondaryTwoGrade(boolean isExam) {
        super(isExam);
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
        int value = getValue();
        if (getValue() <= 15 && getValue() >= 13)
            return Value.VERY_GOOD;
        if (getValue() <= 12 && getValue() >= 10)
            return Value.GOOD;
        if (getValue() <= 9 && getValue() >= 7)
            return Value.SATISFACTORY;
        if (getValue() <= 6 && getValue() >= 4)
            return Value.SUFFICIENT;
        if (getValue() <= 3 && getValue() >= 1)
            return Value.POOR;
        return Value.DEFICIENT;
    }
}
