package markus.wieland.huelssegymnasiumapp.ui.input_widget.subject_input_widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetBody;

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

    public void select(Long id) {
        if (id == null) {
            subjectInputWidgetAdapter.select(null);
            return;
        }
        subjectInputWidgetAdapter.select(getSubjectById(id));
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
    }

    @Override
    public void setValue(Subject subject) {
        subjectInputWidgetAdapter.select(subject);
        getValueChangeListener().onValueChanged(subject);
    }

    public Subject getSubjectById(long id) {
        for (Subject subject : subjectInputWidgetAdapter.getList()) {
            if (subject.getSubjectId() == id) {
                return subject;
            }
        }
        return null;
    }

    @Override
    public Subject getValue() {
        return subjectInputWidgetAdapter.getSelectedSubject();
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
