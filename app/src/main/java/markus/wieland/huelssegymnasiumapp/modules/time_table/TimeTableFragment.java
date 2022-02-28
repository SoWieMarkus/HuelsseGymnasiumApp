package markus.wieland.huelssegymnasiumapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;
import markus.wieland.defaultappelements.uielements.fragments.DefaultFragment;
import markus.wieland.huelssegymnasiumapp.database.entities.time_table.TimeTableSlotViewModel;
import markus.wieland.huelssegymnasiumapp.time_table.TimeTable;
import markus.wieland.huelssegymnasiumapp.time_table.TimeTableSlotWithSubject;
import markus.wieland.huelssegymnasiumapp.ui.time_table.TimeTableBackgroundView;
import markus.wieland.huelssegymnasiumapp.ui.time_table.TimeTableView;

public class TimeTableFragment extends DefaultFragment implements Observer<List<TimeTableSlotWithSubject>>, OnItemClickListener<TimeTableSlotWithSubject> {

    private TimeTableSlotViewModel timeTableSlotViewModel;
    private TimeTableView timeTableView;
    private TimeTableBackgroundView timeTableBackgroundView;

    public TimeTableFragment() {
        super(R.layout.fragment_time_table);
        setHasOptionsMenu(true);
    }

    @Override
    public void bindViews() {
        timeTableSlotViewModel = ViewModelProviders.of(this).get(TimeTableSlotViewModel.class);
        timeTableView = findViewById(R.id.fragment_time_table_time_table_view);
        timeTableBackgroundView = findViewById(R.id.fragment_time_table_time_background_view);
    }

    @Override
    public void initializeViews() {

    }

    @Override
    public void execute() {
        timeTableSlotViewModel.getAllTimeTableSlots().observe(this, this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add_item_add) {
            startActivity(new Intent(getActivity(), CreateTimeTableSlotActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChanged(List<TimeTableSlotWithSubject> timeTableSlotWithSubjects) {
        int height = findViewById(R.id.fragment_time_table_scroll_view).getHeight();
        TimeTable timeTable = new TimeTable(timeTableSlotWithSubjects);
        timeTableView.setTimeTableSlots(this, timeTable, height);
        timeTableBackgroundView.drawTimeTable(timeTable, height);
    }

    @Override
    public void onClick(TimeTableSlotWithSubject timeTableSlotWithSubject) {
        Toast.makeText(getActivity(), timeTableSlotWithSubject.getSubject().getName(), Toast.LENGTH_SHORT).show();
    }
}
