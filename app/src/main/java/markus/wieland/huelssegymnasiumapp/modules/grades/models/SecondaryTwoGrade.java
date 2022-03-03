package markus.wieland.huelssegymnasiumapp.modules.grades.models;

import androidx.annotation.NonNull;

public class SecondaryTwoGrade extends DefaultGrade {

    private static final int MAX_VALUE_VERY_GOOD = 15;
    private static final int MIN_VALUE_VERY_GOOD = 13;
    private static final int MAX_VALUE_GOOD = 12;
    private static final int MIN_VALUE_GOOD = 10;
    private static final int MAX_VALUE_SATISFACTORY = 9;
    private static final int MIN_VALUE_SATISFACTORY = 7;
    private static final int MAX_VALUE_SUFFICIENT = 6;
    private static final int MIN_VALUE_SUFFICIENT = 4;
    private static final int MAX_VALUE_POOR = 3;
    private static final int MIN_VALUE_POOR = 1;

    public SecondaryTwoGrade(int value) {
        super(false, value);
    }

    public SecondaryTwoGrade(boolean isExam) {
        super(isExam);
    }

    public SecondaryTwoGrade() {
        super(false, MAX_VALUE_VERY_GOOD);
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
        if (isInsideRange(valueOfGrade, MAX_VALUE_VERY_GOOD, MIN_VALUE_VERY_GOOD))
            return Value.VERY_GOOD;
        if (isInsideRange(valueOfGrade, MAX_VALUE_GOOD, MIN_VALUE_GOOD))
            return Value.GOOD;
        if (isInsideRange(valueOfGrade, MAX_VALUE_SATISFACTORY, MIN_VALUE_SATISFACTORY))
            return Value.SATISFACTORY;
        if (isInsideRange(valueOfGrade, MAX_VALUE_SUFFICIENT, MIN_VALUE_SUFFICIENT))
            return Value.SUFFICIENT;
        if (isInsideRange(valueOfGrade, MAX_VALUE_POOR, MIN_VALUE_POOR))
            return Value.POOR;
        return Value.DEFICIENT;
    }

    private boolean isInsideRange(int value, int max, int min) {
        return value <= max && value >= min;
    }
}
