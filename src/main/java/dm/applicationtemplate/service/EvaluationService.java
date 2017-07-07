package dm.applicationtemplate.service;

import dm.applicationtemplate.model.Country;
import dm.applicationtemplate.model.Partner;

import java.util.List;

/**
 * Created by David on 06/07/2017.
 */
public interface EvaluationService {

    List<Country> evaluate(List<Partner> partners);

}
