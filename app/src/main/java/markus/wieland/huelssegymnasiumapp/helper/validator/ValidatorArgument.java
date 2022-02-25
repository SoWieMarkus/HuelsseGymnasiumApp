package markus.wieland.huelssegymnasiumapp.helper.validator;

import android.content.Context;

import lombok.Getter;
import markus.wieland.huelssegymnasiumapp.ui.ValidationResult;

@Getter
public abstract class ValidatorArgument<T> {

    private final String argumentName;

    protected ValidatorArgument(String argumentName) {
        this.argumentName = argumentName;
    }

    protected abstract boolean isValid(T t);

    public ValidationResult check(Context context, T t) {
        if (isValid(t)) return new ValidationResult();
        return new ValidationResult(getErrorMessage(context), false);
    }

    public abstract String getErrorMessage(Context context);

}
