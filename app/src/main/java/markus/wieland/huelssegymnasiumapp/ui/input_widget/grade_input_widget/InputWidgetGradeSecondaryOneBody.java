package markus.wieland.huelssegymnasiumapp.ui.grade_input_widget;

import android.content.Context;
import android.widget.NumberPicker;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.grades.transformer.DefaultGrade;
import markus.wieland.huelssegymnasiumapp.grades.transformer.SecondaryOneGrade;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetBody;
import markus.wieland.huelssegymnasiumapp.ui.ValidationResult;

public class InputWidgetGradeSecondaryOneBody extends InputWidgetBody<DefaultGrade> implements NumberPicker.OnValueChangeListener {

    private NumberPicker value;
    private NumberPicker sign;

    public InputWidgetGradeSecondaryOneBody(Context context) {
        super(context);
    }



    @Override
    protected void initialize() {
        super.initialize();
        sign = findViewById(R.id.input_widget_grade_secondary_one_sign);
        value = findViewById(R.id.input_widget_grade_secondary_one_value);

        sign.setMinValue(SecondaryOneGrade.SIGN_PLUS);
        sign.setMaxValue(SecondaryOneGrade.SIGN_MINUS);
        sign.setWrapSelectorWheel(false);
        sign.setDisplayedValues(SecondaryOneGrade.getDisplayValues());
        sign.setOnValueChangedListener(this);

        value.setMinValue(1);
        value.setMaxValue(6);
        value.setWrapSelectorWheel(false);
        value.setOnValueChangedListener(this);
    }

    @Override
    public void setValue(DefaultGrade defaultGrade) {
        SecondaryOneGrade secondaryOneGrade = (SecondaryOneGrade) defaultGrade;
        value.setValue(secondaryOneGrade.getValue());
        sign.setValue(secondaryOneGrade.getSign());
        getValueChangeListener().onValueChanged(secondaryOneGrade);
    }

    @Override
    public SecondaryOneGrade getValue() {
        return new SecondaryOneGrade(value.getValue(), sign.getValue());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_input_widget_grade_secondary_one_body;
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        getValueChangeListener().onValueChanged(getValue());
    }
}
