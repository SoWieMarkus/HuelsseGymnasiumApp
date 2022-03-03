package markus.wieland.huelssegymnasiumapp.ui.time_table;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.Time;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimePeriod;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTable;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableColumn;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlotWithSubject;

public class TimeTableColumnView extends RelativeLayout {

    private static final int COLUMN_MARGIN_HORIZONTAL = 2;
    private static final int COLUMN_MARGIN_BOTTOM = 0;

    public TimeTableColumnView(Context context) {
        super(context);
    }

    public void setTimeTableSlots(TimeTableColumn timeTableColumn, int sizePerMinute, Time time, OnItemClickListener<TimeTableSlotWithSubject> onItemClickListener) {
        for (TimeTableSlotWithSubject timeTableSlotWithSubject : timeTableColumn) {
            TimeTableSlotView timeTableSlotView = new TimeTableSlotView(getContext());
            timeTableSlotView.setTimeTableSlot(timeTableSlotWithSubject);
            timeTableSlotView.setOnClickListener(view -> onItemClickListener.onClick(timeTableSlotWithSubject));

            TimePeriod timePeriod = timeTableSlotWithSubject.getTimeTableSlot().getTimePeriod();

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.height = sizePerMinute * timePeriod.getMinutes();

            layoutParams.setMargins(COLUMN_MARGIN_HORIZONTAL, TimeTable.getMargin(sizePerMinute, time, timePeriod.getStartTime()), COLUMN_MARGIN_HORIZONTAL, COLUMN_MARGIN_BOTTOM);  // left, top, right, bottom

            addView(timeTableSlotView, layoutParams);
        }
    }

}
