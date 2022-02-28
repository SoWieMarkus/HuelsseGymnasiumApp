package markus.wieland.huelssegymnasiumapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import lombok.Getter;
import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;
import markus.wieland.huelssegymnasiumapp.time_table.TimeTableSlotWithSubject;

@Getter
public class TimeTableDayView extends LinearLayout {

    private TimeTableDay timeTableDay;

    public TimeTableDayView(Context context) {
        this(context, null);
    }

    public TimeTableDayView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeTableDayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.weight = 1;
        params.setMargins(3, 0, 3, 0);
        setLayoutParams(params);
    }

    public void setTimeTableDay(TimeTableDay timeTableDay, int sizePerMinute, int minHour, OnItemClickListener<TimeTableSlotWithSubject> onItemClickListener) {
        this.timeTableDay = timeTableDay;

        for (TimeTableColumn timeTableColumn : timeTableDay.getColumns()) {
            TimeTableColumnView tableColumnView = new TimeTableColumnView(getContext());
            tableColumnView.setTimeTableSlots(timeTableColumn, sizePerMinute, minHour, onItemClickListener);
            addView(tableColumnView, getLayoutParams());
        }

    }
}
