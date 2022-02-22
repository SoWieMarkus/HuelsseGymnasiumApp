package markus.wieland.huelssegymnasiumapp.ui.weighting_input_widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.InputWidget;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetBody;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetHeader;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetHeaderText;

public class WeightingInputWidget extends InputWidget<Integer, String> {

    public WeightingInputWidget(Context context) {
        super(context);
    }

    public WeightingInputWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeightingInputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected InputWidgetBody<Integer> getBody() {
        return new InputWidgetWeightingBody(getContext());
    }

    @Override
    protected InputWidgetHeader<String> getHeader() {
        return new InputWidgetHeaderText(getContext());
    }

    @Override
    protected String getTitle() {
        return getContext().getString(R.string.input_widget_weighting_title);
    }

    @Override
    public void onValueChanged(Integer integer) {
        getInputWidgetHeader().setValue(integer + " / " + (InputWidgetWeightingBody.MAX_EXAM_WEIGHTING - integer));
    }
}
