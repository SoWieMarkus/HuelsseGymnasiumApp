package markus.wieland.huelssegymnasiumapp.ui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public abstract class EnumInputWidget<T> extends InputWidget<T, String>{
    public EnumInputWidget(Context context) {
        super(context);
    }

    public EnumInputWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EnumInputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected InputWidgetBody<T> getBody() {
        return null;
    }

    @Override
    protected InputWidgetHeader<String> getHeader() {
        return null;
    }

}
