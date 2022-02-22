package markus.wieland.huelssegymnasiumapp;

import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.defaultappelements.uielements.activities.DefaultActivity;
import markus.wieland.huelssegymnasiumapp.grades.GradeType;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;
import markus.wieland.huelssegymnasiumapp.ui.enum_input_widget.EnumInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.subject_input_widget.SubjectInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.weighting_input_widget.WeightingInputWidget;

public class MainActivity extends DefaultActivity {

    private BottomNavigationView bottomNavigationView;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    public void bindViews() {
        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation_view);
    }

    @Override
    public void initializeViews() {

        startActivity(new Intent(this, CreateSubjectActivity.class));
    }

    @Override
    public void execute() {

    }
}