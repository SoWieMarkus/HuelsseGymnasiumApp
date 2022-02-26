package markus.wieland.huelssegymnasiumapp.ui.grade_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.grades.transformer.DefaultGrade;
import markus.wieland.huelssegymnasiumapp.ui.InputWidget;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetBody;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetHeader;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetHeaderText;

public abstract class GradeInputWidget extends InputWidget<DefaultGrade, String> {
    public GradeInputWidget(Context context) {
        super(context);
    }

    public GradeInputWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GradeInputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
