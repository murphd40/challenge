package dm.applicationtemplate.service;

import dm.applicationtemplate.model.Country;
import dm.applicationtemplate.model.Partner;

import java.util.List;

/**
 * Created by David on 07/07/2017.
 */
public interface HubspotRequestService {

    List<Partner> retrievePartners();

    boolean postResults(List<Country> response);

}
