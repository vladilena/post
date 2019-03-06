package model.dao;

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

    public static CustomerCategoryDAO getInstance() {
        if (instance == null) {
            instance = new CustomerCategoryDAO();
        }
        return instance;
    }

    public int addCustomCategory(CustomerCategory category) {
        Connection connection = null;
        PreparedStatement statement = null;
        int resultAdded = 0;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_CUSTOM_CATEGORY);

            statement.setString(1, category.getCategoryName());
            statement.setInt(2, category.getUserId());

            resultAdded = statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultAdded;
    }

    public Set<CustomerCategory> getAllCustomerCategories(User user) {
        Set<CustomerCategory> categories = new HashSet<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ALL_FROM_CUSTOMER_CATEGORY);
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(ID);
                String categoryName = rs.getString(CATEGORY);
                int userId = rs.getInt(USER_ID);

                CustomerCategory newCategory = new CustomerCategory();

                newCategory.setId(id);
                newCategory.setCategoryName(categoryName);
                newCategory.setUserId(userId);

                categories.add(newCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

}


