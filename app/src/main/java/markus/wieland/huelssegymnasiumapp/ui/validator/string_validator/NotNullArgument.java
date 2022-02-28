package markus.wieland.huelssegymnasiumapp.ui.validator.string_validator;

import android.content.Context;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.validator.ValidatorArgument;

public class NotNullArgument extends ValidatorArgument<String> {

    public NotNullArgument(String argumentName) {
        super(argumentName);
    }

    @Override
    protected boolean isValid(String s) {
        return s != null;
    }

    @Override
    public String getErrorMessage(Context context) {
        return String.format(context.getString(R.string.validation_argument_error_non_null), getArgumentName());
    }
}
