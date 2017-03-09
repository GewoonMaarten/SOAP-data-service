package nl.hu.fnt.maarten.dataAccess;

import nl.hu.fnt.maarten.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
    private static UserDAO instance = null;

    private UserDAO(){}

    public static UserDAO getInstance(){
        if(instance == null){
            instance = new UserDAO();
        }
        return instance;
    }

    public User getUserByToken(String token){
        User user = null;

        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement statement;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM 'User' WHERE token = "+token);
            while (resultSet.next()){
                int id = resultSet.getInt("userId");
                user = new User(id, token);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
    public void insertUser(User user){
        Connection connection = DatabaseConnection.getInstance().getConnection();
        System.out.println(connection);
        Statement statement;
        try{
            statement = connection.createStatement();

            if(user.getUserId() == 0){
                statement.executeUpdate("INSERT INTO 'User'(token) VALUES ("+user.getToken()+")");
            } else {
                statement.executeUpdate("INSERT INTO 'User'(userid, token) VALUES ("+user.getUserId()+","+user.getToken()+")");
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
                e.printStackTrace();
        }
    }
}
