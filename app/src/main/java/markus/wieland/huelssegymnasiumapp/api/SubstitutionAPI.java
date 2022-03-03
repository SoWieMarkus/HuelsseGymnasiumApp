package markus.wieland.huelssegymnasiumapp.api;

import android.app.Activity;

import markus.wieland.defaultappelements.api.API;
import markus.wieland.defaultappelements.api.APIResult;
import markus.wieland.defaultappelements.api.GetRequest;
import markus.wieland.defaultappelements.api.RequestResultListener;
import markus.wieland.huelssegymnasiumapp.api.models.SubstitutionDashboard;

public class SubstitutionAPI extends API {

    private static final String BASE_URL = "https://api.sowiemarkus.com/substitution";

    public SubstitutionAPI(Activity context) {
        super(context);
    }

    public void querySubstitutionDashboard(APIResult<SubstitutionDashboard> result) {
        String url = BASE_URL + "/last";
        GetRequest<SubstitutionDashboard> routesGetRequest = new GetRequest<>(SubstitutionDashboard.class, url, new RequestResultListener<SubstitutionDashboard>() {
            @Override
            public void onLoad(SubstitutionDashboard response) {
                notifyClient(response, result);
            }

            @Override
            public void onError(Exception e) {
                notifyClient(null, result);
            }
        });
        routesGetRequest.execute();
    }
}
