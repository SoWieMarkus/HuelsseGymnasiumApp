package markus.wieland.huelssegymnasiumapp.ui.input_widget.subject_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import java.util.List;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidget;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetBody;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetHeader;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetHeaderText;
import markus.wieland.huelssegymnasiumapp.ui.validator.Validation;
import markus.wieland.huelssegymnasiumapp.ui.validator.ValidationResult;

public class SubjectInputWidget extends InputWidget<Subject, String> implements Validation {

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
        setVisibility(subjects.isEmpty() ? GONE : VISIBLE);
    }

    public void select(Long id) {
        getInputWidgetBody().select(id);
    }

    @Override
    protected int getTitle() {
        return R.string.input_widget_subject_title;
    }

    @Override
    public void onUpdateHeader(Subject subject) {
        getInputWidgetHeader().setValue(subject == null ? "" : subject.getName());
    }

    @Override
    public ValidationResult check(Context context) {
        if (getInputWidgetBody().getValue() == null) {
            return new ValidationResult(context.getString(R.string.input_widget_subject_error_nothing_selected), false);
        }
        return new ValidationResult();
    }
}
