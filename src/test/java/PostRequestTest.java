import nl.hu.fnt.maarten.wsInterface.*;
import nl.hu.fnt.maarten.wsproducer.DataServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class PostRequestTest {
    private GetRequest getRequest = new GetRequest();
    private PostRequest postRequest = new PostRequest();
    private DataServiceImpl dataService = new DataServiceImpl();
    private List<String> data = new ArrayList<String>();
    private Response response = new Response();
    private Message message = new Message();


    /*
    * Clean data from TestToken before executing test!
    * */
    @Test
    public void testGetData(){
        /*
        * Prepare first data insertion
        * */
        postRequest.setToken("TestToken");
        postRequest.getData().add("test data");


        /*
        * Prepare test data
        * */
        List<String> testData = new ArrayList<String>();
        testData.add("test data");

        /*
        * Prepare test get request
        * */
        getRequest.setToken("TestToken");

        try {
            dataService.postData(postRequest);
            assertEquals(testData, dataService.getData(getRequest).getMessage().getData());
        } catch (Fault fault){
            fail();
        }
    }
}
