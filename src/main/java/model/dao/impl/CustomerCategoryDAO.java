package model.dao.impl;

import model.entity.CustomerCategory;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static model.dao.DAOConstants.Columns.*;
import static model.dao.DAOConstants.Statements.*;

public class CustomerCategoryDAO extends AbstractDAO {
    private static CustomerCategoryDAO instance;

    private CustomerCategoryDAO() {
    }

    public static CustomerCategoryDAO getInstance() {
        if (instance == null) {
            instance = new CustomerCategoryDAO();
        }
        return instance;
    }

    public boolean addCustomCategory(CustomerCategory category) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean resultAdded = false;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_CUSTOM_CATEGORY);

            statement.setString(1, category.getCategoryName());
            statement.setLong(2, category.getUser().getId());

            if(statement.executeUpdate()>0){
                resultAdded = true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultAdded;
    }

    public long getCustomerCategoryIdByNameAndUserId(String categoryName, long userId) {
        long customerCategoryId = 0;
        Set<CustomerCategory> categories = new HashSet<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(CUSTOMER_CATEGORY_ID_BY_NAME_AND_USER);
            statement.setString(1, categoryName);
            statement.setLong(2, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                customerCategoryId = rs.getLong(ID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerCategoryId;
    }

    public Set<CustomerCategory> getAllCustomerCategories(User user) {
        Set<CustomerCategory> categories = new HashSet<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ALL_FROM_CUSTOMER_CATEGORY);
            statement.setLong(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CustomerCategory newCategory = parseCustomerCategoryResultSet(rs);
                categories.add(newCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public CustomerCategory getCustomerCategoryById(long categoryId) {
        CustomerCategory category = new CustomerCategory();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(CUSTOMER_CATEGORY_BY_ID);
            statement.setLong(1, categoryId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                category = parseCustomerCategoryResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    private CustomerCategory parseCustomerCategoryResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong(ID);
        String categoryName = rs.getString(CUSTOM_CATEGORY);
        int userId = rs.getInt(USER_ID);
        User currentUser = new User();
        if (userId != 0) {
            currentUser.setId(userId);
            currentUser.setEmail(rs.getString(EMAIL));
            currentUser.setPassword(rs.getString(PASSWORD));
            currentUser.setFirstName(rs.getString(FIRST_NAME));
            currentUser.setLastName(rs.getString(LAST_NAME));
        }
        CustomerCategory newCategory = new CustomerCategory();

        newCategory.setId(id);
        newCategory.setCategoryName(categoryName);
        newCategory.setUser(currentUser);

        return newCategory;
    }
}


