package model.services;

import model.entity.Category;

import java.util.Set;

/**
 * The {@code CategoryService} service is a specified API for working with the {@link model.dao.impl.CategoryDAO}
 */
public interface CategoryService {
    /**
     * Method to get all {@link Category}
     *
     * @return return {@link Set} of all {@link Category}
     */
    Set<Category> getAllCategories();

    /**
     * Method to get {@link Category} by {@code id}
     *
     * @return return {@link Category}
     */
    Category getCategoryById(long id);

    /**
     * Method to get {@code id} of the {@link Category} by {@code categoryName}
     *
     * @return return {@code id}
     */
    long getCategoryIdByName(String categoryName);
}
