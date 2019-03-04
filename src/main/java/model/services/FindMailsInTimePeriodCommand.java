package model.services;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

public class FindMailsInTimePeriodCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {

        DAOFactory daoFactory = DAOFactory.getInstance();
        MailDAO mailDAO = daoFactory.getMailDAO();


        User currentUser = (User) request.getSession().getAttribute("user");

        String from = request.getParameter("start");
        String to = request.getParameter("finish");

//2019-12-02T12:30
        List<Mail> mails = mailDAO.getMailByTimePeriod(from, to ,currentUser);

        if (mails.size()==0){
            request.setAttribute("notFind", "No messages with in this period");
        }else {
            request.setAttribute("mails", mails);
            request.setAttribute("user", currentUser);}

        return "showcategory.jsp";

    }
}


