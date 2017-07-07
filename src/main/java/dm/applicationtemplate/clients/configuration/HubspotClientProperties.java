package dm.applicationtemplate.clients.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by David on 04/07/2017.
 */
@Data
@ConfigurationProperties(prefix = "clients.hubspot")
public class HubspotClientProperties {

    private String baseUrl;
    private String apiKey;

}
