package model.dao.impl;

import model.entity.Mail;
import model.entity.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static model.dao.DAOConstants.Columns.*;
import static model.dao.DAOConstants.Statements.*;


public class MailDAO extends AbstractDAO {
    private static MailDAO instance;

    private MailDAO() {
    }

    static MailDAO getInstance() {
        if (instance == null) {
            instance = new MailDAO();
        }
        return instance;
    }

    public int sendMail(Mail mail) {
        Connection connection = null;
        PreparedStatement statement = null;
        int resultAdded = 0;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_MAIL);

            statement.setString(1, mail.getSender());
            statement.setString(2, mail.getRecipient());
            statement.setString(3, mail.getTitle());
            statement.setString(4, mail.getTags().toString());
            statement.setString(5, mail.getCategory());
            statement.setString(6, mail.getMessage());
            statement.setInt(7, mail.getRelatedUser().getId());

            resultAdded = statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultAdded;
    }

    public List<Mail> getAllMails(User user) {
        List<Mail> mails = new LinkedList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        int userId = user.getId();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ALL_FROM_MAIL);

            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                mails.add(recordFromResultSet(rs));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return mails;
    }

    public List<Mail> getAllMailsWithoutParams() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Mail> mails = new LinkedList<>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement(ALL_FROM_MAIL_WITHOUT);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                mails.add(recordFromResultSet(rs));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return mails;
    }

    public List<Mail> getMailByCategory(String category, User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Mail> mails = new LinkedList<>();


        try {
            connection = getConnection();
            statement = connection.prepareStatement(ALL_FROM_MAIL_WHERE_CATEGORY_AND_USER);

            statement.setString(1, category);
            statement.setInt(2, user.getId());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                mails.add(recordFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mails;
    }

    public List<Mail> getMailByTimePeriod(String from, String to, User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Mail> mails = new LinkedList<>();
        //2019-12-02T12:30
        // 2019-03-01 13:32:09

        Timestamp fromStamp = Timestamp.valueOf(from.replace("T", " ") + ":00");
        Timestamp toStamp = Timestamp.valueOf(to.replace("T", " ") + ":00");
        try {
            connection = getConnection();
            statement = connection.prepareStatement(MAIL_IN_TIME_PERIOD);
            statement.setTimestamp(1, fromStamp);
            statement.setTimestamp(2, toStamp);
            statement.setInt(3, user.getId());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                mails.add(recordFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mails;
    }

    public void deleteMail(int messageId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(DELETE_FROM_MAIL);
            statement.setInt(1, messageId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Mail recordFromResultSet(ResultSet rs) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        CategoryDAO categoryDAO = factory.getCategoryDAO();

        int id = rs.getInt(ID);
        String sender = rs.getString(SENDER);
        String recipient = rs.getString(RECIPIENT);
        Timestamp dateTime = rs.getTimestamp(DATE_TIME);
        String title = rs.getString(TITLE);
        String tags = rs.getString(TAGS);
        String category = rs.getString(CATEGORY);
        String message = rs.getString(MESSAGE);
        int relatedUserId = rs.getInt(RELATED_USER);
        User relatedUser = userDAO.getUserById(relatedUserId);

        Mail mail = new Mail();
        mail.setId(id);
        mail.setSender(sender);
        mail.setRecipient(recipient);
        //2019-03-06T09:59:43.905
        mail.setDateTime(LocalDateTime.parse((dateTime.toString()).replace(" ", "T")));
        mail.setTitle(title);
        mail.setTags(new ArrayList<>(Arrays.asList(tags.split("\\s*,\\s*"))));
        mail.setCategory(category);
        mail.setMessage(message);
        mail.setRelatedUser(relatedUser);

        return mail;
    }


    public int changeCategory(int messageId, String category) {
        Connection connection = null;
        PreparedStatement statement = null;
        int resultAdded = 0;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(CHANGE_CATEGORY);
            statement.setString(1, category);
            statement.setInt(2, messageId);
            resultAdded = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultAdded;
    }

    public List<Mail> getMailByTitle(String title, User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Mail> mails = new LinkedList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_MAIL_BY_TITLE);
            statement.setString(1, title);
            statement.setInt(2, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                mails.add(recordFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mails;
    }

    public List<Mail> getMailByTag(String tag, User currentUser) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Mail> mails = new LinkedList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(FIND_MAIL_BY_TAG);
            statement.setString(1, "%" + tag + "%");
            statement.setInt(2, currentUser.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                mails.add(recordFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mails;
    }

    public List<Mail> getMailBySenderOrRecipient(String email, User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Mail> mails = new LinkedList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(FIND_MAIL_BY_RECIPIENT_OR_SENDER);
            statement.setString(1, email);
            statement.setString(2, email);
            statement.setInt(3, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                mails.add(recordFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mails;
    }

}



