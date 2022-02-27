package markus.wieland.huelssegymnasiumapp;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.time_table.TimeTable;

public class TimeTableBackgroundView extends View {

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
        paint.setColor(Color.rgb(127, 127, 127));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        setWillNotDraw(false);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        tempBitmap = Bitmap.createBitmap(getWidth(), getWidth(), Bitmap.Config.ARGB_8888);
        tempCanvas = new Canvas(tempBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        tempCanvas.drawColor(0xffff00, PorterDuff.Mode.CLEAR);
        tempCanvas.drawCircle((float) getWidth() / 2, (float) getHeight() / 2, 10, paint);
        canvas.drawBitmap(tempBitmap, 0, 0, null);
    }


    public void drawTimeTable(TimeTable timeTable, int height) {
        this.timeTable = timeTable;
        this.height = height;

        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = timeTable.getHeightNeeded(TimeTableView.MIN_TIME_HEIGHT, height);

        setLayoutParams(layoutParams);

        invalidate();
        postInvalidate();
    }
}
