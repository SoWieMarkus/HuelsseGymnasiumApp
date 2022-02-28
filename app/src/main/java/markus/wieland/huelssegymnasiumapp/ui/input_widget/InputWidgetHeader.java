package markus.wieland.huelssegymnasiumapp.ui.input_widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

import lombok.Getter;

@Getter
public abstract class InputWidgetHeader<T> extends InputWidgetComponent {

    private TextView titleTextView;

    protected InputWidgetHeader(Context context) {
        super(context);
    }

    protected InputWidgetHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    protected InputWidgetHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initialize() {
        super.initialize();
        titleTextView = findViewById(getTitleViewId());
    }

    @IdRes
    public abstract int getTitleViewId();

    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    public abstract void setValue(T t);

}
