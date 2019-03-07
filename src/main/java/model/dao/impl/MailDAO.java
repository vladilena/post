package model.dao.impl;

import model.entity.Category;
import model.entity.CustomerCategory;
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
            statement.setLong(5, 2);
            statement.setString(6, mail.getMessage());
            statement.setLong(7, mail.getRelatedUser().getId());

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
        long userId = user.getId();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ALL_FROM_MAIL);

            statement.setLong(1, userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                mails.add(parseFromResultSet(rs));
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
                mails.add(parseFromResultSet(rs));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return mails;
    }

    public List<Mail> getMailByCustomCategory(String category, User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        DAOFactory daoFactory = DAOFactory.getInstance();
        CustomerCategoryDAO customerCategoryDAO = daoFactory.getCustomerCategoryDAO();
        List<Mail> mails = new LinkedList<>();
        long categoryId = customerCategoryDAO.getCustomerCategoryIdByNameAndUserId(category, user.getId());
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ALL_FROM_MAIL_WHERE_CUSTOM_CATEGORY_AND_USER);

            statement.setLong(1, categoryId);
            statement.setLong(2, user.getId());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                mails.add(parseFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mails;
    }
    public List<Mail> getMailByCategory(String category, User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        DAOFactory daoFactory = DAOFactory.getInstance();
        CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
        List<Mail> mails = new LinkedList<>();
        long categoryId = categoryDAO.getCategoryIdByName(category);
        try {
            connection = getConnection();
            //не работает из-за одинаковых названий колонок. Нужно переделать подзапрос
            statement = connection.prepareStatement(ALL_FROM_MAIL_WHERE_CATEGORY_AND_USER);

            statement.setLong(1, categoryId);
            statement.setLong(2, user.getId());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                mails.add(parseFromResultSet(rs));
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
            statement.setLong(3, user.getId());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                mails.add(parseFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mails;
    }

    public void deleteMail(long messageId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(DELETE_FROM_MAIL);
            statement.setLong(1, messageId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
public int ReportedSpam (long messageId) {
    Connection connection = null;
    PreparedStatement statement = null;
    int resultAdded = 0;
    try {
        connection = getConnection();
        statement = connection.prepareStatement(REPORTED_SPAM);
        statement.setLong(1, messageId);
        resultAdded = statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
return resultAdded;
}
    public int changeCategory(long messageId, String category, User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        DAOFactory factory = DAOFactory.getInstance();
        CustomerCategoryDAO customerCategoryDAO = factory.getCustomerCategoryDAO();
        int resultAdded = 0;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(CHANGE_CATEGORY);
            long categoryId = customerCategoryDAO.getCustomerCategoryIdByNameAndUserId(category, user.getId());
            statement.setLong(1, categoryId);
            statement.setLong(2, messageId);
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
            statement.setLong(2, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                mails.add(parseFromResultSet(rs));
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
            statement.setLong(2, currentUser.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                mails.add(parseFromResultSet(rs));
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
            statement.setLong(3, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                mails.add(parseFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mails;
    }
    private Mail parseFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt(ID);
        String sender = rs.getString(SENDER);
        String recipient = rs.getString(RECIPIENT);
        Timestamp dateTime = rs.getTimestamp(DATE_TIME);
        String title = rs.getString(TITLE);
        String tags = rs.getString(TAGS);
        long relatedCategoryId = rs.getLong(CATEGORY);
        Category currentCategory = new Category();
        if (relatedCategoryId !=0){
            String categoryName = rs.getString(CATEGORY);
            String uaCategoryName = rs.getString(UA_CATEGORY);
            currentCategory.setId(relatedCategoryId);
            currentCategory.setCategoryName(categoryName);
            currentCategory.setUaCategoryName(uaCategoryName);
        }
        long relatedUserId = rs.getLong(RELATED_USER);
        User relatedUser = new User();
        if (relatedUserId !=0){
            String userEmail =rs.getString(EMAIL);
            String userPassword =rs.getString(PASSWORD);
            String userFirstName =rs.getString(FIRST_NAME);
            String userLastName =rs.getString(LAST_NAME);

            relatedUser.setId(relatedUserId);
            relatedUser.setEmail(userEmail);
            relatedUser.setPassword(userPassword);
            relatedUser.setFirstName(userFirstName);
            relatedUser.setLastName(userLastName);
        }
        long relatedCustomCategoryId = rs.getLong(CUSTOMER_CATEGORY);
        CustomerCategory currentCustomerCategory = new CustomerCategory();
        if (relatedCustomCategoryId !=0){
            String customCategoryName = rs.getString(CUSTOM_CATEGORY);
            currentCustomerCategory.setId(relatedCustomCategoryId);
            currentCustomerCategory.setCategoryName(customCategoryName);
            currentCustomerCategory.setUser(relatedUser);
        }
        String message = rs.getString(MESSAGE);

        Mail mail = new Mail();
        mail.setId(id);
        mail.setSender(sender);
        mail.setRecipient(recipient);
        //2019-03-06T09:59:43.905
        mail.setDateTime(LocalDateTime.parse((dateTime.toString()).replace(" ", "T")));
        mail.setTitle(title);
        mail.setTags(new ArrayList<>(Arrays.asList(tags.split("\\s*,\\s*"))));
        mail.setCategory(currentCategory);
        mail.setCustomCategory(currentCustomerCategory);
        mail.setMessage(message);
        mail.setRelatedUser(relatedUser);

        return mail;
    }

}



