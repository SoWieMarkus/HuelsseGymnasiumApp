package markus.wieland.huelssegymnasiumapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class InputWidget<T, G> extends LinearLayout implements ValueChangeListener<T> {

    private InputWidgetBody<T> inputWidgetBody;
    private InputWidgetHeader<G> inputWidgetHeader;

    public InputWidget(Context context) {
        this(context, null);
    }

    public InputWidget(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    protected abstract InputWidgetBody<T>  getBody();

    protected abstract InputWidgetHeader<G> getHeader();

    protected abstract String getTitle();

    protected void initialize() {
        setOrientation(LinearLayout.VERTICAL);
        inputWidgetBody = getBody();
        inputWidgetHeader = getHeader();

        this.addView(inputWidgetHeader);
        this.addView(inputWidgetBody);

        inputWidgetBody.setValueChangeListener(this);
        inputWidgetHeader.setTitle(getTitle());
    }

    public void setValue(T t) {
        inputWidgetBody.setValue(t);
    }

    public T getValue(){
        return inputWidgetBody.getValue();
    }

    public ValidationResult check(){
        return inputWidgetBody.check();
    }

}
