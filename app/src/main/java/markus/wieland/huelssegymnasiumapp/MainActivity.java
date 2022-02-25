package markus.wieland.huelssegymnasiumapp;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

import markus.wieland.defaultappelements.uielements.activities.DefaultActivity;

public class MainActivity extends DefaultActivity implements NavigationBarView.OnItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    private SubjectFragment subjectFragment;
    private CalendarFragment calendarFragment;
    private TimeTableFragment timeTableFragment;
    private SubstitutionFragment substitutionFragment;

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
    }

    @Override
    public void initializeViews() {
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    @Override
    public void execute() {
        bottomNavigationView.setSelectedItemId(R.id.activity_main_bottom_navigation_subjects);
    }

    private void showFragment(Fragment fragment, @StringRes int title) {
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).commit();
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_main_bottom_navigation_calendar) {
            showFragment(calendarFragment, R.string.menu_calendar);
        } else if (item.getItemId() == R.id.activity_main_bottom_navigation_subjects) {
            showFragment(subjectFragment, R.string.menu_subjects);
        } else if (item.getItemId() == R.id.activity_main_bottom_navigation_substitutions) {
            showFragment(substitutionFragment, R.string.menu_substitutions);
        } else if (item.getItemId() == R.id.activity_main_bottom_navigation_time_table) {
            showFragment(timeTableFragment, R.string.menu_time_table);
        }

        return true;
    }
}