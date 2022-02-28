package markus.wieland.huelssegymnasiumapp.ui.time_table;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;
import markus.wieland.huelssegymnasiumapp.modules.time_table.Time;
import markus.wieland.huelssegymnasiumapp.modules.time_table.TimePeriod;
import markus.wieland.huelssegymnasiumapp.modules.time_table.TimeTableColumn;
import markus.wieland.huelssegymnasiumapp.modules.time_table.TimeTableSlotWithSubject;

public class TimeTableColumnView extends RelativeLayout {

    public TimeTableColumnView(Context context) {
        super(context);
    }

    public void setTimeTableSlots(TimeTableColumn timeTableColumn, int sizePerMinute, int minHour, OnItemClickListener<TimeTableSlotWithSubject> onItemClickListener) {
        for (TimeTableSlotWithSubject timeTableSlotWithSubject : timeTableColumn) {
            TimeTableSlotView view = new TimeTableSlotView(getContext());
            view.setTimeTableSlot(timeTableSlotWithSubject);
            view.setOnClickListener(view1 -> onItemClickListener.onClick(timeTableSlotWithSubject));

            TimePeriod timePeriod = timeTableSlotWithSubject.getTimeTableSlot().getTimePeriod();

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.height = sizePerMinute * timePeriod.getMinutes();

            int minutesToStart = TimePeriod.getMinutes(new Time(minHour, 0), timePeriod.getStartTime());
            int marginTop = sizePerMinute * minutesToStart;

            layoutParams.setMargins(2, marginTop, 0, 2);  // left, top, right, bottom

            addView(view, layoutParams);
        }
    }

}
