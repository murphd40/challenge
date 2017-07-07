package dm.applicationtemplate.clients.configuration;

import dm.applicationtemplate.clients.HubspotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by David on 04/07/2017.
 */
@Configuration
@EnableConfigurationProperties(HubspotClientProperties.class)
public class RetrofitClientConfiguration {

    @Autowired
    private HubspotClientProperties hubspotClientProperties;

    @Bean
    public HubspotClient jsonPlaceholderClient() {
        return new Retrofit.Builder()
                .baseUrl(hubspotClientProperties.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HubspotClient.class);
    }

}
