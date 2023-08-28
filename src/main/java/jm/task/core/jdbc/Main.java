package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("ShahaSDASDsdaa", "ASFSAFIvaasdanov", (byte) 63);
    }
}
