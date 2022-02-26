package markus.wieland.huelssegymnasiumapp.ui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class InputWidgetBody<T> extends InputWidgetComponent {

    private ValueChangeListener<T> valueChangeListener;

    protected InputWidgetBody(Context context) {
        this(context, null);
    }

    protected InputWidgetBody(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    protected InputWidgetBody(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void setValue(T t);

    public abstract T getValue();

    public abstract ValidationResult check();


}
