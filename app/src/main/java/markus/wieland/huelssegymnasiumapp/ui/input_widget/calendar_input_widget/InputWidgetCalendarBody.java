package markus.wieland.huelssegymnasiumapp.ui.input_widget.calendar_input_widget;

import android.content.Context;
import android.widget.CalendarView;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetBody;

public class InputWidgetCalendarBody extends InputWidgetBody<LocalDate> implements CalendarView.OnDateChangeListener {

    private CalendarView calendarView;
    private Calendar calendar;

    public InputWidgetCalendarBody(Context context) {
        super(context);
    }

    @Override
    public void onValueSet(LocalDate localDate) {
        calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
        calendarView.setDate(calendar.getTimeInMillis());
    }

    @Override
    protected void initialize() {
        super.initialize();
        calendarView = findViewById(R.id.input_widget_calendar_body_calendar);
        calendarView.setOnDateChangeListener(this);
        calendar = Calendar.getInstance();
    }

    @Override
    public LocalDate getValue() {
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_input_widget_calendar_body;
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
        calendar.set(year, month, day);
        getValueChangeListener().onValueChanged(getValue());
    }
}
