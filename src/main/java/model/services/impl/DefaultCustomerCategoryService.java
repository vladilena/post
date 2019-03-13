package model.services.impl;

import model.dao.factory.DAOFactory;
import model.dao.impl.CustomerCategoryDAO;
import model.entity.CustomerCategory;
import model.entity.User;
import model.services.CustomerCategoryService;
import org.apache.logging.log4j.*;

import java.util.Set;

public class DefaultCustomerCategoryService implements CustomerCategoryService {
    private static final Logger logger = LogManager.getLogger(DefaultCustomerCategoryService.class);

    private static volatile CustomerCategoryService customerCategoryService;
    private static CustomerCategoryDAO customerCategoryDAO;

    private DefaultCustomerCategoryService() {
        customerCategoryDAO = DAOFactory.getInstance().getCustomerCategoryDAO();
    }

    public static CustomerCategoryService getInstance(){
        CustomerCategoryService localInstance = customerCategoryService;
        if(localInstance == null) {
            synchronized (DefaultCategoryService.class) {
                localInstance = customerCategoryService;
                if(localInstance == null) {
                    customerCategoryService = new DefaultCustomerCategoryService();
                    logger.debug("Create DefaultCustomerCategoryService instance");
                }
            }
        }
        logger.debug("Return DefaultCustomerCategoryService instance");
        return customerCategoryService;
    }

    @Override
    public boolean addCustomCategory(CustomerCategory category) {
        return customerCategoryDAO.addCustomCategory(category);
    }

    @Override
    public long getCustomerCategoryIdByNameAndUserId(String name, long userId) {
        return customerCategoryDAO.getCustomerCategoryIdByNameAndUserId(name,userId);
    }

    @Override
    public Set<CustomerCategory> getAllCustomerCategories(User user) {
        return customerCategoryDAO.getAllCustomerCategories(user);
    }

    @Override
    public CustomerCategory getCustomerCategoryById(long categoryId) {
        return customerCategoryDAO.getCustomerCategoryById(categoryId);
    }
}


