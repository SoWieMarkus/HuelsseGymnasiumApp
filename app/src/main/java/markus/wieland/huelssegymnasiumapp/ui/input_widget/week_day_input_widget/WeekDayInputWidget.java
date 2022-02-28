package markus.wieland.huelssegymnasiumapp.ui.week_day_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.InputWidget;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetBody;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetHeader;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetHeaderText;

public class WeekDayInputWidget extends InputWidget<Integer, String> {
    public WeekDayInputWidget(Context context) {
        super(context);
    }

    public WeekDayInputWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeekDayInputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected InputWidgetWeekDayBody getBody() {
        return new InputWidgetWeekDayBody(getContext());
    }

    @Override
    protected InputWidgetHeader<String> getHeader() {
        return new InputWidgetHeaderText(getContext());
    }

    @Override
    public InputWidgetWeekDayBody getInputWidgetBody() {
        return (InputWidgetWeekDayBody) super.getInputWidgetBody();
    }

    @Override
    protected int getTitle() {
        return R.string.input_widget_week_day_title;
    }

    @Override
    public void onValueChanged(Integer integer) {
        getInputWidgetHeader().setValue(getInputWidgetBody().getDisplayName(integer));
    }
}
