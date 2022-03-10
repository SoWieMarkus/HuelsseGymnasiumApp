package markus.wieland.huelssegymnasiumapp.ui.time_table;

import android.content.Context;
import android.graphics.Color;
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

        addTextView(timeTableSlotWithSubject.getSubject().getAbbreviation());
        addTextView(timeTableSlotWithSubject.getTimeTableSlot().roomToString(getContext()));
    }

    private void addTextView(String text) {
        TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        textView.setText(text);
        addView(textView);
    }

}
