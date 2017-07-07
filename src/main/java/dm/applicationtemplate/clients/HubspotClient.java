package dm.applicationtemplate.clients;

import dm.applicationtemplate.model.CountryBatch;
import dm.applicationtemplate.model.PartnerBatch;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by David on 04/07/2017.
 */
public interface HubspotClient {

    @GET("candidateTest/v2/partners")
    Call<PartnerBatch> getPartners(@Query("userKey") String userKey);

    @POST("candidateTest/v2/results")
    Call<Void> postResults(@Query("userKey") String userKey, @Body CountryBatch body);

}
