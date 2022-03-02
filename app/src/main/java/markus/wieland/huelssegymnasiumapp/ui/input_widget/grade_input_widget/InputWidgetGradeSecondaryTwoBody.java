package markus.wieland.huelssegymnasiumapp.ui.input_widget.grade_input_widget;

import android.content.Context;
import android.widget.NumberPicker;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.DefaultGrade;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.SecondaryTwoGrade;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetBody;

public class InputWidgetGradeSecondaryTwoBody extends InputWidgetBody<DefaultGrade> implements NumberPicker.OnValueChangeListener {

    private NumberPicker numberPicker;

    public InputWidgetGradeSecondaryTwoBody(Context context) {
        super(context);
    }

    @Override
    public void onValueSet(DefaultGrade secondaryTwoGrade) {
        numberPicker.setValue(secondaryTwoGrade.getValue());
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
