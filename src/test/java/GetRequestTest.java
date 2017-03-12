import nl.hu.fnt.maarten.wsInterface.Fault;
import nl.hu.fnt.maarten.wsInterface.Message;
import nl.hu.fnt.maarten.wsInterface.PostRequest;
import nl.hu.fnt.maarten.wsInterface.Response;
import nl.hu.fnt.maarten.wsproducer.DataServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GetRequestTest {
    private PostRequest postRequest = new PostRequest();
    private DataServiceImpl dataService = new DataServiceImpl();
    private List<String> data = new ArrayList<String>();
    private Response response = new Response();
    private Message message = new Message();

    @Test
    public void testPostData(){
        /*
        * Prepare request
        * */
        postRequest.setToken("TestToken");
        postRequest.getData().add("test data");


        /*
        * Prepare response
        * */
        message.setMessage("Succefully added data to Database.");
        response.setMessage(message);

        try {
            assertEquals(response.getMessage().getMessage(), dataService.postData(postRequest).getMessage().getMessage());
        } catch (Fault fault) {
            fail();
        }
    }
}
