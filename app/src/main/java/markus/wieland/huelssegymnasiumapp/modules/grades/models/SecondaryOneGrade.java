package markus.wieland.huelssegymnasiumapp.modules.grades.models;

import androidx.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecondaryOneGrade extends DefaultGrade {

    // the lowest possible combination of the grade and the sign is ten
    private static final int LOWEST_POSSIBLE_GRADE_COMBINATION_VALUE = 10;
    private static final int HIGHEST_POSSIBLE_GRADE_COMBINATION_VALUE = 62;
    private static final int HIGHEST_GRADE_VALUE = 6;
    private static final int LOWEST_GRADE_VALUE = 1;
    private static final float SIGN_VALUE = 0.3f;

    public static final int SIGN_PLUS = 0;
    public static final int SIGN_MINUS = 2;
    public static final int SIGN_NONE = 1;
    protected static final String[] DISPLAY_VALUES = new String[]{"+", " ", "-"};

    private int sign;

    public SecondaryOneGrade(int value, int sign) {
        super(false, value);
        this.sign = sign;
    }

    public SecondaryOneGrade(){
        super(false, LOWEST_GRADE_VALUE);
        this.sign = SIGN_NONE;
    }

    public static String[] getDisplayValues() {
        return DISPLAY_VALUES;
    }

    public SecondaryOneGrade(boolean isExam) {
        super(isExam, LOWEST_GRADE_VALUE);
        this.sign = SIGN_NONE;
    }

    @NonNull
    @Override
    public String toString() {
        return (getValue() + DISPLAY_VALUES[sign]).trim();
    }

    @Override
    public float getValueToCalculateAverage() {
        if (getValue() == LOWEST_GRADE_VALUE && sign == SIGN_PLUS) return LOWEST_GRADE_VALUE;
        if (getValue() == HIGHEST_GRADE_VALUE && sign == SIGN_MINUS) return HIGHEST_GRADE_VALUE;
        switch (sign) {
            case SIGN_PLUS:
                return getValue() - SIGN_VALUE;
            case SIGN_MINUS:
                return getValue() + SIGN_VALUE;
            default:
                return getValue();
        }
    }

    @Override
    public int getDatabaseValue() {
        return Integer.parseInt(getValue() + "" + sign);
    }

    @Override
    public DefaultGrade setUpFromDatabaseValue(int databaseValue) {
        IllegalArgumentException exception = new IllegalArgumentException("Database value isn't valid " + databaseValue);
        String[] splits = String.valueOf(databaseValue).split("");
        if (databaseValue < LOWEST_POSSIBLE_GRADE_COMBINATION_VALUE
                || databaseValue > HIGHEST_POSSIBLE_GRADE_COMBINATION_VALUE
                || splits.length != 2)
            throw exception;

        int valueFromDatabase = Integer.parseInt(splits[0]);
        int signFromDatabase = Integer.parseInt(splits[1]);

        if (valueFromDatabase < LOWEST_GRADE_VALUE
                || valueFromDatabase > HIGHEST_GRADE_VALUE
                || signFromDatabase < SIGN_PLUS
                || signFromDatabase > SIGN_MINUS) throw exception;

        setValue(valueFromDatabase);
        setSign(signFromDatabase);
        return this;
    }

    @Override
    public Value getRawGradeValue() {

        switch (getValue()) {
            case 1:
                return Value.VERY_GOOD;
            case 2:
                return Value.GOOD;
            case 3:
                return Value.SATISFACTORY;
            case 4:
                return Value.SUFFICIENT;
            case 5:
                return Value.POOR;
            case 6:
                return Value.DEFICIENT;
            default:
                throw new IllegalArgumentException("Unknown value " + getValue());
        }

    }
}
