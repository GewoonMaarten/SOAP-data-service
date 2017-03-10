package nl.hu.fnt.maarten.dataAccess;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    private MongoClient mongoClient;
    private DB database;


    private DatabaseConnection(){
        try {
            mongoClient = new MongoClient(new MongoClientURI("mongodb://192.168.99.100:27017"));
            database = mongoClient.getDB("DataService");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if(instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public DB getDatabase() {
        return database;
    }

    public void setDatabase(DB database) {
        this.database = database;
    }
}
