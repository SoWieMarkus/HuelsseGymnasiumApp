package markus.wieland.huelssegymnasiumapp.ui.week_day_input_widget;

import android.content.Context;
import android.widget.NumberPicker;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetBody;

public class InputWidgetWeekDayBody extends InputWidgetBody<Integer> implements NumberPicker.OnValueChangeListener {

    private NumberPicker numberPicker;
    private String[] displayNames;

    protected InputWidgetWeekDayBody(Context context) {
        super(context);
    }

    @Override
    protected void initialize() {
        super.initialize();
         displayNames= new String[] {
                getContext().getString(R.string.week_day_monday),
                getContext().getString(R.string.week_day_tuesday),
                getContext().getString(R.string.week_day_wednesday),
                getContext().getString(R.string.week_day_thursday),
                getContext().getString(R.string.week_day_friday)
        };
        numberPicker = findViewById(R.id.input_widget_number_picker_body_number_picker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(4);
        numberPicker.setDisplayedValues(displayNames);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setOnValueChangedListener(this);
    }

    public String getDisplayName(int index) {
        return displayNames[index];
    }

    @Override
    public void setValue(Integer integer) {
        numberPicker.setValue(integer);
        getValueChangeListener().onValueChanged(integer);
    }

    @Override
    public Integer getValue() {
        return numberPicker.getValue();
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
