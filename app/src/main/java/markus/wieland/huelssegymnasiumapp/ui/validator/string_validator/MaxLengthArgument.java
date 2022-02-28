package markus.wieland.huelssegymnasiumapp.helper.validator.string_validator;

import android.content.Context;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.helper.validator.ValidatorArgument;

public class MaxLengthArgument extends ValidatorArgument<String> {

    private final int maxLength;

    public MaxLengthArgument(String argumentName, int maxLength) {
        super(argumentName);
        this.maxLength = maxLength;
    }

    @Override
    public String getErrorMessage(Context context) {
        return String.format(context.getString(R.string.validation_argument_error_max_length), getArgumentName(), maxLength);
    }

    @Override
    protected boolean isValid(String s) {
        return s.length() < maxLength;
    }
}
