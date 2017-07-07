package dm.applicationtemplate.model;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * Created by David on 06/07/2017.
 */
@Data
public class CountryBatch {

    @NonNull
    private List<Country> countries;

}
