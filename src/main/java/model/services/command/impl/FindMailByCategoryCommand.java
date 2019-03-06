package model.services.command.impl;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;
import model.services.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static model.services.command.CommandConstants.*;

public class FindMailByCategoryCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        User currentUser = (User) request.getSession().getAttribute(USER);
        DAOFactory daoFactory = DAOFactory.getInstance();
        MailDAO mailDAO = daoFactory.getMailDAO();

        String category = request.getParameter(CATEGORY_NAME);
        List <Mail> mails = mailDAO.getMailByCategory(category, currentUser);

        request.setAttribute(MAILS_ATTRIBUTE, mails);
        request.setAttribute(USER, currentUser);

        return "showcategory.jsp";
    }
}


