package markus.wieland.huelssegymnasiumapp;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.time_table.Time;
import markus.wieland.huelssegymnasiumapp.time_table.TimePeriod;
import markus.wieland.huelssegymnasiumapp.time_table.TimeTable;

public class TimeTableBackgroundView extends RelativeLayout {

    private TimeTable timeTable;
    private int height;
    private final Paint paint = new Paint();
    private Bitmap tempBitmap;
    private Canvas tempCanvas;


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
        this.timeTable = timeTable;
        this.height = height;

        this.removeAllViews();

        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = timeTable.getHeightNeeded(TimeTableView.MIN_TIME_HEIGHT, height);
        setLayoutParams(layoutParams);
        
        int minHour = timeTable.getMinHour();
        int maxHour = timeTable.getMaxHour();
        int heightHour = timeTable.getSizePerHour(TimeTableView.MIN_TIME_HEIGHT, height);


        for (int i = 1; i < maxHour - minHour; i++) {
            TimeTableDividerView view = new TimeTableDividerView(getContext());
            view.setTime(new Time(minHour +i, 0));

            RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int heightTest = view.getMeasuredHeight();

            int marginTop = heightHour * i - heightTest / 2;
                        layoutParams1.setMargins(2, marginTop, 0, 2);  // left, top, right, bottom

            addView(view, layoutParams1);
        }


        invalidate();
        postInvalidate();
    }
}
