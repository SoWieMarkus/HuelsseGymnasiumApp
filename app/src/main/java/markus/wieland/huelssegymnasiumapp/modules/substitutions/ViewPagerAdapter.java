package markus.wieland.huelssegymnasiumapp.modules.substitutions;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import markus.wieland.huelssegymnasiumapp.api.models.SubstitutionDashboard;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final SubstitutionFragment latest;
    private final SubstitutionFragment secondLatest;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, SubstitutionFragment latest, SubstitutionFragment secondLatest) {
        super(fragmentActivity);
        this.latest = latest;
        this.secondLatest = secondLatest;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == SubstitutionDashboard.SECOND_LATEST_INDEX) return secondLatest;
        return latest;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
