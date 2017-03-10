package nl.hu.fnt.maarten.dataAccess;

import com.mongodb.*;
import java.util.List;

public class DataDAO {
    private static DataDAO instance = null;

    private DataDAO(){}

    public static DataDAO getInstance(){
        if(instance == null){
            instance = new DataDAO();
        }
        return instance;
    }

    public void insertData(String token, List data){
        DB database = DatabaseConnection.getInstance().getDatabase();
        DBCollection collection = database.getCollection("users");

        if(getDataByToken(token).one() == null){
            BasicDBObject userData = new BasicDBObject("_id", token);
            userData.append("data", data);
            collection.insert(userData);
        } else {
            for (Object object : data){
                DBObject userData = new BasicDBObject().append("$push", new  BasicDBObject("data", object));
                DBObject query = new BasicDBObject().append("_id", token);
                collection.update(query, userData);
            }
        }

    }

    public DBCursor getDataByToken(String token){
        DB database = DatabaseConnection.getInstance().getDatabase();
        DBCollection collection = database.getCollection("users");
        DBObject query = new BasicDBObject().append("_id", token);
        return collection.find(query);
    }

}
