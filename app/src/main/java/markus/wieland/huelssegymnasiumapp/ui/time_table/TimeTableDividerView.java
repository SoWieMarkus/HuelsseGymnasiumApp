package markus.wieland.huelssegymnasiumapp.ui.time_table;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.time_table.Time;

public class TimeTableDividerView extends LinearLayout {

    private final TextView timeTextView;

    public TimeTableDividerView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_time_table_time_divider_view, this);
        timeTextView = findViewById(R.id.time_table_divider_view_time);
    }

    public void setTime(Time time) {
        timeTextView.setText(time.toString());
    }
}


