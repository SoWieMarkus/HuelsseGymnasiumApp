package markus.wieland.huelssegymnasiumapp.ui.input_widget.course_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidget;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetBody;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetHeader;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetHeaderText;


public class CourseInputWidget extends InputWidget<String, String> {
    public CourseInputWidget(Context context) {
        super(context);
    }

    public CourseInputWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CourseInputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected InputWidgetBody<String> getBody() {
        return new InputWidgetCourseBody(getContext());
    }

    @Override
    protected InputWidgetHeader<String> getHeader() {
        return new InputWidgetHeaderText(getContext());
    }

    @Override
    protected int getTitle() {
        return R.string.input_widget_string_title;
    }

    @Override
    public void onValueChanged(String s) {
        getInputWidgetHeader().setValue(s);
    }
}
