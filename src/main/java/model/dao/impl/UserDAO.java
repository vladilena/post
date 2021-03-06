package model.dao.impl;

import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static model.dao.DAOConstants.Statements.*;
import static model.dao.DAOConstants.Columns.*;

public class UserDAO extends AbstractDAO {
    private static UserDAO instance;

    private Set<User> allUsers;

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public boolean addUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean resultAdded = false;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_USER);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());


            if(statement.executeUpdate()>0){
                resultAdded = true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultAdded;
    }

    public Set<String> getAllEmails() {
        Set<String> emails = new HashSet<>();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(ALL_EMAILS_FROM_USERS);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String email = rs.getString(EMAIL);
                emails.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
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
                User user = parseUsersFromResultSet (rs);
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
            statement = connection.prepareStatement(SELECT_USER_ON_PASSWORD_EMAIL);

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                user = parseUsersFromResultSet (rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public User getUserById(long userId) {
        User user = null;

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(USER_BY_ID);

            statement.setLong(1, userId);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                user = parseUsersFromResultSet (rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user = null;

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(USER_BY_EMAIL);

            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                user = parseUsersFromResultSet (rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
   private User parseUsersFromResultSet (ResultSet rs) throws SQLException {
       long id = rs.getLong(ID);
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
       return user;
   }
}