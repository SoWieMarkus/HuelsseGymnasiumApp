package markus.wieland.huelssegymnasiumapp.ui.time_table;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.Time;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTable;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableDay;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlotWithSubject;

public class TimeTableView extends LinearLayout {

    public static final int MIN_TIME_HEIGHT = 180;

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
        layoutParams.height = timeTable.getHeightNeeded(height);
        setLayoutParams(layoutParams);

        int sizePerMinute = timeTable.sizePerMinute(height);
        Time startTime = timeTable.getRange().getStartTime();

        for (TimeTableDay timeTableDay : timeTable.getDays()) {
            TimeTableDayView timeTableDayView = new TimeTableDayView(getContext());
            timeTableDayView.setTimeTableDay(timeTableDay, sizePerMinute, startTime, onItemClickListener);
            addView(timeTableDayView);
        }

    }
}
