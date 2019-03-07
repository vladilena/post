package model.dao.impl;

import model.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static model.dao.DAOConstants.Columns.*;
import static model.dao.DAOConstants.Statements.*;

public class CategoryDAO extends AbstractDAO {
    private static CategoryDAO instance;

    private CategoryDAO() {
    }

    public static CategoryDAO getInstance() {
        if (instance == null) {
            instance = new CategoryDAO();
        }
        return instance;
    }


    public Set<Category> getAllCategories() {
        Set<Category> categories = new HashSet<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ALL_FROM_CATEGORY);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category newCategory = parseCategoryFromResultSet(rs);
                categories.add(newCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category getCategoryById(int categoryId) {
        Category category = new Category();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(CATEGORY_BY_ID);
            statement.setInt(1,categoryId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                category = parseCategoryFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
    public long getCategoryIdByName(String categoryName){
        long categoryId = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(CATEGORY_ID_BY_NAME);
            statement.setString(1, categoryName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                categoryId = rs.getLong(ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
return categoryId;
    }
    private Category parseCategoryFromResultSet (ResultSet rs) throws SQLException {
        long id = rs.getLong(ID);
        String categoryName = rs.getString(CATEGORY);
        String uaCategoryName = rs.getString(UA_CATEGORY);

        Category newCategory = new Category();

        newCategory.setId(id);
        newCategory.setCategoryName(categoryName);
        newCategory.setUaCategoryName(uaCategoryName);
        return newCategory;
    }
}
