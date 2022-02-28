package markus.wieland.huelssegymnasiumapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import markus.wieland.defaultappelements.uielements.activities.DefaultActivity;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.database.entities.calendar.CalendarViewModel;
import markus.wieland.huelssegymnasiumapp.database.entities.grade.GradeViewModel;
import markus.wieland.huelssegymnasiumapp.database.entities.subject.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.grades.Grade;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;
import markus.wieland.huelssegymnasiumapp.subjects.SubjectWithGradesAndCalendar;
import markus.wieland.huelssegymnasiumapp.ui.OnCalendarContextMenu;
import markus.wieland.huelssegymnasiumapp.ui.OnContextMenuListener;
import markus.wieland.huelssegymnasiumapp.ui.StateRecyclerView;

public class SubjectDetailActivity extends DefaultActivity implements Observer<SubjectWithGradesAndCalendar>, OnContextMenuListener<Grade> {

    public static final String SUBJECT_ID = "markus.wieland.huelssegymnasiumapp.SUBJECT_ID";

    private StateRecyclerView<Grade, GradeAdapter.GradeViewHolder, GradeAdapter> recyclerViewGrades;
    private StateRecyclerView<CalendarEntry, CalendarAdapter.CalendarViewHolder, CalendarAdapter> recyclerViewCalendarEntries;

    private Button addGrade;
    private Button addCalendarEntry;

    private SubjectViewModel subjectViewModel;
    private GradeViewModel gradeViewModel;
    private CalendarViewModel calendarViewModel;
    private long subjectId;

    private Settings settings;
    private Subject subject;

    private AverageView averageView;

    public SubjectDetailActivity() {
        super(R.layout.activity_subject_detail);
    }

    private final OnCalendarContextMenu<CalendarEntry> calendarContextMenu = new OnCalendarContextMenu<CalendarEntry>() {
        @Override
        public void onDone(CalendarEntry calendarEntry) {
            onDelete(calendarEntry);
        }

        @Override
        public void onEdit(CalendarEntry calendarEntry) {
            startActivity(new Intent(SubjectDetailActivity.this, CreateCalendarEntryActivity.class).putExtra(
                    CreateItemActivity.OBJECT_TO_EDIT, calendarEntry));
        }

        @Override
        public void onDelete(CalendarEntry calendarEntry) {
            calendarViewModel.delete(calendarEntry);
        }

        @Override
        public void onClick(CalendarEntry calendarEntry) {
            onEdit(calendarEntry);
        }
    };

    @Override
    public void bindViews() {

        settings = new Settings(this);
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
        gradeViewModel = ViewModelProviders.of(this).get(GradeViewModel.class);
        calendarViewModel = ViewModelProviders.of(this).get(CalendarViewModel.class);
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

        recyclerViewGrades.setAdapter(new GradeAdapter(this));
        recyclerViewGrades.getRecyclerView().setHasFixedSize(false);
        recyclerViewGrades.getRecyclerView().setNestedScrollingEnabled(false);

        averageView.update(null);

        recyclerViewCalendarEntries.setAdapter(new CalendarAdapter(calendarContextMenu));
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
        } else if (item.getItemId() == R.id.menu_subject_delete && subject != null) {
            subjectViewModel.getSubjectWithGradesAndEvents(subjectId).removeObservers(this);
            subjectViewModel.delete(subject);
            finish();
        } else if (item.getItemId() == R.id.menu_subject_edit && subject != null) {
            startActivity(new Intent(this, CreateSubjectActivity.class)
                    .putExtra(CreateItemActivity.OBJECT_TO_EDIT, subject));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_subject, menu);
        return true;
    }
    @Override
    public void onChanged(SubjectWithGradesAndCalendar subjectWithGradesAndCalendar) {
        subject = subjectWithGradesAndCalendar.getSubject();
        recyclerViewGrades.getAdapter().setGradeFormat(settings.getGradeFormat());
        Objects.requireNonNull(getSupportActionBar()).setTitle(subjectWithGradesAndCalendar.getSubject().getName());
        recyclerViewGrades.submitList(subjectWithGradesAndCalendar.getGrades());
        recyclerViewCalendarEntries.submitList(subjectWithGradesAndCalendar.getCalendarEntries());
        averageView.update(subjectWithGradesAndCalendar.calculateAverage(settings.getGradeFormat()));
    }

    @Override
    public void onEdit(Grade grade) {
        startActivity(new Intent(this, CreateGradeActivity.class)
                .putExtra(CreateItemActivity.OBJECT_TO_EDIT, grade));
    }

    @Override
    public void onDelete(Grade grade) {
        gradeViewModel.delete(grade);
    }

    @Override
    public void onClick(Grade grade) {
        onEdit(grade);
    }
}