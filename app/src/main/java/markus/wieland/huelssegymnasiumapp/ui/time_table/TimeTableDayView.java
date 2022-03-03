package markus.wieland.huelssegymnasiumapp.ui.time_table;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import lombok.Getter;
import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.Time;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableColumn;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableDay;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlotWithSubject;

@Getter
public class TimeTableDayView extends LinearLayout {

    private static final int DAY_MARGIN_HORIZONTAL = 3;
    private static final int DAY_MARGIN_VERTICAL = 0;

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
        params.setMargins(DAY_MARGIN_HORIZONTAL, DAY_MARGIN_VERTICAL, DAY_MARGIN_HORIZONTAL, DAY_MARGIN_VERTICAL);
        setLayoutParams(params);
    }

    public void setTimeTableDay(TimeTableDay timeTableDay, int sizePerMinute, Time time, OnItemClickListener<TimeTableSlotWithSubject> onItemClickListener) {
        this.timeTableDay = timeTableDay;

        for (TimeTableColumn timeTableColumn : timeTableDay.getColumns()) {
            TimeTableColumnView tableColumnView = new TimeTableColumnView(getContext());
            tableColumnView.setTimeTableSlots(timeTableColumn, sizePerMinute, time, onItemClickListener);
            addView(tableColumnView, getLayoutParams());
        }

    }
}
