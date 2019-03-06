package model.services.command.impl;

import model.dao.CategoryDAO;
import model.dao.CustomerCategoryDAO;
import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Category;
import model.entity.CustomerCategory;
import model.entity.Mail;
import model.entity.User;
import model.services.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

import static model.services.command.CommandConstants.*;

public class MainCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        DAOFactory factory = DAOFactory.getInstance();
        User user = (User) request.getSession().getAttribute(USER);

        if (user != null) {
            MailDAO mailDAO = factory.getMailDAO();
            List<Mail> mails = mailDAO.getAllMails(user);

            CategoryDAO categoryDAO = factory.getCategoryDAO();
            Set<Category> categories = categoryDAO.getAllCategories();

            CustomerCategoryDAO customerCategoryDAO = factory.getCustomerCategoryDAO();
            Set<CustomerCategory> customerCategories = customerCategoryDAO.getAllCustomerCategories(user);

            request.setAttribute(CUSTOM_CATEGORIES_ATTRIBUTE, customerCategories);
            request.setAttribute(MAILS_ATTRIBUTE, mails);
            request.setAttribute(CATEGORIES_ATTRIBUTE, categories);
        }
        return "main.jsp";
    }
}