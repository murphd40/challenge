package test.dm.applicationtemplate;

import dm.applicationtemplate.clients.JsonPlaceholderClient;
import dm.applicationtemplate.model.response.Post;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by David on 04/07/2017.
 */
public class JsonPlaceholderClientTest extends BaseSpringBootTest {

    @Autowired
    private JsonPlaceholderClient jsonPlaceholderClient;

    @Test
    public void jsonPlaceholderClientTest() throws IOException {
        Call<Post> call = jsonPlaceholderClient.getPost("1");

        Response<Post> response = call.execute();

        assertTrue(response.isSuccessful());

        assertThat(response.body().getId(), is("1"));

        System.out.println(response.body().toString());
    }

}
