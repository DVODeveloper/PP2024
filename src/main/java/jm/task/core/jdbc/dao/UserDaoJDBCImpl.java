package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mydbtest";
    private static final String USER = "root";
    private static final String PASSWORD = "Kata03092024";
    Connection conn;

    public UserDaoJDBCImpl() {
        Util util = new Util(URL, USER, PASSWORD);
        this.conn = util.getConnection();
    }

    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "last_name VARCHAR(255), " +
                "age TINYINT)";

        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(createTableSQL);
            System.out.println("Выполнен метод createUsersTable()");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = conn.createStatement()) {
            String deleteAllTableSQL = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(deleteAllTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        if (name != null && lastName != null && age > 0) {
            String insertUserSQL = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(insertUserSQL)) {
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setByte(3, age);
                int rowInserted = statement.executeUpdate();
                if (rowInserted > 0) {
                    System.out.println("User с именем " + name + " добавлен в базу данных.\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else return;
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userArrayList = new ArrayList<>();
        String getAllUsers = "SELECT * FROM users";

        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllUsers);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                byte age = resultSet.getByte("age");

                User user = new User(id, name, lastName, age);
                userArrayList.add(user);
            }
            for (User user : userArrayList) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return userArrayList;
        }
        return userArrayList;
    }

    public void cleanUsersTable() {
        try (Statement statement = conn.createStatement()) {
            String deleteSQL = "DELETE FROM users";
            statement.executeUpdate(deleteSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
