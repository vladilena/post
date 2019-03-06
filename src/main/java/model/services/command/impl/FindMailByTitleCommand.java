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


public class FindMailByTitleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Validation validation = new Validation();
        List<Mail> mails = null;

        User currentUser = (User) request.getSession().getAttribute(USER);
        String title =  request.getParameter(TITLE);

        if(validation.isTitleValid(title)){
            DAOFactory daoFactory = DAOFactory.getInstance();
            MailDAO mailDAO = daoFactory.getMailDAO();

            mails = mailDAO.getMailByTitle(title,currentUser);
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

