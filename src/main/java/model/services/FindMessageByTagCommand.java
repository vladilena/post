package model.services;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindMessageByTagCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MailDAO mailDAO = daoFactory.getMailDAO();


        User currentUser = (User) request.getSession().getAttribute("user");
        String tag =  request.getParameter("tag");
        List<Mail> mails = mailDAO.getMailByTag(tag,currentUser);

        if (mails.size()==0){
            request.setAttribute("notFind", "No messages with this tag");
        }else {
        request.setAttribute("mails", mails);
        request.setAttribute("user", currentUser);}

        return "showcategory.jsp";
    }
}


