package dm.applicationtemplate;

import dm.applicationtemplate.model.Country;
import dm.applicationtemplate.model.Partner;
import dm.applicationtemplate.service.EvaluationService;
import dm.applicationtemplate.service.HubspotRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

/**
 * Created by David on 04/07/2017.
 */

@Slf4j
@SpringBootApplication
public class Application {

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private HubspotRequestService hubspotRequestService;

    @EventListener(classes = ApplicationReadyEvent.class)
    public void onApplicationStart() {

        List<Partner> partners = hubspotRequestService.retrievePartners();

        List<Country> countries = evaluationService.evaluate(partners);

        boolean success = hubspotRequestService.postResults(countries);

        if (success) {
            log.info("Evaluation Successful!");
        } else {
            log.info("Evaluation Unsuccessful!");
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
