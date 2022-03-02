package markus.wieland.huelssegymnasiumapp.ui.input_widget.enum_input_widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.helper.Type;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetBody;

public class InputWidgetEnumBody<T extends Type> extends InputWidgetBody<T> implements NumberPicker.OnValueChangeListener {

    private NumberPicker numberPicker;
    private T[] ts;

    public InputWidgetEnumBody(Context context) {
        super(context);
    }

    public InputWidgetEnumBody(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InputWidgetEnumBody(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initialize() {
        super.initialize();
        numberPicker = findViewById(R.id.input_widget_number_picker_body_number_picker);
    }

    @Override
    public void onValueSet(T t) {
        if (t == null) return;
        this.numberPicker.setValue(t.getId());
    }

    @Override
    public T getValue() {
        return getSelection();
    }

    public void setList(T[] ts) {
        this.ts = ts;
        this.numberPicker.setMinValue(0);
        this.numberPicker.setMaxValue(ts.length - 1);
        this.numberPicker.setDisplayedValues(getDisplayValues());
        this.numberPicker.setWrapSelectorWheel(false);
        this.numberPicker.setOnValueChangedListener(this);
        this.setValue(ts[0]);
    }

    public T getSelection() {
        for (T t : ts) {
            if (t.getId() == numberPicker.getValue()) {
                return t;
            }
        }
        throw new IllegalStateException("Couldn't find type with id " + numberPicker.getValue());
    }

    private String[] getDisplayValues() {
        String[] displayValues = new String[ts.length];
        for (int i = 0; i < ts.length; i++) {
            displayValues[i] = ts[i].getDisplayNameAsString(getContext());
        }
        return displayValues;
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
