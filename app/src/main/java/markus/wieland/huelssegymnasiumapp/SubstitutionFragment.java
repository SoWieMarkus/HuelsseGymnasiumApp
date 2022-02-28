package markus.wieland.huelssegymnasiumapp;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import markus.wieland.defaultappelements.api.APIResult;
import markus.wieland.huelssegymnasiumapp.api.SubstitutionAPI;
import markus.wieland.huelssegymnasiumapp.api.models.Substitution;
import markus.wieland.huelssegymnasiumapp.api.models.SubstitutionPlan;
import markus.wieland.huelssegymnasiumapp.substitutions.SubstitutionAdapter;

public class SubstitutionFragment extends ListFragment<Substitution, SubstitutionAdapter.SubstitutionViewHolder, SubstitutionAdapter>
        implements APIResult<SubstitutionPlan> {

    private SubstitutionAPI substitutionAPI;
    private Settings settings;

    public SubstitutionFragment() {
        super(R.layout.fragment_substitutions);
        setHasOptionsMenu(true);
    }

    @Override
    public void execute() {
        if (getActivity() == null) return;
        substitutionAPI = new SubstitutionAPI(getActivity());
        settings = new Settings(getActivity());
        load();
    }

    private void load() {
        getRecyclerView().setState(StateRecyclerView.State.LOADING);
        substitutionAPI.queryLatest(this);
    }

    @Override
    public int getRecyclerViewId() {
        return R.id.fragment_substitutions_recycler_view;
    }

    @Override
    public SubstitutionAdapter createAdapter() {
        return new SubstitutionAdapter();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_substitution, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_substitution_refresh) {
            load();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoad(SubstitutionPlan substitutionPlan) {
        getRecyclerView().getAdapter().setCourse(settings.getCourse());
        if (substitutionPlan == null) {
            getRecyclerView().setState(StateRecyclerView.State.EMPTY);
            return;
        }
        submitList(substitutionPlan.getSubstitutions());
    }
}
