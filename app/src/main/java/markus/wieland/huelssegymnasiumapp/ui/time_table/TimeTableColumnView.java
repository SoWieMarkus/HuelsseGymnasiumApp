package markus.wieland.huelssegymnasiumapp.ui.time_table;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.Time;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimePeriod;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTable;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableColumn;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlotWithSubject;

public class TimeTableColumnView extends RelativeLayout {

    public TimeTableColumnView(Context context) {
        super(context);
    }

    public void setTimeTableSlots(TimeTableColumn timeTableColumn, int sizePerMinute, Time time, OnItemClickListener<TimeTableSlotWithSubject> onItemClickListener) {
        for (TimeTableSlotWithSubject timeTableSlotWithSubject : timeTableColumn) {
            TimeTableSlotView view = new TimeTableSlotView(getContext());
            view.setTimeTableSlot(timeTableSlotWithSubject);
            view.setOnClickListener(view1 -> onItemClickListener.onClick(timeTableSlotWithSubject));

            TimePeriod timePeriod = timeTableSlotWithSubject.getTimeTableSlot().getTimePeriod();

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.height = sizePerMinute * timePeriod.getMinutes();

            layoutParams.setMargins(2, TimeTable.getMargin(sizePerMinute, time, timePeriod.getStartTime()), 0, 2);  // left, top, right, bottom

            addView(view, layoutParams);
        }
    }

}
