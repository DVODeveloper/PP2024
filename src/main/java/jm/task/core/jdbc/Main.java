package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {


        new UserServiceImpl().createUsersTable();

        new UserServiceImpl().saveUser("Ivan", "Ivanov", (byte) 30);
        new UserServiceImpl().saveUser("Petr", "Petrov", (byte) 25);
        new UserServiceImpl().saveUser("Vasilisa", "Prekrasnaya", (byte) 20);
        new UserServiceImpl().saveUser("Alexander", "Pushkin", (byte) 35);

        new UserServiceImpl().getAllUsers();

        new UserServiceImpl().cleanUsersTable();
        new UserServiceImpl().dropUsersTable();

    }
}
