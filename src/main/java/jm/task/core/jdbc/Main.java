package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 30);
        userService.saveUser("Petr", "Petrov", (byte) 25);
        userService.saveUser("Vasilisa", "Prekrasnaya", (byte) 20);
        userService.saveUser("Alexander", "Pushkin", (byte) 35);


        userService.getAllUsers();

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
