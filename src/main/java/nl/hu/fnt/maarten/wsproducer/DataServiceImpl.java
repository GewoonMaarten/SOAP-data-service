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

    @Override()
    public Response postData(PostRequest postRequest) throws Fault {

        ObjectFactory factory = new ObjectFactory();
        Response response = factory.createResponse();
        Message message = factory.createMessage();

        DataDAO dataDAO = DataDAO.getInstance();

        try {

            String token = (postRequest.getToken() == null) ? tokenGenerator() : postRequest.getToken();

            dataDAO.insertData(token, postRequest.getData());

            message.setMessage("Succefully added data to Database.");

            response.setMessage(message);
            response.setToken(token);
        } catch (RuntimeException  e){
            e.printStackTrace();
            DataFault dataFault = factory.createDataFault();

            dataFault.setErrorCode((short)1);
            dataFault.setMessage("Could not insert data into database!");

            throw new Fault("Oops, something went wrong :(" , dataFault);
        }

        return response;
    }

    @Override()
    public Response getData(GetRequest getRequest) throws Fault {

        ObjectFactory factory = new ObjectFactory();
        Response response = factory.createResponse();
        Message message = factory.createMessage();

        DataDAO dataDAO = DataDAO.getInstance();

        try {
            DBCursor cursor = dataDAO.getDataByToken(getRequest.getToken());

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
            DataFault dataFault = factory.createDataFault();

            dataFault.setErrorCode((short)2);
            dataFault.setMessage("Could not get data from database!");

            throw new Fault("Oops, something went wrong :(" , dataFault);
        }
        return response;
    }

    private String tokenGenerator(){
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

}
