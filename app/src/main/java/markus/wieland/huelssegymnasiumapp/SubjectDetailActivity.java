package markus.wieland.huelssegymnasiumapp;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import markus.wieland.defaultappelements.uielements.activities.DefaultActivity;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.database.entities.subject.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.grades.Grade;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;
import markus.wieland.huelssegymnasiumapp.subjects.SubjectWithGradesAndCalendar;

public class SubjectDetailActivity extends DefaultActivity implements Observer<SubjectWithGradesAndCalendar> {

    public static final String SUBJECT_ID = "markus.wieland.huelssegymnasiumapp.SUBJECT_ID";

    private StateRecyclerView<Grade, GradeAdapter.GradeViewHolder, GradeAdapter> recyclerViewGrades;
    private StateRecyclerView<CalendarEntry, CalendarAdapter.CalendarViewHolder, CalendarAdapter> recyclerViewCalendarEntries;

    private Button addGrade;
    private Button addCalendarEntry;

    private SubjectViewModel subjectViewModel;
    private long subjectId;

    private Settings settings;

    private AverageView averageView;

    public SubjectDetailActivity() {
        super(R.layout.activity_subject_detail);
    }

    @Override
    public void bindViews() {

        settings = new Settings(this);
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
        recyclerViewGrades = findViewById(R.id.activity_subject_detail_grades);
        recyclerViewCalendarEntries = findViewById(R.id.activity_subject_detail_calendar_entries);
        addGrade = findViewById(R.id.actvitiy_subject_detail_add_grade);
        addCalendarEntry = findViewById(R.id.actvitiy_subject_detail_add_calendar_entries);
        averageView = findViewById(R.id.actvitiy_subject_detail_average);
    }

    @Override
    public void initializeViews() {
        if (getSupportActionBar() == null)
            throw new IllegalStateException("Support Action Bar can't be null");
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewGrades.setAdapter(new GradeAdapter());
        recyclerViewGrades.getRecyclerView().setHasFixedSize(false);
        recyclerViewGrades.getRecyclerView().setNestedScrollingEnabled(false);

        averageView.update(null);

        recyclerViewCalendarEntries.setAdapter(new CalendarAdapter(null));
        recyclerViewCalendarEntries.getRecyclerView().setHasFixedSize(false);
        recyclerViewCalendarEntries.getRecyclerView().setNestedScrollingEnabled(false);

        addGrade.setOnClickListener(view ->
                startActivity(new Intent(this, CreateGradeActivity.class).putExtra(
                        CreateItemActivity.SUBJECT_ID, subjectId)));

        addCalendarEntry.setOnClickListener(view ->
                startActivity(new Intent(this, CreateCalendarEntryActivity.class).putExtra(
                        CreateItemActivity.SUBJECT_ID, subjectId)));
    }

    @Override
    public void execute() {
        subjectId = getIntent().getLongExtra(SUBJECT_ID, Subject.NO_SUBJECT_ID);
        if (subjectId == Subject.NO_SUBJECT_ID) {
            finish();
            return;
        }
        subjectViewModel.getSubjectWithGradesAndEvents(subjectId).observe(this, this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChanged(SubjectWithGradesAndCalendar subjectWithGradesAndCalendar) {
        recyclerViewGrades.getAdapter().setGradeFormat(settings.getGradeFormat());
        Objects.requireNonNull(getSupportActionBar()).setTitle(subjectWithGradesAndCalendar.getSubject().getName());
        recyclerViewGrades.submitList(subjectWithGradesAndCalendar.getGrades());
        recyclerViewCalendarEntries.submitList(subjectWithGradesAndCalendar.getCalendarEntries());
        averageView.update(subjectWithGradesAndCalendar.calculateAverage(settings.getGradeFormat()));
    }
}