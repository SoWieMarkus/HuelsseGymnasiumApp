package markus.wieland.huelssegymnasiumapp.modules.subjects;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.settings.Settings;
import markus.wieland.huelssegymnasiumapp.modules.subjects.database.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.SubjectWithGradesAndCalendar;
import markus.wieland.huelssegymnasiumapp.ui.AverageView;
import markus.wieland.huelssegymnasiumapp.ui.CreateItemActivity;
import markus.wieland.huelssegymnasiumapp.ui.ListFragment;
import markus.wieland.huelssegymnasiumapp.ui.OnContextMenuListener;

public class SubjectFragment extends ListFragment<SubjectWithGradesAndCalendar, SubjectAdapter.SubjectViewHolder, SubjectAdapter>
        implements Observer<List<SubjectWithGradesAndCalendar>>, OnContextMenuListener<SubjectWithGradesAndCalendar> {

    private SubjectViewModel subjectViewModel;
    private Settings settings;
    private AverageView averageView;

    public SubjectFragment() {
        super(R.layout.fragment_subjects);
        setHasOptionsMenu(true);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        if (getActivity() == null) return;
        subjectViewModel = ViewModelProviders.of(getActivity()).get(SubjectViewModel.class);
        settings = new Settings(getActivity());
        averageView = findViewById(R.id.fragment_subjects_average);
        averageView.update(null);
    }


    @Override
    public int getRecyclerViewId() {
        return R.id.fragment_subjects_recycler_view;
    }

    @Override
    public SubjectAdapter createAdapter() {
        return new SubjectAdapter(this);
    }

    @Override
    public void onClick(SubjectWithGradesAndCalendar subject) {
        startActivity(new Intent(getActivity(), SubjectDetailActivity.class)
                .putExtra(SubjectDetailActivity.SUBJECT_ID, subject.getSubject().getSubjectId()));
    }

    @Override
    public void execute() {
        subjectViewModel.getAllSubjectsWithGradesAndEvents().observe(this, this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add_item_add) {
            startActivity(new Intent(getActivity(), CreateSubjectActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChanged(List<SubjectWithGradesAndCalendar> subjects) {
        getAdapter().setGradeFormat(settings.getGradeFormat());
        submitList(subjects);
        averageView.calculateAverage(subjects);
    }

    @Override
    public void onEdit(SubjectWithGradesAndCalendar subjectWithGradesAndCalendar) {
        startActivity(new Intent(getActivity(), CreateSubjectActivity.class)
                .putExtra(CreateItemActivity.OBJECT_TO_EDIT, subjectWithGradesAndCalendar.getSubject()));
    }

    @Override
    public void onDelete(SubjectWithGradesAndCalendar subjectWithGradesAndCalendar) {
        subjectViewModel.delete(subjectWithGradesAndCalendar.getSubject());
    }
}
