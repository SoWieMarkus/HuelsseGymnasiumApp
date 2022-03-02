package markus.wieland.huelssegymnasiumapp.ui.input_widget.weighting_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidget;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetBody;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetHeader;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetHeaderText;

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
    protected int getTitle() {
        return R.string.input_widget_weighting_title;
    }

    @Override
    public void onUpdateHeader(Integer integer) {
        getInputWidgetHeader().setValue(integer + " / " + (InputWidgetWeightingBody.MAX_EXAM_WEIGHTING - integer));
    }
}
