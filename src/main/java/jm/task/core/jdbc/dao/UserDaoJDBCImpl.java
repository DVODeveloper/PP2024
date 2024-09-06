package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "last_name VARCHAR(255), " +
                "age TINYINT)";

        try (Connection conn = Util.getConnection();
             PreparedStatement statement = conn.prepareStatement(createTableSQL)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String deleteAllTableSQL = "DROP TABLE IF EXISTS users";
        try (Connection conn = Util.getConnection();
             PreparedStatement statement = conn.prepareStatement(deleteAllTableSQL)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        if (name != null && lastName != null && age > 0) {
            String insertUserSQL = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
            try (Connection conn = Util.getConnection();
                 PreparedStatement statement = conn.prepareStatement(insertUserSQL)) {
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setByte(3, age);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else return;
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnection();
             PreparedStatement statement = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userArrayList = new ArrayList<>();
        String getAllUsers = "SELECT * FROM users";

        try (Connection conn = Util.getConnection();
             PreparedStatement statement = conn.prepareStatement(getAllUsers)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                byte age = resultSet.getByte("age");

                User user = new User(id, name, lastName, age);
                userArrayList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return userArrayList;
        }
        return userArrayList;
    }

    public void cleanUsersTable() {
        String deleteSQL = "DELETE FROM users";
        try (Connection conn = Util.getConnection();
             PreparedStatement statement = conn.prepareStatement(deleteSQL)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
