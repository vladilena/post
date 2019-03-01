package model.dao;

import model.entity.Mail;
import model.entity.User;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static model.dao.GlobalConstants.Columns.*;
import static model.dao.GlobalConstants.Statements.*;


public class MailDAO extends AbstractDAO {
    private static MailDAO instance;

    private List<Mail> mails;

    private MailDAO() {
        mails = new LinkedList<>();
    }

    public static MailDAO getInstance() {
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

            statement.setInt(1, mail.getSender());
            statement.setInt(2, mail.getRecipient());
            statement.setString(3, mail.getTitle());
            statement.setString(4, mail.getTags());
            statement.setInt(5, mail.getCategory());
            statement.setString(6, mail.getMessage());

            resultAdded = statement.executeUpdate();

// инкремент??
            ResultSet rs = statement.executeQuery(MAIL_MAX_ID);
            rs.next();
            int mailId = rs.getInt(ID);
            mail.setId(mailId);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultAdded;
    }

    public List<Mail> getAllMails() {
        List<Mail> mails = new LinkedList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(ALL_FROM_MAIL);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(ID);
                int senderId = rs.getInt(SENDER_ID);
                int recipientId = rs.getInt(RECIPIENT_ID);
                Timestamp dateTime = rs.getTimestamp(DATE_TIME);
                String title = rs.getString(TITLE);
                String tags = rs.getString(TAGS);
                int categoryId = rs.getInt(CATEGORY_ID);
                String message = rs.getString(MESSAGE);

                Mail mail = new Mail();
                mail.setId(id);
                mail.setSender(senderId);
                mail.setRecipient(recipientId);
                mail.setDateTime(dateTime);
                mail.setTitle(title);
                mail.setTags(tags);
                mail.setCategory(categoryId);
                mail.setMessage(message);

                mails.add(mail);
            }
    } catch (SQLException e1) {
            e1.printStackTrace();
        }
return mails;
    }

    }



