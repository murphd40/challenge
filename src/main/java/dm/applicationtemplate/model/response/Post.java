package dm.applicationtemplate.model.response;

import lombok.Data;

/**
 * Created by David on 04/07/2017.
 */
@Data
public class Post {

    private String userId;
    private String id;
    private String title;
    private String body;

}
