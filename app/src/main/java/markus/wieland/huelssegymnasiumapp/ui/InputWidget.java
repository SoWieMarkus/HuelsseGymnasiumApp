package markus.wieland.huelssegymnasiumapp.ui;

import android.animation.LayoutTransition;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class InputWidget<T, G> extends LinearLayout implements ValueChangeListener<T>, View.OnClickListener {

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

    protected abstract InputWidgetBody<T> getBody();

    protected abstract InputWidgetHeader<G> getHeader();

    protected abstract String getTitle();

    protected void initialize() {
        setOrientation(LinearLayout.VERTICAL);
        inputWidgetBody = getBody();
        inputWidgetHeader = getHeader();

        inputWidgetHeader.setOnClickListener(this);

        this.addView(inputWidgetHeader);
        this.addView(inputWidgetBody);

        inputWidgetBody.setValueChangeListener(this);
        inputWidgetBody.setLayoutTransition(new LayoutTransition());
        inputWidgetHeader.setTitle(getTitle());

        setExpanded(true);
        inputWidgetBody.setValue(inputWidgetBody.getDefaultValue());
    }

    @Override
    public void onClick(View view) {
        inputWidgetBody.setVisibility(inputWidgetBody.getVisibility() == VISIBLE ? GONE : VISIBLE);
    }

    public void setExpanded(boolean expanded) {
        inputWidgetBody.setVisibility(expanded ? VISIBLE : GONE);
    }

    public void setValue(T t) {
        inputWidgetBody.setValue(t);
        setExpanded(false);
    }

    public T getValue() {
        return inputWidgetBody.getValue();
    }

    public ValidationResult check() {
        return inputWidgetBody.check();
    }

}
