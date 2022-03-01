package markus.wieland.huelssegymnasiumapp.modules.calendar;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.calendar.database.CalendarViewModel;
import markus.wieland.huelssegymnasiumapp.modules.calendar.models.CalendarEntryWithSubject;
import markus.wieland.huelssegymnasiumapp.ui.CreateItemActivity;
import markus.wieland.huelssegymnasiumapp.ui.ListFragment;
import markus.wieland.huelssegymnasiumapp.ui.OnCalendarContextMenu;

public class CalendarFragment extends ListFragment<CalendarEntryWithSubject, CalendarWithSubjectAdapter.CalendarViewHolder, CalendarWithSubjectAdapter>
        implements Observer<List<CalendarEntryWithSubject>>{

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
        if (getActivity() == null) return;
        calendarViewModel = ViewModelProviders.of(getActivity()).get(CalendarViewModel.class);
    }

    @Override
    public CalendarWithSubjectAdapter createAdapter() {
        return new CalendarWithSubjectAdapter(new CalendarInteractionListener(getActivity(), calendarViewModel));
    }

    @Override
    public void execute() {
        calendarViewModel.getCalendarEntryWithSubjects().observe(this, this);
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
        submitList(calendarEntries);
    }

}
