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
import markus.wieland.defaultappelements.uielements.fragments.DefaultFragment;
import markus.wieland.huelssegymnasiumapp.database.entities.subject.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;

public class SubjectFragment extends ListFragment<Subject, SubjectAdapter.SubjectViewHolder, SubjectAdapter>
        implements OnItemClickListener<Subject>, Observer<List<Subject>> {

    private SubjectViewModel subjectViewModel;

    public SubjectFragment() {
        super(R.layout.fragment_subjects);
        setHasOptionsMenu(true);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        subjectViewModel = ViewModelProviders.of(getActivity()).get(SubjectViewModel.class);
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
    public void onClick(Subject subject) {
        startActivity(new Intent(getActivity(), SubjectDetailActivity.class).putExtra(SubjectDetailActivity.SUBJECT_ID, subject.getSubjectId()));
    }

    @Override
    public void execute() {
        subjectViewModel.getAllSubjects().observe(this,this);
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
    public void onChanged(List<Subject> subjects) {
        getAdapter().submitList(subjects);
    }
}
