package markus.wieland.huelssegymnasiumapp.ui.input_widget.color_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import lombok.Setter;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidget;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.ValueChangeListener;

@Setter
public class ColorInputWidget extends InputWidget<Color, Color> {

    private ValueChangeListener<Color> colorValueChangeListener;

    public ColorInputWidget(Context context) {
        super(context);
    }

    public ColorInputWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorInputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected InputWidgetColorBody getBody() {
        return new InputWidgetColorBody(getContext());
    }

    @Override
    protected InputWidgetColorHeader getHeader() {
        return new InputWidgetColorHeader(getContext());
    }

    @Override
    protected int getTitle() {
        return R.string.input_widget_color_title;
    }

    @Override
    public void onValueChanged(Color color) {
        super.getInputWidgetHeader().setValue(color);
        if (colorValueChangeListener != null) colorValueChangeListener.onValueChanged(color);
    }
}
