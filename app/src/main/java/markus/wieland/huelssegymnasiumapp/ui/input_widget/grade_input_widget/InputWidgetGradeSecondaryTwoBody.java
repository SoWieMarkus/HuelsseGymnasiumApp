package markus.wieland.huelssegymnasiumapp.ui.grade_input_widget;

import android.content.Context;
import android.widget.NumberPicker;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.grades.GradeFormat;
import markus.wieland.huelssegymnasiumapp.grades.transformer.DefaultGrade;
import markus.wieland.huelssegymnasiumapp.grades.transformer.SecondaryOneGrade;
import markus.wieland.huelssegymnasiumapp.grades.transformer.SecondaryTwoGrade;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetBody;
import markus.wieland.huelssegymnasiumapp.ui.ValidationResult;

public class InputWidgetGradeSecondaryTwoBody extends InputWidgetBody<DefaultGrade> implements NumberPicker.OnValueChangeListener {

    private NumberPicker numberPicker;

    public InputWidgetGradeSecondaryTwoBody(Context context) {
        super(context);
    }

    @Override
    public void setValue(DefaultGrade secondaryTwoGrade) {
        numberPicker.setValue(secondaryTwoGrade.getValue());
        getValueChangeListener().onValueChanged(secondaryTwoGrade);
    }

    @Override
    public SecondaryTwoGrade getValue() {
        return new SecondaryTwoGrade(numberPicker.getValue());
    }


    @Override
    protected void initialize() {
        super.initialize();
        numberPicker = findViewById(R.id.input_widget_number_picker_body_number_picker);
        numberPicker.setOnValueChangedListener(this);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(15);
        numberPicker.setWrapSelectorWheel(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_input_widget_number_picker_body;
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        getValueChangeListener().onValueChanged(getValue());
    }
}
