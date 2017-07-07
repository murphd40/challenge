package dm.applicationtemplate.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by David on 06/07/2017.
 */
@Data
public class Partner {

    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private List<Date> availableDates;

}
