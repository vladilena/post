package model.dao;

import model.entity.Category;
import model.entity.User;
import model.services.Command;

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

    private Set<Category> categories;

    private CategoryDAO() {
        categories = new HashSet<Category>();
    }

    public static CategoryDAO getInstance() {
        if (instance == null) {
            instance = new CategoryDAO();
        }
        return instance;
    }

    public int addCustomCategory(Category category) {
        Connection connection = null;
        PreparedStatement statement = null;
        int resultAdded = 0;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_CATEGORY);

            statement.setString(1, category.getCategory());

            resultAdded = statement.executeUpdate();

// инкремент??
            ResultSet rs = statement.executeQuery(CATEGORY_MAX_ID);
            rs.next();
            int categoryId = rs.getInt(ID);
            category.setId(categoryId);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultAdded;
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
                int id = rs.getInt(ID);
                String category = rs.getString(CATEGORY);

                Category newCategory = new Category();
                newCategory.setId(id);
                newCategory.setCategory(category);
                categories.add(newCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}

