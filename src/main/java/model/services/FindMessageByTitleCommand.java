package model.services;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;


public class FindMessageByTitleCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MailDAO mailDAO = daoFactory.getMailDAO();


        User currentUser = (User) request.getSession().getAttribute("user");
        String title =  request.getParameter("title");
        List<Mail> mails = mailDAO.getMailByTitle(title,currentUser);

        if (mails.size()==0){
            request.setAttribute("notFind", "No messages with this tag");
        }else {
            request.setAttribute("mails", mails);
            request.setAttribute("user", currentUser);}

        return "showcategory.jsp";
    }
}


