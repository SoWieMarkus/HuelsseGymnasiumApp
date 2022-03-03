package markus.wieland.huelssegymnasiumapp.ui.time_table;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.modules.time_table.models.Time;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTable;

public class TimeTableBackgroundView extends RelativeLayout {

    private static final int DIVIDER_MARGIN_HORIZONTAL = 2;
    private static final int DIVIDER_MARGIN_BOTTOM = 0;

    public TimeTableBackgroundView(Context context) {
        this(context, null);
    }

    public TimeTableBackgroundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeTableBackgroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void drawTimeTable(TimeTable timeTable, int height) {

        this.removeAllViews();

        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = timeTable.getHeightNeeded(height);
        setLayoutParams(layoutParams);

        int minHour = timeTable.getMinHour();
        int maxHour = timeTable.getMaxHour();
        int sizePerMinute = timeTable.sizePerMinute(height);

        for (int i = 1; i < maxHour - minHour; i++) {
            Time time = new Time(minHour + i, 0);
            TimeTableDividerView view = new TimeTableDividerView(getContext());
            view.setTime(time);

            RelativeLayout.LayoutParams dividerParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int heightTest = view.getMeasuredHeight();

            int marginTop = TimeTable.getMargin(sizePerMinute, timeTable.getRange().getStartTime(), time) - heightTest / 2;
            dividerParams.setMargins(DIVIDER_MARGIN_HORIZONTAL, marginTop, DIVIDER_MARGIN_HORIZONTAL, DIVIDER_MARGIN_BOTTOM);  // left, top, right, bottom

            addView(view, dividerParams);
        }


        invalidate();
        postInvalidate();
    }
}
