package model.dao;

import model.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static model.dao.GlobalConstants.Columns.CATEGORY;
import static model.dao.GlobalConstants.Columns.ID;
import static model.dao.GlobalConstants.Statements.*;

public class CategoryDAO extends AbstractDAO {
    private static CategoryDAO instance;


    public static CategoryDAO getInstance() {
        if (instance == null) {
            instance = new CategoryDAO();
        }
        return instance;
    }


    public Set<Category> getAllCategories() {
        Set<Category>categories = new HashSet<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ALL_FROM_CATEGORY);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(ID);
                String categoryName = rs.getString(CATEGORY);

                Category newCategory = new Category();

                newCategory.setId(id);
                newCategory.setCategoryName(categoryName);
                categories.add(newCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public int getCategoryIdByName(String categoryName) {
        int categoryId = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            System.out.println("connection in CategoryDAO.getCategoryIDByName");
            statement = connection.prepareStatement(FIND_CATEGORY_BY_ID);

            statement.setString(1, categoryName);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                categoryId = rs.getInt(ID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }
}
