package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection connection = Util.getMyConnection()) {
            connection.createStatement().execute("CREATE TABLE users (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,name VARCHAR(20) NOT NULL , lastname VARCHAR(20) NOT NULL, age TINYINT UNSIGNED NOT NULL);");
            //System.out.println("Успешное соединение с Базой данных!");
            System.out.println("Таблица создана!");
        } catch (SQLException e) {
            System.out.println("Таблица не создалась");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getMyConnection()){
           connection.createStatement().execute("DROP TABLE myusers.users");
            System.out.println("Таблица удалена!");
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMyConnection();
             PreparedStatement prStatement = connection.prepareStatement("INSERT INTO myusers.users (" +
                     "name, lastname, age) VALUES (?,?,?)")){
            prStatement.setString(1,name);
            prStatement.setString(2,lastName);
            prStatement.setByte(3,age);
            prStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try(Connection connection=Util.getMyConnection();
            PreparedStatement prStatement = connection.prepareStatement("DELETE FROM myusers.users " +
                    "WHERE id= ?")) {
            prStatement.setLong(1,id);
            prStatement.executeUpdate();
            System.out.println("Строка с ИД = "+id+" удалена ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try(Connection connection=Util.getMyConnection();
        PreparedStatement prStatement= connection.prepareStatement("SELECT * FROM users")){
            ResultSet resultSet = prStatement.executeQuery();
            ArrayList <User> userArrayList = new ArrayList<>();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user =new User(name,lastName,age);
                userArrayList.add(user);
                System.out.println(user);
            }
            return userArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try(Connection connection=Util.getMyConnection();
            PreparedStatement prStatement = connection.prepareStatement(" DELETE  FROM myusers.users")) {
            prStatement.executeUpdate();
            System.out.println("Все строки очищены!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
