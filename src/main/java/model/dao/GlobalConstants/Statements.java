package model.dao.GlobalConstants;

public interface Statements {
    String INSERT_USER = "INSERT INTO user(email, password, first_name, last_name) VALUES (?,?,?,?)";
    String USERS_MAX_ID = "SELECT MAX(id) FROM user";
    String ALL_FROM_USERS = "SELECT * FROM user";
    String SELECT_USER_ON_PASSWORD_EMAIL = "SELECT id, first_name, last_name FROM user WHERE email = ? AND password = ?";
    String INSERT_CATEGORY = "INSERT INTO category (category) VALUES (?)";
    String CATEGORY_MAX_ID = "SELECT MAX(id) FROM category";
    String ALL_FROM_CATEGORY = "SELECT * FROM category";

}
