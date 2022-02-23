package markus.wieland.huelssegymnasiumapp.ui.calendar_input_widget;

import android.content.Context;
import android.widget.CalendarView;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetBody;
import markus.wieland.huelssegymnasiumapp.ui.ValidationResult;

public class InputWidgetCalendarBody extends InputWidgetBody<LocalDate> implements CalendarView.OnDateChangeListener {

    private CalendarView calendarView;
    private Calendar calendar;

    public InputWidgetCalendarBody(Context context) {
        super(context);
    }

    @Override
    public void setValue(LocalDate localDate) {
        calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
        calendarView.setDate(calendar.getTimeInMillis());
        getValueChangeListener().onValueChanged(localDate);
    }

    @Override
    protected void initialize() {
        super.initialize();
        calendarView = findViewById(R.id.input_widget_calendar_body_calendar);
        calendarView.setOnDateChangeListener(this);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
    }

    @Override
    public LocalDate getValue() {
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
    }

    @Override
    public ValidationResult check() {
        return new ValidationResult();
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
