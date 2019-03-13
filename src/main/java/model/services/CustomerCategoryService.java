package model.services;



import model.entity.CustomerCategory;
import model.entity.User;

import java.util.Set;

public interface CustomerCategoryService {

    /**
     * Method to add {@link CustomerCategory} to a DB
     *
     * @return return {@code boolean}
     */
    boolean addCustomCategory(CustomerCategory category);
    /**
     * Method to get {@link CustomerCategory} id which related to {@link User}
     * by {@code name}
     *
     * @return return {@link CustomerCategory}
     */
    long getCustomerCategoryIdByNameAndUserId(String name, long userId);

    /**
     * Method to get all {@link CustomerCategory} which related to {@link User}
     *
     * @return return {@link Set} of {@link CustomerCategory}
     */
    Set<CustomerCategory> getAllCustomerCategories(User user);

    /**
     * Method to get {@link CustomerCategory} by {@code id}
     *
     * @return return {@link CustomerCategory}
     */
    CustomerCategory getCustomerCategoryById (long categoryId);

}
