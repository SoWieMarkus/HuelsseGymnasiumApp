package markus.wieland.huelssegymnasiumapp.helper.validator.string_validator;

import android.content.Context;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.helper.validator.ValidatorArgument;

public class MinLengthArgument extends ValidatorArgument<String> {

    private final int minLength;

    public MinLengthArgument(String argumentName, int minLength) {
        super(argumentName);
        this.minLength = minLength;
    }

    @Override
    public String getErrorMessage(Context context) {
        return String.format(context.getString(R.string.validation_argument_error_min_length), getArgumentName(), minLength);
    }

    @Override
    protected boolean isValid(String s) {
        return s.length() >= minLength;
    }
}
