package model.services.command.impl;

import model.dao.impl.DAOFactory;
import model.dao.impl.MailDAO;
import model.entity.Mail;
import model.entity.User;
import model.services.command.Command;
import model.services.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static model.services.command.CommandConstants.*;

public class FindMailByTagCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Validation validation = new Validation();
        List<Mail> mails = null;

        User currentUser = (User) request.getSession().getAttribute(USER);
        String tag =  request.getParameter(TAG);

        if(validation.isTagsValid(tag)){
            DAOFactory daoFactory = DAOFactory.getInstance();
            MailDAO mailDAO = daoFactory.getMailDAO();
            mails = mailDAO.getMailByTag(tag,currentUser);
            if(mails.size()==0){
                request.setAttribute(NOINFO_ATTRIBUTE, true);
            }else {
                request.setAttribute(MAILS_ATTRIBUTE, mails);
                request.setAttribute(USER, currentUser);
            }
        }else {
            request.setAttribute(WRONG_INPUT_ATTRIBUTE, true);
        }
        return "showcategory.jsp";
    }
}


