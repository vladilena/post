package model.services.command.impl;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;
import model.services.command.Command;
import model.services.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static model.services.command.CommandConstants.*;

public class FindMailInTimePeriodCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Validation validation = new Validation();

        User currentUser = (User) request.getSession().getAttribute(USER);
        String from = request.getParameter(START);
        String to = request.getParameter(FINISH);

        if (validation.isDateTimeValid(from) && validation.isDateTimeValid(to)) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            MailDAO mailDAO = daoFactory.getMailDAO();
            List<Mail> mails = mailDAO.getMailByTimePeriod(from, to, currentUser);
           if (mails.size() == 0) {
               request.setAttribute(NOINFO_ATTRIBUTE, true);
           }else {
               request.setAttribute(MAILS_ATTRIBUTE, mails);
               request.setAttribute(USER, currentUser);
           }
        } else {
            request.setAttribute(WRONG_INPUT_ATTRIBUTE, true);
        }
        return "showcategory.jsp";
    }
}


