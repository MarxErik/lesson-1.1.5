package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.Objects;
import java.util.logging.Logger;


public class Main {
    private final static UserService userService = new UserServiceImpl();
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    static {
        String path = Objects.requireNonNull(Main.class.getClassLoader()
                .getResource("logging.properties")).getFile();
        System.setProperty("java.util.logging.config.file", path);
    }

    public static void main(String[] args) {

        userService.createUsersTable();

        userService.saveUser("Стас", "Сильвесторов", (byte) 18);
        userService.saveUser("Миша", "Бегинс", (byte) 24);
        userService.saveUser("Елена", "Иванова", (byte) 43);
        userService.saveUser("Гриша", "Гришнов", (byte) 14);

        for (User user: userService.getAllUsers()){
            logger.info(String.valueOf(user));
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
