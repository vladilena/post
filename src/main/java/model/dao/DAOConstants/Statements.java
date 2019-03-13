package model.dao.DAOConstants;

public interface Statements {
    String INSERT_USER = "INSERT INTO user(email, password, first_name, last_name) VALUES (?,?,?,?)";
    String ALL_FROM_USERS = "SELECT * FROM user";
    String SELECT_USER_ON_PASSWORD_EMAIL = "SELECT * FROM user WHERE email = ? AND password = ?";
    String ALL_FROM_CATEGORY = "SELECT * FROM category";
    String INSERT_MAIL = "INSERT INTO mail (sender, recipient, title, tags, category, message, related_user) VALUES (?,?,?,?,?,?,?)";
    String USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    String USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    //String ALL_FROM_MAIL = "SELECT * FROM mail WHERE related_user = ? ORDER BY date_time DESC";
    String ALL_FROM_MAIL = "SELECT * FROM mail A left join user B on (A.related_user = B.id) \n" +
            "left join category C on (A.category = C.id) \n" +
            "left join  customer_category D on (A.customer_category = D.id)\n" +
            "WHERE related_user = ? ORDER BY date_time DESC";
    //String ALL_FROM_MAIL_WHERE_CATEGORY_AND_USER = "SELECT * FROM mail WHERE category = ? AND related_user = ?";
    String ALL_FROM_MAIL_WHERE_CATEGORY_AND_USER = "SELECT * FROM mail A left join user B on (A.related_user = B.id) left join category C on (A.category = C.id) left join  customer_category D on (A.customer_category = D.id)WHERE A.category = ? AND related_user = ?";
    String ALL_FROM_MAIL_WHERE_CUSTOM_CATEGORY_AND_USER = "SELECT * FROM mail A left join user B on (A.related_user = B.id) left join category C on (A.category = C.id) left join  customer_category D on (A.customer_category = D.id)WHERE customer_category = ? AND related_user = ?";
    //String MAIL_IN_TIME_PERIOD = "Select * from mail where date_time between ? and ? and related_user=?";
    String MAIL_IN_TIME_PERIOD = "Select * from mail A left join user B on (A.related_user = B.id) left join category C on (A.category = C.id) left join  customer_category D on (A.customer_category = D.id)where date_time between ? and ? and related_user=?";
    //String ALL_FROM_MAIL_WITHOUT = "SELECT * FROM mail ORDER BY date_time DESC";
    String ALL_FROM_MAIL_WITHOUT = "SELECT * FROM mail A left join user B on (A.related_user = B.id) left join category C on (A.category = C.id) left join  customer_category D on (A.customer_category = D.id) ORDER BY date_time DESC";
    String DELETE_FROM_MAIL = "DELETE FROM mail where id = ?";
    String INSERT_CUSTOM_CATEGORY = "INSERT INTO customer_category (custom_category, user_id) VALUES (?, ?)";
    String CHANGE_CATEGORY = "UPDATE mail set customer_category = ? where id = ?";
    //String ALL_FROM_CUSTOMER_CATEGORY = "SELECT * FROM customer_category where user_id = ?";
    String ALL_FROM_CUSTOMER_CATEGORY = " SELECT * FROM customer_category A left join user B on (A.user_id = B.id) where user_id = ?";
    //String GET_MAIL_BY_TITLE = "SELECT * FROM mail where title = ? and related_user = ?";
    String GET_MAIL_BY_TITLE = "SELECT * FROM mail A left join user B on (A.related_user = B.id) left join category C on (A.category = C.id) left join  customer_category D on (A.customer_category = D.id) where title = ? and related_user = ?";
    //String FIND_MAIL_BY_TAG = "SELECT * FROM mail where tags LIKE ? and related_user = ?";
    String FIND_MAIL_BY_TAG = "SELECT * FROM mail A left join user B on (A.related_user = B.id) left join category C on (A.category = C.id) left join  customer_category D on (A.customer_category = D.id) where tags LIKE ? and related_user = ?;";
    //String FIND_MAIL_BY_RECIPIENT_OR_SENDER = "SELECT * FROM mail WHERE sender = ? OR recipient = ? AND related_user = ?";
    String FIND_MAIL_BY_RECIPIENT_OR_SENDER = "SELECT * FROM mail A left join user B on (A.related_user = B.id) left join category C on (A.category = C.id) left join  customer_category D on (A.customer_category = D.id) where recipient = ? OR sender = ? and related_user = ?";
    String ALL_EMAILS_FROM_USERS = "SELECT email FROM user";
    String CUSTOMER_CATEGORY_BY_ID = "SELECT * FROM customer_category WHERE id = ?";
    String CATEGORY_BY_ID = "SELECT * FROM category WHERE id = ?";
    String CUSTOMER_CATEGORY_ID_BY_NAME_AND_USER = "SELECT id FROM customer_category WHERE custom_category = ? and user_id = ?";
    String REPORTED_SPAM = "UPDATE mail set category = ? where id = ?";
    String CATEGORY_ID_BY_NAME = "SELECT id FROM category WHERE category = ?";

}