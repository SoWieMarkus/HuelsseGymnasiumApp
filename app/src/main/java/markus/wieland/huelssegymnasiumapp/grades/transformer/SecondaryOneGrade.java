package markus.wieland.huelssegymnasiumapp.grades.transformer;

import androidx.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecondaryOneGrade extends DefaultGrade {

    public static final int SIGN_PLUS = 0;
    public static final int SIGN_MINUS = 2;
    public static final String[] DISPLAY_VALUES = new String[]{"+", " ", "-"};

    private int sign;

    public SecondaryOneGrade(int value, int sign) {
        super(false, value);
        this.sign = sign;
    }

    public SecondaryOneGrade(boolean isExam) {
        super(isExam);
        this.sign = 0;
        setValue(0);
    }


    @NonNull
    @Override
    public String toString() {
        return (getValue() + DISPLAY_VALUES[sign]).trim();
    }

    @Override
    public float getValueToCalculateAverage() {
        if (getValue() == 1 && sign == SIGN_PLUS) return 1;
        if (getValue() == 6 && sign == SIGN_MINUS) return 6;
        switch (sign) {
            case SIGN_PLUS:
                return (float) (getValue() - 0.3);
            case SIGN_MINUS:
                return (float) (getValue() + 0.3);
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
        if (databaseValue < 10 || databaseValue > 62 || splits.length != 2)
            throw exception;

        int valueFromDatabase = Integer.parseInt(splits[0]);
        int signFromDatabase = Integer.parseInt(splits[1]);

        if (valueFromDatabase < 1
                || valueFromDatabase > 6
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
