package markus.wieland.huelssegymnasiumapp.ui.input_widget.time_period_input_widget;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.Button;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.Time;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimePeriod;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetBody;

public class InputWidgetTimePeriodBody extends InputWidgetBody<TimePeriod> {

    private TimePeriod timePeriod;
    private Button startTime;
    private Button endTime;

    public InputWidgetTimePeriodBody(Context context) {
        super(context);
        this.timePeriod = new TimePeriod();
    }

    @Override
    protected void initialize() {
        super.initialize();
        startTime = findViewById(R.id.input_widget_time_period_start_time);
        endTime = findViewById(R.id.input_widget_time_period_end_time);
        startTime.setOnClickListener(view -> onClick(true));
        endTime.setOnClickListener(view -> onClick(false));
    }

    protected void onClick(boolean isStartTime) {
        Time time = isStartTime ? timePeriod.getStartTime() : timePeriod.getEndTime();

        TimePickerDialog timePickerDialog = new TimePickerDialog(new ContextThemeWrapper(getContext(), R.style.Theme_Dialog), (timePicker, selectedHour, selectedMinute) ->
                setNewValues(selectedHour, selectedMinute, isStartTime), time.getHour(), time.getMinute(), true);//Yes 24 hour time

        timePickerDialog.show();
    }

    protected void setNewValues(int hour, int minute, boolean isStartTime) {
        Time time = new Time(hour, minute);
        if (isStartTime) {
            timePeriod.setStartTime(time);
        } else {
            timePeriod.setEndTime(time);
        }

        update();
        getValueChangeListener().onValueChanged(getValue());
    }


    private void update() {
        startTime.setText(timePeriod.getStartTime().toString());
        endTime.setText(timePeriod.getEndTime().toString());
    }

    @Override
    public void onValueSet(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
        update();
    }

    @Override
    public TimePeriod getValue() {
        return timePeriod;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_input_widget_time_period_body;
    }
}
