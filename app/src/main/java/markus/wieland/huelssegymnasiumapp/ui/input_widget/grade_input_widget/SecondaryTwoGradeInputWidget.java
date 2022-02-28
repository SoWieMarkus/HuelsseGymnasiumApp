package markus.wieland.huelssegymnasiumapp.ui.grade_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.grades.transformer.DefaultGrade;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetBody;

public class SecondaryTwoGradeInputWidget extends GradeInputWidget{
    public SecondaryTwoGradeInputWidget(Context context) {
        super(context);
    }

    public SecondaryTwoGradeInputWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SecondaryTwoGradeInputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected InputWidgetBody<DefaultGrade> getBody() {
        return new InputWidgetGradeSecondaryTwoBody(getContext());
    }
}
