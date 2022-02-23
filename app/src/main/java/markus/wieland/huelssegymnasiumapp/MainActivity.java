package markus.wieland.huelssegymnasiumapp;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.defaultappelements.uielements.activities.DefaultActivity;
import markus.wieland.huelssegymnasiumapp.grades.GradeType;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;
import markus.wieland.huelssegymnasiumapp.ui.enum_input_widget.EnumInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.subject_input_widget.SubjectInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.weighting_input_widget.WeightingInputWidget;

public class MainActivity extends DefaultActivity implements NavigationBarView.OnItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    private SubjectFragment subjectFragment;
    private CalendarFragment calendarFragment;
    private DashBoardFragment dashBoardFragment;
    private TimeTableFragment timeTableFragment;
    private SubstitutionFragment substitutionFragment;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    public void bindViews() {
        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation_view);

        timeTableFragment = new TimeTableFragment();
        dashBoardFragment = new DashBoardFragment();
        subjectFragment = new SubjectFragment();
        calendarFragment = new CalendarFragment();
        substitutionFragment = new SubstitutionFragment();
    }

    @Override
    public void initializeViews() {
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    @Override
    public void execute() {
        showFragment(new DashBoardFragment());
        bottomNavigationView.setSelectedItemId(R.id.activity_main_bottom_navigation_dashboard);
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_main_bottom_navigation_calendar) {
            showFragment(calendarFragment);
        } else if (item.getItemId() == R.id.activity_main_bottom_navigation_subjects) {
            showFragment(subjectFragment);
        } else if (item.getItemId() == R.id.activity_main_bottom_navigation_substitutions) {
            showFragment(substitutionFragment);
        } else if (item.getItemId() == R.id.activity_main_bottom_navigation_dashboard) {
            showFragment(dashBoardFragment);
        } else if (item.getItemId() == R.id.activity_main_bottom_navigation_time_table) {
            showFragment(timeTableFragment);
        }

        return true;
    }
}