package markus.wieland.huelssegymnasiumapp.ui.enum_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.helper.Type;
import markus.wieland.huelssegymnasiumapp.ui.InputWidget;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetHeaderText;

public class EnumInputWidget<T extends Type> extends InputWidget<T, String> {
    public EnumInputWidget(Context context) {
        super(context);
    }

    public EnumInputWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EnumInputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected InputWidgetEnumBody<T> getBody() {
        return new InputWidgetEnumBody<>(getContext());
    }

    @Override
    protected InputWidgetHeaderText getHeader() {
        return new InputWidgetHeaderText(getContext());
    }

    @Override
    protected int getTitle() {
        return R.string.input_widget_enum_title;
    }

    public void setList(T[] ts) {
        ((InputWidgetEnumBody<T>)getInputWidgetBody()).setList(ts);
    }

    @Override
    public void onValueChanged(T t) {
        getInputWidgetHeader().setValue(t.getDisplayNameAsString(getContext()));
    }
}
