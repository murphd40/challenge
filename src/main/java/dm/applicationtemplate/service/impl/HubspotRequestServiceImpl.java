package dm.applicationtemplate.service.impl;

import dm.applicationtemplate.clients.HubspotClient;
import dm.applicationtemplate.clients.configuration.HubspotClientProperties;
import dm.applicationtemplate.model.Country;
import dm.applicationtemplate.model.CountryBatch;
import dm.applicationtemplate.model.Partner;
import dm.applicationtemplate.model.PartnerBatch;
import dm.applicationtemplate.service.HubspotRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by David on 07/07/2017.
 */
@Service
@Slf4j
public class HubspotRequestServiceImpl implements HubspotRequestService {

    @Autowired
    private HubspotClientProperties hubspotClientProperties;

    @Autowired
    private HubspotClient hubspotClient;

    @Override
    public List<Partner> retrievePartners() {
        Call<PartnerBatch> call = hubspotClient.getPartners(hubspotClientProperties.getApiKey());

        List<Partner> partners = Collections.emptyList();

        try {
            Response<PartnerBatch> response = call.execute();

            partners = Optional.ofNullable(response.body()).map(PartnerBatch::getPartners)
                    .orElse(Collections.emptyList());

        } catch (Exception e) {
            log.error("Failed to retrieve partners.", e);
        }

        return partners;
    }

    @Override
    public boolean postResults(List<Country> response) {
        Call<Void> call = hubspotClient.postResults(hubspotClientProperties.getApiKey(), new CountryBatch(response));

        boolean success = false;

        try {
            success = call.execute().isSuccessful();
        } catch (Exception e) {
            log.error("Failed to post results.", e);
        }

        return success;
    }
}
