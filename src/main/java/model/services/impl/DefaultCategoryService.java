package model.services.impl;

import model.dao.factory.DAOFactory;
import model.dao.impl.CategoryDAO;
import model.entity.Category;
import model.services.CategoryService;
import org.apache.logging.log4j.*;


import java.util.Set;

public class DefaultCategoryService implements CategoryService {
    private static final Logger logger = LogManager.getLogger(DefaultCategoryService.class);
    private static volatile CategoryService categoryService;
    private static CategoryDAO categoryDAO;


    private DefaultCategoryService() {
        categoryDAO = DAOFactory.getInstance().getCategoryDAO();
    }

    public static CategoryService getInstance() {
        CategoryService localInstance = categoryService;
        if (localInstance == null) {
            synchronized (DefaultCategoryService.class) {
                localInstance = categoryService;
                if (localInstance == null) {
                    categoryService = new DefaultCategoryService();
                    logger.debug("Create DefaultCategoryService instance");
                }
            }
        }
        logger.debug("Return DefaultCategoryService instance");
        return categoryService;
    }


    @Override
    public Set<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryDAO.getCategoryById(id);
    }

    @Override
    public long getCategoryIdByName(String categoryName) {
        return categoryDAO.getCategoryIdByName(categoryName);
    }
}


