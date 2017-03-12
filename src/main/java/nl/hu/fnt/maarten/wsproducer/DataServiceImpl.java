package nl.hu.fnt.maarten.wsproducer;

import com.mongodb.DBCursor;
import nl.hu.fnt.maarten.dataAccess.DataDAO;
import nl.hu.fnt.maarten.wsInterface.*;

import javax.jws.WebService;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

@WebService( endpointInterface = "nl.hu.fnt.maarten.wsInterface.DataServiceInterface")
public class DataServiceImpl implements DataServiceInterface {
    private ObjectFactory factory = new ObjectFactory();
    private Response response = factory.createResponse();
    private Message message = factory.createMessage();
    private DataDAO dataDAO = DataDAO.getInstance();

    @Override()
    public Response postData(PostRequest postRequest) throws Fault {

        try {
            String token = (postRequest.getToken() == null) ? tokenGenerator() : postRequest.getToken();

            dataDAO.insertData(token, postRequest.getData());

            message.setMessage("Succefully added data to Database.");
            message.getData().clear();
            response.setMessage(message);
            response.setToken(token);
        } catch (RuntimeException  e){
            throwFault((short)1,"Could not insert data into database!");
        }

        return response;
    }

    @Override()
    public Response getData(GetRequest getRequest) throws Fault {

        try {
            DBCursor cursor = dataDAO.getDataByToken(getRequest.getToken());

            if (!cursor.hasNext()){
                throwFault((short) 3, "No data found with this token!");
            }

            while(cursor.hasNext()){
                List list = (List)cursor.next().get("data");

                for (Object object : list){
                    message.getData().add((String)object);
                }
            }
            message.setMessage("Got following data: ");
            response.setMessage(message);
            response.setToken(getRequest.getToken());

        } catch (RuntimeException e){
            throwFault((short)2, "Could not get data from database!");
        }
        return response;
    }

    private String tokenGenerator(){
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    private void throwFault(short errorcode, String message) throws Fault{
        DataFault dataFault = factory.createDataFault();

        dataFault.setErrorCode(errorcode);
        dataFault.setMessage(message);

        throw new Fault("Oops, something went wrong :(" , dataFault);
    }

}
