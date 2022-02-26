package markus.wieland.huelssegymnasiumapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import markus.wieland.defaultappelements.uielements.fragments.DefaultFragment;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntryWithSubject;
import markus.wieland.huelssegymnasiumapp.database.entities.calendar.CalendarViewModel;
import markus.wieland.huelssegymnasiumapp.database.entities.subject.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.database.entities.time_table.TimeTableSlotViewModel;
import markus.wieland.huelssegymnasiumapp.subjects.SubjectWithGradesAndCalendar;

public class DashboardFragment extends DefaultFragment {

    private SubjectViewModel subjectViewModel;
    private TimeTableSlotViewModel timeTableSlotViewModel;
    private CalendarViewModel calendarViewModel;
    private AverageView averageView;

    private Button addGrade;
    private Button addCalendarEntry;

    private OnFragmentSelectedListener onFragmentSelectedListener;

    public DashboardFragment() {
        super(R.layout.fragment_dashboard);
        setHasOptionsMenu(true);
    }

    public void setOnFragmentSelectedListener(OnFragmentSelectedListener onFragmentSelectedListener) {
        this.onFragmentSelectedListener = onFragmentSelectedListener;
    }

    @Override
    public void bindViews() {
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
        timeTableSlotViewModel = ViewModelProviders.of(this).get(TimeTableSlotViewModel.class);
        calendarViewModel = ViewModelProviders.of(this).get(CalendarViewModel.class);

        addCalendarEntry = findViewById(R.id.fragment_dashboard_add_calendar_entry);
        addGrade = findViewById(R.id.fragment_dashboard_add_grade);
        averageView = findViewById(R.id.fragment_dashboard_total_average);
        averageView.update(null);
    }

    @Override
    public void initializeViews() {
        addGrade.setOnClickListener(this::onClickAddGrade);
        addCalendarEntry.setOnClickListener(this::onClickAddCalendarEntry);
    }

    public void onClickAddGrade(View view){
        startActivity(new Intent(getActivity(), CreateGradeActivity.class));
        onFragmentSelectedListener.onSelect(R.id.activity_main_bottom_navigation_subjects);
    }

    public void onClickAddCalendarEntry(View view){
        startActivity(new Intent(getActivity(), CreateCalendarEntryActivity.class));
        onFragmentSelectedListener.onSelect(R.id.activity_main_bottom_navigation_calendar);
    }


    @Override
    public void execute() {
        calendarViewModel.getCalendarEntryWithSubjects().observe(this, this::onChangedCalendar);
        subjectViewModel.getAllSubjectsWithGradesAndEvents().observe(this,this::onChangedSubjects);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_profile_profile) {
            if (getActivity() == null) return false;
            startActivity(new Intent(getActivity(), SettingsActivity.class));
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void onChangedCalendar(List<CalendarEntryWithSubject> calendarEntryWithSubjects) {
        LocalDate inSevenDays = LocalDate.now().plusDays(7);
        List<CalendarEntryWithSubject> calendarEntriesThisWeek = new ArrayList<>();
        for (CalendarEntryWithSubject calendarEntryWithSubject : calendarEntryWithSubjects) {
            if (calendarEntryWithSubject.getCalendarEntry().getLocalDate().isBefore(inSevenDays)) {
                calendarEntriesThisWeek.add(calendarEntryWithSubject);
            }
        }
    }

    public void onChangedSubjects(List<SubjectWithGradesAndCalendar> subjectWithGradesAndCalendars) {
        averageView.calculateAverage(subjectWithGradesAndCalendars);
    }
}
