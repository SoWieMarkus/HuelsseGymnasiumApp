package markus.wieland.huelssegymnasiumapp.ui.input_widget.color_input_widget;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetHeader;

public class InputWidgetColorHeader extends InputWidgetHeader<Color> {

    private ConstraintLayout colorLayout;

    public InputWidgetColorHeader(Context context) {
        super(context);
    }

    @Override
    protected void initialize() {
        super.initialize();
        colorLayout = findViewById(R.id.input_widget_color_header_color);
    }

    @Override
    public int getTitleViewId() {
        return R.id.input_widget_color_header_title;
    }

    @Override
    public void setValue(Color color) {
        colorLayout.setBackgroundColor(color.getColorValue());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_input_widget_color_header;
    }
}
