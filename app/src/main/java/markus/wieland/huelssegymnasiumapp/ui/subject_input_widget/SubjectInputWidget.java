package markus.wieland.huelssegymnasiumapp.ui.subject_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import java.util.List;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;
import markus.wieland.huelssegymnasiumapp.ui.InputWidget;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetBody;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetHeader;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetHeaderText;

public class SubjectInputWidget extends InputWidget<Subject, String> {

    public SubjectInputWidget(Context context) {
        super(context);
    }

    public SubjectInputWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SubjectInputWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected InputWidgetBody<Subject> getBody() {
        return new InputWidgetSubjectBody(getContext());
    }

    @Override
    protected InputWidgetHeader<String> getHeader() {
        return new InputWidgetHeaderText(getContext());
    }

    @Override
    public InputWidgetSubjectBody getInputWidgetBody() {
        return (InputWidgetSubjectBody) super.getInputWidgetBody();
    }

    public void setList(List<Subject> subjects) {
        getInputWidgetBody().setList(subjects);
    }

    @Override
    protected int getTitle() {
        return R.string.input_widget_subject_title;
    }

    @Override
    public void onValueChanged(Subject subject) {
        getInputWidgetHeader().setValue(subject == null ? "" : subject.getName());
    }
}
