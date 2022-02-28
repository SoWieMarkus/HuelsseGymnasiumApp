package markus.wieland.huelssegymnasiumapp.ui.time_table_slot_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.helper.validator.Validation;
import markus.wieland.huelssegymnasiumapp.time_table.TimePeriod;
import markus.wieland.huelssegymnasiumapp.ui.InputWidget;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetBody;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetHeader;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetHeaderText;
import markus.wieland.huelssegymnasiumapp.ui.ValidationResult;

public class TimePeriodInputWidget extends InputWidget<TimePeriod, String> implements Validation {
    public TimePeriodInputWidget(Context context) {
        super(context);
    }

    public TimePeriodInputWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TimePeriodInputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected InputWidgetBody<TimePeriod> getBody() {
        return new InputWidgetTimePeriodBody(getContext());
    }

    @Override
    protected InputWidgetHeader<String> getHeader() {
        return new InputWidgetHeaderText(getContext());
    }

    @Override
    protected int getTitle() {
        return R.string.input_widget_time_period_title;
    }

    @Override
    public void onValueChanged(TimePeriod period) {
        getInputWidgetHeader().setValue(period.toString());
    }

    @Override
    public ValidationResult check(Context context) {
        TimePeriod timePeriod = getInputWidgetBody().getValue();
        if (timePeriod.getStartTime().isAfter(timePeriod.getEndTime())) {
            return new ValidationResult(getContext().getString(R.string.input_widget_time_period_error_time));
        }
        return new ValidationResult();
    }
}
