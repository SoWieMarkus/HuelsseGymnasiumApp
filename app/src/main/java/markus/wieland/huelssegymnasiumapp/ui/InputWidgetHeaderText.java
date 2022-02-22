package markus.wieland.huelssegymnasiumapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.R;

public class InputWidgetHeaderText extends InputWidgetHeader<String> {

    private TextView valueTextView;

    public InputWidgetHeaderText(Context context) {
        super(context);
    }

    public InputWidgetHeaderText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InputWidgetHeaderText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initialize() {
        super.initialize();
        valueTextView = findViewById(R.id.input_widget_text_header_value);
    }

    @Override
    public int getTitleViewId() {
        return R.id.input_widget_text_header_title;
    }

    @Override
    public void setValue(String s) {
        valueTextView.setText(s);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_input_widget_header_text;
    }
}
