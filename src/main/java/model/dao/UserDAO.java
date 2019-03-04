package model.dao;

import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static model.dao.GlobalConstants.Statements.*;
import static model.dao.GlobalConstants.Columns.*;

public class UserDAO extends AbstractDAO {
    private static UserDAO instance;

    private Set<User> allUsers;

    private UserDAO() {
        allUsers = new HashSet<User>();
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public int addUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        int resultAdded = 0;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_USER);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());


            resultAdded = statement.executeUpdate();

// инкремент
            ResultSet rs = statement.executeQuery(USERS_MAX_ID);
            rs.next();
            int userId = rs.getInt(ID);
            user.setId(userId);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultAdded;
    }

    public Set<User> getAllUsers() {
        Set<User> users = new HashSet<>();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(ALL_FROM_USERS);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(ID);
                String email = rs.getString(EMAIL);
                String password = rs.getString(PASSWORD);
                String firstName = rs.getString(FIRST_NAME);
                String lastName = rs.getString(LAST_NAME);

                User user = new User();
                user.setId(id);
                user.setEmail(email);
                user.setPassword(password);
                user.setFirstName(firstName);
                user.setLastName(lastName);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUser(String email, String password) {
        User user = null;

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            System.out.println("Получили коннекшн в юзердао");
            statement = connection.prepareStatement(SELECT_USER_ON_PASSWORD_EMAIL);

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(ID);
                String firstName = rs.getString(FIRST_NAME);
                String lastName = rs.getString(LAST_NAME);

                user = new User();
                user.setId(id);
                user.setEmail(email);
                user.setFirstName(firstName);
                user.setPassword(password);
                user.setLastName(lastName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR DRIVER");
        }
        return user;
    }
}
