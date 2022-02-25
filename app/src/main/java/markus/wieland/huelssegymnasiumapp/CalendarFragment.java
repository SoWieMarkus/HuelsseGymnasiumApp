package markus.wieland.huelssegymnasiumapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntryWithSubject;
import markus.wieland.huelssegymnasiumapp.database.entities.calendar.CalendarViewModel;

public class CalendarFragment extends ListFragment<CalendarEntryWithSubject, CalendarAdapter.CalendarViewHolder, CalendarAdapter>
        implements OnItemClickListener<CalendarEntry>, Observer<List<CalendarEntryWithSubject>> {

    private CalendarViewModel calendarViewModel;

    public CalendarFragment() {
        super(R.layout.fragment_calendar);
        setHasOptionsMenu(true);
    }

    @Override
    public int getRecyclerViewId() {
        return R.id.fragment_calendar_recycler_view;
    }

    @Override
    public void bindViews() {
        super.bindViews();
        calendarViewModel = ViewModelProviders.of(getActivity()).get(CalendarViewModel.class);
    }

    @Override
    public CalendarAdapter createAdapter() {
        return new CalendarAdapter(null);
    }

    @Override
    public void execute() {
        calendarViewModel.getCalendarEntryWithSubjects().observe(this, this);
    }

    @Override
    public void onClick(CalendarEntry calendarEntry) {
        startActivity(new Intent(getActivity(), CreateItemActivity.class).putExtra(CreateItemActivity.OBJECT_TO_EDIT, calendarEntry));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add_item_add) {
            startActivity(new Intent(getActivity(), CreateCalendarEntryActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChanged(List<CalendarEntryWithSubject> calendarEntries) {
        getAdapter().submitList(calendarEntries);
    }
}
