package markus.wieland.huelssegymnasiumapp.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubstitutionDashboard {

    public static final int LATEST_INDEX = 0;
    public static final int SECOND_LATEST_INDEX = 1;

    private SubstitutionPlan latest;
    private SubstitutionPlan secondLatest;

}
