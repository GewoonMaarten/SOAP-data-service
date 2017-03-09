package nl.hu.fnt.maarten.dataAccess;

import nl.hu.fnt.maarten.domain.Data;
import nl.hu.fnt.maarten.domain.User;

import java.sql.*;
import java.util.ArrayList;

public class DataDAO {
    private static DataDAO instance = null;

    private DataDAO(){}

    public static DataDAO getInstance(){
        if(instance == null){
            instance = new DataDAO();
        }
        return instance;
    }
    public ArrayList<Data> getDataByUser(User user){
        ArrayList<Data> datas = new ArrayList<>();

        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement statement;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM 'Data' WHERE userId = "+user.getUserId());
            while (resultSet.next()){
                Blob value = resultSet.getBlob("value");
                int dataId = resultSet.getInt("dataId");
                Data data = new Data(dataId,value,user);
                datas.add(data);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return datas;
    }

    public void insetDataByUser(Data data, User user){

    }
}
