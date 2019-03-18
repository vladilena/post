package model.command.impl;


import model.entity.Category;
import model.entity.CustomerCategory;
import model.entity.Mail;
import model.entity.User;
import model.command.Command;
import model.services.CategoryService;
import model.services.CustomerCategoryService;
import model.services.MailService;
import model.services.impl.DefaultCategoryService;
import model.services.impl.DefaultCustomerCategoryService;
import model.services.impl.DefaultMailService;
import model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

import static model.command.CommandConstants.*;

public class MainCommand implements Command {
    private static MailService mailService;
    private static CategoryService categoryService;
    private static CustomerCategoryService customerCategoryService;

    public MainCommand(){
        mailService = DefaultMailService.getInstance();
        categoryService = DefaultCategoryService.getInstance();
        customerCategoryService = DefaultCustomerCategoryService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER);

        if (user != null) {
            List<Mail> mails = mailService.getAllUserMails(user);
            Set<Category> categories = categoryService.getAllCategories();
            Set<CustomerCategory> customerCategories = customerCategoryService.getAllCustomerCategories(user);

            request.setAttribute(CUSTOM_CATEGORIES_ATTRIBUTE, customerCategories);
            request.setAttribute(MAILS_ATTRIBUTE, mails);
            request.setAttribute(CATEGORIES_ATTRIBUTE, categories);
        }
        return PathManager.getProperty("path.page.main");
    }
}