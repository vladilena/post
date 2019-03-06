package model.services.command.impl;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;
import model.services.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static model.services.command.CommandConstants.*;

public class DeleteMailCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {


        User currentUser = (User) request.getSession().getAttribute(USER);

        int messageId = Integer.valueOf(request.getParameter(ID));

        DAOFactory daoFactory = DAOFactory.getInstance();
        MailDAO mailDAO = daoFactory.getMailDAO();

        mailDAO.deleteMail(messageId);
        List<Mail> mails = mailDAO.getAllMails(currentUser);

        HttpSession session = request.getSession();
        session.setAttribute(MAILS_ATTRIBUTE, mails);

        return "controller?action=main";
    }
}


