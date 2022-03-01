package markus.wieland.huelssegymnasiumapp.modules.dashboard;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import markus.wieland.defaultappelements.uielements.fragments.DefaultFragment;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.calendar.CalendarInteractionListener;
import markus.wieland.huelssegymnasiumapp.modules.calendar.CalendarWithSubjectAdapter;
import markus.wieland.huelssegymnasiumapp.modules.calendar.CreateCalendarEntryActivity;
import markus.wieland.huelssegymnasiumapp.modules.calendar.database.CalendarViewModel;
import markus.wieland.huelssegymnasiumapp.modules.calendar.models.CalendarEntryWithSubject;
import markus.wieland.huelssegymnasiumapp.modules.grades.CreateGradeActivity;
import markus.wieland.huelssegymnasiumapp.modules.settings.SettingsActivity;
import markus.wieland.huelssegymnasiumapp.modules.subjects.database.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.SubjectWithGradesAndCalendar;
import markus.wieland.huelssegymnasiumapp.modules.time_table.database.TimeTableSlotViewModel;
import markus.wieland.huelssegymnasiumapp.ui.AverageView;
import markus.wieland.huelssegymnasiumapp.ui.StateRecyclerView;

public class DashboardFragment extends DefaultFragment {

    private SubjectViewModel subjectViewModel;
    private TimeTableSlotViewModel timeTableSlotViewModel;
    private CalendarViewModel calendarViewModel;
    private AverageView averageView;

    private Button addGrade;
    private Button addCalendarEntry;

    private StateRecyclerView<CalendarEntryWithSubject, CalendarWithSubjectAdapter.CalendarViewHolder, CalendarWithSubjectAdapter> homework;

    public DashboardFragment() {
        super(R.layout.fragment_dashboard);
        setHasOptionsMenu(true);
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
        homework = findViewById(R.id.fragment_dashboard_homework);
    }

    @Override
    public void initializeViews() {
        addGrade.setOnClickListener(this::onClickAddGrade);
        addCalendarEntry.setOnClickListener(this::onClickAddCalendarEntry);
        homework.setAdapter(new CalendarWithSubjectAdapter(new CalendarInteractionListener(getActivity(), calendarViewModel)));
    }

    public void onClickAddGrade(View view) {
        startActivity(new Intent(getActivity(), CreateGradeActivity.class));
    }

    public void onClickAddCalendarEntry(View view) {
        startActivity(new Intent(getActivity(), CreateCalendarEntryActivity.class));
    }


    @Override
    public void execute() {
        calendarViewModel.getCalendarEntryWithSubjects().observe(this, this::onChangedCalendar);
        subjectViewModel.getAllSubjectsWithGradesAndEvents().observe(this, this::onChangedSubjects);
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
        homework.submitList(calendarEntriesThisWeek);
    }

    public void onChangedSubjects(List<SubjectWithGradesAndCalendar> subjectWithGradesAndCalendars) {
        averageView.calculateAverage(subjectWithGradesAndCalendars);
    }

}
