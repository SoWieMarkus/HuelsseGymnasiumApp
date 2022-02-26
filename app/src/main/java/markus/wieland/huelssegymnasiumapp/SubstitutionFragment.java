package markus.wieland.huelssegymnasiumapp;

import markus.wieland.defaultappelements.api.APIResult;
import markus.wieland.huelssegymnasiumapp.api.SubstitutionAPI;
import markus.wieland.huelssegymnasiumapp.api.models.Substitution;
import markus.wieland.huelssegymnasiumapp.api.models.SubstitutionPlan;
import markus.wieland.huelssegymnasiumapp.substitutions.SubstitutionAdapter;

public class SubstitutionFragment extends ListFragment<Substitution, SubstitutionAdapter.SubstitutionViewHolder, SubstitutionAdapter>
        implements APIResult<SubstitutionPlan> {

    // TODO RefreshLayout

    private SubstitutionAPI substitutionAPI;

    public SubstitutionFragment() {
        super(R.layout.fragment_substitutions);
        setHasOptionsMenu(true);
    }

    @Override
    public void execute() {
        if (getActivity() == null) return;
        substitutionAPI = new SubstitutionAPI(getActivity());
        load();

    }

    private void load(){
        getRecyclerView().setState(StateRecyclerView.State.LOADING);
        substitutionAPI.queryLatest(this);
    }

    @Override
    public int getRecyclerViewId() {
        return R.id.fragment_substitutions_recycler_view;
    }

    @Override
    public SubstitutionAdapter createAdapter() {
        return new SubstitutionAdapter(null);
    }

    @Override
    public void onLoad(SubstitutionPlan substitutionPlan) {
        submitList(substitutionPlan.getSubstitutions());
    }

}
