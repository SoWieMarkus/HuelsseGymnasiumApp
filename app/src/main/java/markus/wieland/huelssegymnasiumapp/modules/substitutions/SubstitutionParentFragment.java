package markus.wieland.huelssegymnasiumapp.modules.substitutions;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

import markus.wieland.defaultappelements.api.APIResult;
import markus.wieland.defaultappelements.uielements.fragments.DefaultFragment;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.api.SubstitutionAPI;
import markus.wieland.huelssegymnasiumapp.api.models.SubstitutionDashboard;
import markus.wieland.huelssegymnasiumapp.ui.StateRecyclerView;

public class SubstitutionParentFragment extends DefaultFragment implements APIResult<SubstitutionDashboard> {

    private SubstitutionAPI substitutionAPI;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;

    private LinearLayout data;
    private LinearLayout empty;
    private ProgressBar loading;

    public SubstitutionParentFragment() {
        super(R.layout.fragment_substitution_parent);
        setHasOptionsMenu(true);
    }

    @Override
    public void bindViews() {
        data = findViewById(R.id.fragment_substitution_data);
        empty = findViewById(R.id.fragment_substitution_empty);
        loading = findViewById(R.id.fragment_substitution_loading);
        viewPager2 = findViewById(R.id.fragment_substitution_view_pager);
        tabLayout = findViewById(R.id.fragment_substitution_tab_layout);
    }

    @Override
    public void initializeViews() {
        setState(StateRecyclerView.State.LOADING);
    }

    @Override
    public void execute() {
        if (getActivity() == null) return;
        substitutionAPI = new SubstitutionAPI(getActivity());
        load();
    }

    private void load(){
        setState(StateRecyclerView.State.LOADING);
        substitutionAPI.querySubstitutionDashboard(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_substitution, menu);
    }

    private void setState(StateRecyclerView.State state) {
        loading.setVisibility(state == StateRecyclerView.State.LOADING ? View.VISIBLE : View.GONE);
        empty.setVisibility(state == StateRecyclerView.State.EMPTY ? View.VISIBLE : View.GONE);
        data.setVisibility(state == StateRecyclerView.State.LIST ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_substitution_refresh) {
            load();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoad(SubstitutionDashboard substitutionDashboard) {
        if (getActivity() == null) return;
        if (substitutionDashboard == null) {
            setState(StateRecyclerView.State.EMPTY);
            return;
        }
        setState(StateRecyclerView.State.LIST);

        SubstitutionFragment latest = new SubstitutionFragment();
        SubstitutionFragment secondLatest = new SubstitutionFragment();

        latest.getAdapter().submitList(substitutionDashboard.getLatest().getSubstitutions());
        secondLatest.getAdapter().submitList(substitutionDashboard.getSecondLatest().getSubstitutions());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity(), latest, secondLatest);
        viewPager2.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            if (position == SubstitutionDashboard.SECOND_LATEST_INDEX)
                tab.setText(substitutionDashboard.getSecondLatest().toString(getContext()));
            if (position == SubstitutionDashboard.LATEST_INDEX){
                tab.setText(substitutionDashboard.getLatest().toString(getContext()));
            }
        }).attach();



    }

}
