package markus.wieland.huelssegymnasiumapp.ui.input_widget.grade_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.DefaultGrade;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidget;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetHeader;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetHeaderText;

public abstract class GradeInputWidget extends InputWidget<DefaultGrade, String> {
    protected GradeInputWidget(Context context) {
        super(context);
    }

    protected GradeInputWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    protected GradeInputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected InputWidgetHeader<String> getHeader() {
        return new InputWidgetHeaderText(getContext());
    }

    @Override
    protected int getTitle() {
        return R.string.activity_create_grade_title;
    }

    @Override
    public void onValueChanged(DefaultGrade defaultGrade) {
        getInputWidgetHeader().setValue(defaultGrade.toString());
    }
}
