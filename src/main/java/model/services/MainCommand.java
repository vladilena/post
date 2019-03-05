package model.services;

import model.dao.CategoryDAO;
import model.dao.CustomerCategoryDAO;
import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Category;
import model.entity.CustomerCategory;
import model.entity.Mail;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class MainCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        DAOFactory factory = DAOFactory.getInstance();
        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            MailDAO mailDAO = factory.getMailDAO();
            List<Mail> mails = mailDAO.getAllMails(user);

            CategoryDAO categoryDAO = factory.getCategoryDAO();
            Set<Category> categories = categoryDAO.getAllCategories();

            CustomerCategoryDAO customerCategoryDAO = factory.getCustomerCategoryDAO();
            Set<CustomerCategory> customerCategories = customerCategoryDAO.getAllCustomerCategories(user);

            request.setAttribute("custom_categories", customerCategories);
            request.setAttribute("mails", mails);
            request.setAttribute("categories", categories);
        }
        return "main.jsp";
    }
}