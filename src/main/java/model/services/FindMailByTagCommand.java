package model.services;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindMailByTagCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Validation validation = new Validation();
        List<Mail> mails = null;

        User currentUser = (User) request.getSession().getAttribute("user");
        String tag =  request.getParameter("tag");

        if(validation.isTagsValid(tag)){
            DAOFactory daoFactory = DAOFactory.getInstance();
            MailDAO mailDAO = daoFactory.getMailDAO();
            mails = mailDAO.getMailByTag(tag,currentUser);
            if(mails.size()==0){
                request.setAttribute("noInfo", "No messages for this parameters");
            }else {
                request.setAttribute("mails", mails);
                request.setAttribute("user", currentUser);
            }
        }else {
            request.setAttribute("wrongInput", "Wrong input. Try again");
        }
        return "showcategory.jsp";
    }
}


