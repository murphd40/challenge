package dm.applicationtemplate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * Created by David on 06/07/2017.
 */
@Data
@AllArgsConstructor
public class Country {

    private int attendeeCount;
    private Set<String> attendees;
    private String name;
    private String startDate;

}
