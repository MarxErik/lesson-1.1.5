package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConection;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = getConection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = null;
            String sql = "CREATE TABLE IF NOT EXISTS USER" +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void dropUsersTable() {
        try {
            connection.setAutoCommit(false);
            try {
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = null;
                String sql = "DROP TABLE IF EXISTS USER";
                try {
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.executeUpdate();
                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = null;
            String sql = "INSERT INTO USER (name, lastName, age) VALUES (?, ?, ?)";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);

                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void removeUserById(long id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = null;
            String sql = "DELETE FROM USER WHERE ID = ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, id);

                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        Connection connection = getConection();
        List <User> userList = new ArrayList<>();
        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM USER";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    public void cleanUsersTable() {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = null;
            String sql = "TRUNCATE TABLE USER";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
