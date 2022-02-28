package markus.wieland.huelssegymnasiumapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;
import markus.wieland.huelssegymnasiumapp.time_table.TimeTable;
import markus.wieland.huelssegymnasiumapp.time_table.TimeTableSlotWithSubject;

public class TimeTableView extends LinearLayout {

    public static final int MIN_TIME_HEIGHT = 120;

    public TimeTableView(Context context) {
        this(context, null);
    }

    public TimeTableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTimeTableSlots(OnItemClickListener<TimeTableSlotWithSubject> onItemClickListener, TimeTable timeTable, int height) {
        this.removeAllViews();

        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = timeTable.getHeightNeeded(MIN_TIME_HEIGHT, height);

        setLayoutParams(layoutParams);

        int sizePerMinute = timeTable.getSizePerMinute(MIN_TIME_HEIGHT, height);
        int minHour = timeTable.getMinHour();



        for (TimeTableDay timeTableDay : timeTable.getDays()) {
            TimeTableDayView timeTableDayView = new TimeTableDayView(getContext());
            timeTableDayView.setTimeTableDay(timeTableDay, sizePerMinute, minHour, onItemClickListener);
            addView(timeTableDayView);
        }

    }
}
