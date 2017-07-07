package test.dm.applicationtemplate;

import dm.applicationtemplate.model.Country;
import dm.applicationtemplate.model.Partner;
import dm.applicationtemplate.service.EvaluationService;
import dm.applicationtemplate.service.HubspotRequestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by David on 04/07/2017.
 */
public class ApplicationTest extends BaseSpringBootTest {

    @Autowired
    private HubspotRequestService hubspotRequestService;

    @Autowired
    private EvaluationService evaluationService;

    @Test
    public void applicationTest() throws IOException {

        List<Partner> partners = hubspotRequestService.retrievePartners();

        List<Country> countries = evaluationService.evaluate(partners);

        boolean success = hubspotRequestService.postResults(countries);

        assertTrue("Post should be successful", success);

    }

}
