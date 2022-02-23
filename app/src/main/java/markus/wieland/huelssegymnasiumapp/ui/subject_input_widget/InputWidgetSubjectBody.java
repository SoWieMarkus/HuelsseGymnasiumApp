package markus.wieland.huelssegymnasiumapp.ui.subject_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;
import markus.wieland.huelssegymnasiumapp.ui.InputWidgetBody;
import markus.wieland.huelssegymnasiumapp.ui.ValidationResult;

public class InputWidgetSubjectBody extends InputWidgetBody<Subject> implements OnItemClickListener<Subject> {

    private SubjectInputWidgetAdapter subjectInputWidgetAdapter;

    public InputWidgetSubjectBody(Context context) {
        super(context);
    }

    public InputWidgetSubjectBody(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InputWidgetSubjectBody(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initialize() {
        super.initialize();
        subjectInputWidgetAdapter = new SubjectInputWidgetAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.input_widget_subject_body_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(subjectInputWidgetAdapter);
    }

    public void setList(List<Subject> subjects) {
        subjectInputWidgetAdapter.submitList(subjects);
        setValue(subjects.isEmpty() ? null : subjects.get(0));
    }

    @Override
    public void setValue(Subject subject) {
        subjectInputWidgetAdapter.select(subject);
        getValueChangeListener().onValueChanged(subject);
    }

    @Override
    public Subject getValue() {
        return subjectInputWidgetAdapter.getSelectedSubject();
    }

    @Override
    public ValidationResult check() {
        if (subjectInputWidgetAdapter.getSelectedSubject() == null) {
            return new ValidationResult(getContext().getString(R.string.input_widget_subject_error_nothing_selected), false);
        }
        return new ValidationResult();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_input_widget_subject_body;
    }

    @Override
    public void onClick(Subject subject) {
        getValueChangeListener().onValueChanged(subject);
    }
}
