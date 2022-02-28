package markus.wieland.huelssegymnasiumapp.helper.validator;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.huelssegymnasiumapp.ui.ValidationResult;

@Getter
@Setter
public class Validator<T> implements Validation {

    private T item;
    protected List<ValidatorArgument<T>> arguments;

    public Validator(T item) {
        this.item = item;
        this.arguments = new ArrayList<>();
    }

    public Validator<T> add(ValidatorArgument<T> argument) {
        this.arguments.add(argument);
        return this;
    }

    public ValidationResult check(Context context) {
        for (ValidatorArgument<T> argument : arguments) {
            ValidationResult result = argument.check(context, item);
            if (!result.isValid()) return result;
        }
        return new ValidationResult();
    }


}
