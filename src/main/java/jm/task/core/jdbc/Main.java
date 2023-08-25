package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) throws SQLException {
//        userService.createUsersTable();
//        userService.saveUser("Shaha", "Ivanov", (byte) 23);
//        userService.saveUser("Shahasdaa", "Ivaasdanov", (byte) 43);
//        //userService.dropUsersTable();
//        userService.removeUserById(2);
//        System.out.println(userService.getAllUsers());
//        userService.cleanUsersTable();
//        System.out.println(userService.getAllUsers());
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("ShahaSDASDsdaa", "ASFSAFIvaasdanov", (byte) 63);
    }
}
