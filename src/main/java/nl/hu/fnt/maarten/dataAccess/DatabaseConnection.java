package nl.hu.fnt.maarten.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    private Connection connection = null;


    private DatabaseConnection(){}

    public static DatabaseConnection getInstance() {
        if(instance == null) {
            instance = new DatabaseConnection();
            instance.setConnection();
        }
        return instance;
    }

    private void setConnection(){
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:dataServiceDB.db");
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
