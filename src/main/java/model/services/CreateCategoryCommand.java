package model.services;


import model.dao.CustomerCategoryDAO;
import model.dao.DAOFactory;
import model.entity.CustomerCategory;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;


public class CreateCategoryCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Validation validation = new Validation();
        User user = (User) request.getSession().getAttribute("user");
        String categoryName = request.getParameter("newcategory");

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
                session.setAttribute("custom_categories", categories);
            } else {
                request.setAttribute("wrongCategory", "Incorrect name for category or this category exists");
            }
        } else {
            request.setAttribute("wrongCategory", "Incorrect name for category or this category exists");
        }
        return "controller?action=main";
    }
}

