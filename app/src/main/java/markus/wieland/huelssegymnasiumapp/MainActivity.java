package markus.wieland.huelssegymnasiumapp;

import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

import markus.wieland.defaultappelements.uielements.activities.DefaultActivity;
import markus.wieland.huelssegymnasiumapp.modules.calendar.CalendarFragment;
import markus.wieland.huelssegymnasiumapp.modules.dashboard.DashboardFragment;
import markus.wieland.huelssegymnasiumapp.modules.settings.Settings;
import markus.wieland.huelssegymnasiumapp.modules.settings.SettingsActivity;
import markus.wieland.huelssegymnasiumapp.modules.subjects.SubjectFragment;
import markus.wieland.huelssegymnasiumapp.modules.substitutions.SubstitutionFragment;
import markus.wieland.huelssegymnasiumapp.modules.time_table.TimeTableFragment;

public class MainActivity extends DefaultActivity implements NavigationBarView.OnItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    private SubjectFragment subjectFragment;
    private CalendarFragment calendarFragment;
    private TimeTableFragment timeTableFragment;
    private SubstitutionFragment substitutionFragment;
    private DashboardFragment dashboardFragment;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    public void bindViews() {
        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation_view);

        timeTableFragment = new TimeTableFragment();
        subjectFragment = new SubjectFragment();
        calendarFragment = new CalendarFragment();
        substitutionFragment = new SubstitutionFragment();
        dashboardFragment = new DashboardFragment();
    }

    @Override
    public void initializeViews() {
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    @Override
    public void execute() {
        bottomNavigationView.setSelectedItemId(R.id.activity_main_bottom_navigation_dashboard);

        Settings settings = new Settings(this);
        if (!settings.doesExist()) {
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
        }

    }

    private void showFragment(Fragment fragment, @StringRes int title) {
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).commit();
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    public void select(int id) {
        if (id == R.id.activity_main_bottom_navigation_calendar) {
            showFragment(calendarFragment, R.string.menu_calendar);
        } else if (id == R.id.activity_main_bottom_navigation_subjects) {
            showFragment(subjectFragment, R.string.menu_subjects);
        } else if (id == R.id.activity_main_bottom_navigation_substitutions) {
            showFragment(substitutionFragment, R.string.menu_substitutions);
        } else if (id == R.id.activity_main_bottom_navigation_time_table) {
            showFragment(timeTableFragment, R.string.menu_time_table);
        } else if (id == R.id.activity_main_bottom_navigation_dashboard) {
            showFragment(dashboardFragment, R.string.menu_dashboard);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        select(item.getItemId());
        return true;
    }
}