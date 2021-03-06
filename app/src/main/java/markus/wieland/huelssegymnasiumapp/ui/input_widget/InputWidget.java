package markus.wieland.huelssegymnasiumapp.ui.input_widget;

import android.animation.LayoutTransition;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.huelssegymnasiumapp.R;


@Getter
@Setter
public abstract class InputWidget<T, G> extends LinearLayout implements ValueChangeListener<T>, View.OnClickListener {

    private InputWidgetBody<T> inputWidgetBody;
    private InputWidgetHeader<G> inputWidgetHeader;

    private ValueChangeListener<T> valueChangeListener;

    protected InputWidget(Context context) {
        this(context, null);
    }

    protected InputWidget(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    protected InputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    protected abstract InputWidgetBody<T> getBody();

    protected abstract InputWidgetHeader<G> getHeader();

    @StringRes
    protected abstract int getTitle();

    protected void initialize() {
        setOrientation(LinearLayout.VERTICAL);
        inputWidgetBody = getBody();
        inputWidgetHeader = getHeader();

        inputWidgetHeader.setOnClickListener(this);

        addView(inputWidgetHeader);
        addView(inputWidgetBody);

        inputWidgetBody.setValueChangeListener(this);
        inputWidgetBody.setLayoutTransition(new LayoutTransition());
        inputWidgetHeader.setTitle(getContext().getString(getTitle()));

        setExpanded(true);
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

    @Override
    public void onValueChanged(T t) {
        onUpdateHeader(t);
        if (valueChangeListener == null) return;
        valueChangeListener.onValueChanged(t);
    }

    public abstract void onUpdateHeader(T t);
}
