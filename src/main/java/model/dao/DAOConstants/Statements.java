package model.dao.DAOConstants;

public interface Statements {
    String INSERT_USER = "INSERT INTO user(email, password, first_name, last_name) VALUES (?,?,?,?)";
    String ALL_FROM_USERS = "SELECT * FROM user";
    String SELECT_USER_ON_PASSWORD_EMAIL = "SELECT id, first_name, last_name FROM user WHERE email = ? AND password = ?";
    String ALL_FROM_CATEGORY = "SELECT * FROM category";
    String INSERT_MAIL = "INSERT INTO mail (sender, recipient, title, tags, category, message, related_user) VALUES (?,?,?,?,?,?,?)";
    String USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    String ALL_FROM_MAIL = "SELECT * FROM mail WHERE related_user = ? ORDER BY date_time DESC";
    String ALL_FROM_MAIL_WHERE_CATEGORY_AND_USER = "SELECT * FROM mail WHERE category = ? AND related_user = ?";
    String MAIL_IN_TIME_PERIOD = "Select * from mail where date_time between ? and ? and related_user=?";
    String ALL_FROM_MAIL_WITHOUT = "SELECT * FROM mail ORDER BY date_time DESC";
    String DELETE_FROM_MAIL = "DELETE FROM mail where id = ?";
    String INSERT_CUSTOM_CATEGORY = "INSERT INTO customer_category (category, user_id) VALUES (?, ?)";
    String CHANGE_CATEGORY = "UPDATE mail set category = ? where id = ?";
    String ALL_FROM_CUSTOMER_CATEGORY = "SELECT * FROM customer_category where user_id = ?";
    String GET_MAIL_BY_TITLE = "SELECT * FROM mail where title = ? and related_user = ?";
    String FIND_MAIL_BY_TAG = "SELECT * FROM mail where tags LIKE ? and related_user = ?";
    String FIND_MAIL_BY_RECIPIENT_OR_SENDER = "SELECT * FROM mail WHERE sender = ? OR recipient = ? AND related_user = ?";
    String ALL_EMAILS_FROM_USERS = "SELECT email FROM user";
    String CUSTOMER_CATEGORY_BY_ID = "SELECT * FROM customer_category WHERE id = ?";
    String CATEGORY_BY_ID = "SELECT * FROM category WHERE id = ?";
}