package model.command.impl.category;


import controller.validation.DefaultValidation;
import model.entity.CustomerCategory;
import model.entity.User;
import model.command.Command;
import model.services.CustomerCategoryService;
import model.services.impl.DefaultCustomerCategoryService;
import controller.validation.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static model.command.CommandConstants.*;


public class CreateCategoryCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateCategoryCommand.class);

    private static CustomerCategoryService customerCategoryService;
    private static Validation validation;

    public CreateCategoryCommand() {
        customerCategoryService = DefaultCustomerCategoryService.getInstance();
        validation = DefaultValidation.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute(USER);
        String categoryName = request.getParameter(NEW_CATEGORY);

        if (categoryChecked(categoryName)) {
            logger.info("Category name is valid");
            CustomerCategory category = new CustomerCategory();
            category.setCategoryName(categoryName);
            category.setUser(user);

            if (addCategory(category, user)) {
                logger.info("Category was added to a database");
                Set<CustomerCategory> categories = customerCategoryService.getAllCustomerCategories(user);
                request.setAttribute(CUSTOM_CATEGORIES_ATTRIBUTE, categories);
            } else {
                logger.debug("Category wasn't added to a database");
                request.setAttribute(WRONG_CATEGORY_ATTRIBUTE, true);
            }

        } else {
            logger.debug("Category name is invalid");
            request.setAttribute(WRONG_CATEGORY_INPUT_ATTRIBUTE, true);
        }

        return "controller?action=main";
    }

    private boolean categoryChecked(String categoryName) {
        return (categoryName != null && validation.isCategoryValid(categoryName));
    }

    private boolean addCategory(CustomerCategory category, User user) {
        boolean result = false;
        if (!categoryExists(category, user)) {
            customerCategoryService.addCustomCategory(category);
            result = true;
        }
        return result;
    }

    private boolean categoryExists(CustomerCategory category, User user) {
        Set<CustomerCategory> categories = customerCategoryService.getAllCustomerCategories(user);
        return categories.contains(category);
    }


}