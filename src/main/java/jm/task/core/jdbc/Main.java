package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
        UserService userService = new UserServiceImpl(userDao);


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

