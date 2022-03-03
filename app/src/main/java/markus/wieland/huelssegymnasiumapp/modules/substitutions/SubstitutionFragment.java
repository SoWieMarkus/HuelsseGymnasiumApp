package markus.wieland.huelssegymnasiumapp.modules.substitutions;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.api.models.Substitution;
import markus.wieland.huelssegymnasiumapp.modules.settings.Settings;
import markus.wieland.huelssegymnasiumapp.ui.ListFragment;
import markus.wieland.huelssegymnasiumapp.ui.StateRecyclerView;

public class SubstitutionFragment extends ListFragment<Substitution, SubstitutionAdapter.SubstitutionViewHolder, SubstitutionAdapter> {

    public SubstitutionFragment() {
        super(R.layout.fragment_substitutions);
        setHasOptionsMenu(true);
        setAdapter(createAdapter());
    }

    @Override
    public void execute() {
        if (getActivity() == null) return;
        Settings settings = new Settings(getActivity());
        getAdapter().setCourse(settings.getCourse());
        getRecyclerView().setState(StateRecyclerView.State.LIST);
    }

    @Override
    public void initializeViews() {
        getRecyclerView().setAdapter(getAdapter());
    }

    @Override
    public int getRecyclerViewId() {
        return R.id.fragment_substitutions_recycler_view;
    }

    @Override
    public SubstitutionAdapter createAdapter() {
        return new SubstitutionAdapter();
    }

}
