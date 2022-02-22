package markus.wieland.huelssegymnasiumapp;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import markus.wieland.defaultappelements.uielements.activities.DefaultActivity;
import markus.wieland.huelssegymnasiumapp.ui.color_input_widget.Color;
import markus.wieland.huelssegymnasiumapp.ui.color_input_widget.ColorInputWidget;

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
        ColorInputWidget colorInputWidget = findViewById(R.id.test_widget);
        colorInputWidget.setValue(new Color(255,0,9));
    }

    @Override
    public void execute() {

    }
}