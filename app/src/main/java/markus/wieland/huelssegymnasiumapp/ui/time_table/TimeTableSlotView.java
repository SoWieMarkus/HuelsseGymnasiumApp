package markus.wieland.huelssegymnasiumapp.ui.time_table;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlotWithSubject;

public class TimeTableSlotView extends LinearLayout {
    public TimeTableSlotView(Context context) {
        super(context);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
    }

    public void setTimeTableSlot(TimeTableSlotWithSubject timeTableSlotWithSubject) {
        if (timeTableSlotWithSubject.getSubject() == null) return;
        setBackgroundColor(timeTableSlotWithSubject.getSubject().getColor());
        TextView abbreviation = new TextView(getContext());
        TextView room = new TextView(getContext());
        abbreviation.setGravity(Gravity.CENTER);
        room.setGravity(Gravity.CENTER);

        abbreviation.setText(timeTableSlotWithSubject.getSubject().getAbbreviation());
        room.setText("RAUM");
        addView(abbreviation);
        addView(room);
    }

}
