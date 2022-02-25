package markus.wieland.huelssegymnasiumapp;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import markus.wieland.defaultappelements.uielements.activities.DefaultActivity;
import markus.wieland.huelssegymnasiumapp.database.entities.subject.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.subjects.SubjectWithGradesAndCalendar;

public class SubjectDetailActivity extends DefaultActivity implements Observer<SubjectWithGradesAndCalendar> {

    public static final String SUBJECT_ID = "markus.wieland.huelssegymnasiumapp.SUBJECT_ID";
    public static final long NO_SUBJECT_ID = -1;

    // TODO maybe other adapters for this?

    private SubjectViewModel subjectViewModel;

    public SubjectDetailActivity() {
        super(R.layout.activity_subject_detail);
    }

    @Override
    public void bindViews() {
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);

    }

    @Override
    public void initializeViews() {
        if (getSupportActionBar() == null) throw new IllegalStateException("Support Action Bar can't be null");
        getSupportActionBar().setElevation(0);
    }

    @Override
    public void execute() {
        long subjectId = getIntent().getLongExtra(SUBJECT_ID, NO_SUBJECT_ID);
        if (subjectId == NO_SUBJECT_ID) {
            finish();
            return;
        }
        subjectViewModel.getSubjectWithGradesAndEvents(subjectId).observe(this, this);
    }

    @Override
    public void onChanged(SubjectWithGradesAndCalendar subjectWithGradesAndCalendar) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(subjectWithGradesAndCalendar.getSubject().getName());
    }
}