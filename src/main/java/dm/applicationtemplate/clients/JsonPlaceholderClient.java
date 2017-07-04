package dm.applicationtemplate.clients;

import dm.applicationtemplate.model.response.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by David on 04/07/2017.
 */
public interface JsonPlaceholderClient {

    @GET("posts/{postId}")
    Call<Post> getPost(@Path("postId") String postId);

}
