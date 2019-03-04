package model.services;

import model.dao.CategoryDAO;
import model.dao.CustomerCategoryDAO;
import model.dao.DAOFactory;
import model.entity.Category;
import model.entity.CustomerCategory;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

import static model.dao.GlobalConstants.Columns.CATEGORY;

public class CreateCategoryCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();
        String categoryName = request.getParameter("newcategory");

        CustomerCategory categ = new CustomerCategory();

        categ.setCategoryName(categoryName);
        categ.setUserId(userId);

        DAOFactory factory = DAOFactory.getInstance();
        CustomerCategoryDAO categoryDao = factory.getCustomerCategoryDAO();
        Set<CustomerCategory> categories = categoryDao.getAllCustomerCategories(user);

        int add =0;

        if (!categories.contains(categ)) {
            add = categoryDao.addCustomCategory(categ);
            System.out.println(add + " size " + categoryDao.getAllCustomerCategories(user).size());
        }

        if (add == 0) {
            request.setAttribute("notAdd", "This category is exists");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("custom_categories", categories);
        }

        //String result = (add == 0) ? "addcategory.jsp" : "controller?action=main";
        return "controller?action=main";

    }
}

