package markus.wieland.huelssegymnasiumapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

public abstract class InputWidgetComponent extends LinearLayout {
    public InputWidgetComponent(Context context) {
        this(context, null);
    }

    public InputWidgetComponent(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputWidgetComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected void initialize() {
        LayoutInflater.from(getContext()).inflate(getLayoutId(), this);
    }
}
