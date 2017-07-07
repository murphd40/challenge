package test.dm.applicationtemplate.service;

import com.google.gson.Gson;
import dm.applicationtemplate.model.Country;
import dm.applicationtemplate.model.CountryBatch;
import dm.applicationtemplate.model.PartnerBatch;
import dm.applicationtemplate.service.EvaluationService;
import dm.applicationtemplate.service.impl.EvaluationServiceImpl;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by David on 06/07/2017.
 */
public class EvaluationServiceTest {

    private PartnerBatch partnerBatch;
    private CountryBatch countryBatch;

    private EvaluationService evaluationService = new EvaluationServiceImpl();

    @Before
    public void init() throws IOException {

        ResourceLoader resourceLoader = new DefaultResourceLoader();

        File partnersFile = resourceLoader.getResource("classpath:test-partners.json").getFile();
        String partnersJson = IOUtils.toString(partnersFile.toURI(), Charset.defaultCharset());
        partnerBatch = new Gson().fromJson(partnersJson, PartnerBatch.class);

        File countriesFile = resourceLoader.getResource("classpath:test-countries.json").getFile();
        String countriesJson = IOUtils.toString(countriesFile.toURI(), Charset.defaultCharset());
        countryBatch = new Gson().fromJson(countriesJson, CountryBatch.class);

    }

    @Test
    public void evaluationServiceTest() {
        List<Country> countries = evaluationService.evaluate(partnerBatch.getPartners());

        assertThat(countries.size(), is(countryBatch.getCountries().size()));
        assertTrue(countries.containsAll(countryBatch.getCountries()));
    }

}
