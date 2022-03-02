package markus.wieland.huelssegymnasiumapp.ui.input_widget.calendar_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import java.time.LocalDate;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.calendar.database.LocalDateConverter;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidget;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetBody;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetHeader;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetHeaderText;

public class CalendarInputWidget extends InputWidget<LocalDate, String> {


    public CalendarInputWidget(Context context) {
        super(context);
    }

    public CalendarInputWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarInputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected InputWidgetBody<LocalDate> getBody() {
        return new InputWidgetCalendarBody(getContext());
    }

    @Override
    protected InputWidgetHeader<String> getHeader() {
        return new InputWidgetHeaderText(getContext());
    }

    @Override
    protected int getTitle() {
        return R.string.input_widget_calendar_title;
    }

    @Override
    public void onUpdateHeader(LocalDate localDate) {
        getInputWidgetHeader().setValue(LocalDateConverter.toDisplayString(localDate));
    }
}
