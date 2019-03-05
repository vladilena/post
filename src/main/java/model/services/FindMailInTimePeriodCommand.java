package model.services;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

public class FindMailInTimePeriodCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        Validation validation = new Validation();

        User currentUser = (User) request.getSession().getAttribute("user");
        String from = request.getParameter("start");
        String to = request.getParameter("finish");

        if (validation.isDateTimeValid(from) && validation.isDateTimeValid(to)) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            MailDAO mailDAO = daoFactory.getMailDAO();
            List<Mail> mails = mailDAO.getMailByTimePeriod(from, to, currentUser);
           if (mails.size() == 0) {
               request.setAttribute("noInfo", "No messages for this parameters");
           }else {
               request.setAttribute("mails", mails);
               request.setAttribute("user", currentUser);
           }
        } else {
            request.setAttribute("wrongInput", "Wrong input. Try again");
        }
        return "showcategory.jsp";
    }
}


