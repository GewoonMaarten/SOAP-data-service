package nl.hu.fnt.maarten.wsproducer;

import nl.hu.fnt.maarten.dataAccess.DataDAO;
import nl.hu.fnt.maarten.dataAccess.UserDAO;
import nl.hu.fnt.maarten.domain.Data;
import nl.hu.fnt.maarten.domain.User;
import nl.hu.fnt.maarten.wsInterface.*;

import javax.jws.WebService;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@WebService( endpointInterface = "nl.hu.fnt.maarten.wsInterface.DataServiceInterface")
public class DataServiceImpl implements DataServiceInterface {

    @Override()
    public Response postData(PostRequest postRequest) throws Fault {

        ObjectFactory factory = new ObjectFactory();
        Response response = factory.createResponse();
        Message message = factory.createMessage();

        UserDAO userDAO = UserDAO.getInstance();
        DataDAO dataDAO = DataDAO.getInstance();

        try {

            User user = null;
            String token;

            if (postRequest.getToken() == null){;
                token = tokenGenerator();
                user = new User(token);
                userDAO.insertUser(user);
            } else {
                token = postRequest.getToken();
                userDAO.getUserByToken(token);
            }

            List<Object> datas = postRequest.getData();
            for (Object obj : datas){
                dataDAO.insetDataByUser(new Data(obj, user), user);
            }

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

        UserDAO userDAO = UserDAO.getInstance();
        DataDAO dataDAO = DataDAO.getInstance();

        try {
            String token = getRequest.getToken();
            User user = userDAO.getUserByToken(token);

            ArrayList<Data> datas = dataDAO.getDataByUser(user);

            message.setMessage("Got the following data");

            for (Data data : datas){
                message.getData().add(data.getValue());
            }

            response.setMessage(message);
            response.setToken(token);

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
