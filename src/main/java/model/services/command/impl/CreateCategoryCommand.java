package model.services.command.impl;


import model.dao.impl.CustomerCategoryDAO;
import model.dao.impl.DAOFactory;
import model.entity.CustomerCategory;
import model.entity.User;
import model.services.command.Command;
import model.services.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

import static model.services.command.CommandConstants.*;


public class CreateCategoryCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Validation validation = new Validation();
        User user = (User) request.getSession().getAttribute(USER);
        String categoryName = request.getParameter(NEW_CATEGORY);

        if (validation.isCategoryValid(categoryName)) {
            CustomerCategory categ = new CustomerCategory();

            categ.setCategoryName(categoryName);
            categ.setUserId(user.getId());

            DAOFactory factory = DAOFactory.getInstance();
            CustomerCategoryDAO categoryDao = factory.getCustomerCategoryDAO();
            Set<CustomerCategory> categories = categoryDao.getAllCustomerCategories(user);

            int add = 0;

            if (!categories.contains(categ)) {
                add = categoryDao.addCustomCategory(categ);
                HttpSession session = request.getSession();
                categories = categoryDao.getAllCustomerCategories(user);
                session.setAttribute(CUSTOM_CATEGORIES_ATTRIBUTE, categories);
                session.setAttribute(USER, user);
            } else {
                request.setAttribute(WRONG_CATEGORY_ATTRIBUTE, true);
            }
        } else {
            request.setAttribute(WRONG_CATEGORY_ATTRIBUTE, true);
        }
        return "controller?action=main";
    }
}

