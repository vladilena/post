package model.services;

import model.dao.CategoryDAO;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.entity.Category;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class CreateCategoryCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        DAOFactory factory = DAOFactory.getInstance();
        CategoryDAO categoryDao = factory.getCategoryDAO();
        Set<Category> categories = categoryDao.getAllCategories();
        int add = 0;

        String categoryName = request.getParameter("category");
        Category categ = new Category();
        categ.setCategory(categoryName);

        if (!categories.contains(categ)){
            add = categoryDao.addCustomCategory(categ);
        }
        if (add == 0){
            request.setAttribute("notAdd", "This category exists");
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("category", categ);
        }

        String result = (add == 0) ? "addcategory.jsp" : "controller?action=main";
        return result;
    }
}

