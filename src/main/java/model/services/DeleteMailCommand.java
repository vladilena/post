package model.services;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

public class DeleteMailCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("user");

        int messageId = Integer.valueOf(request.getParameter("id"));

        DAOFactory daoFactory = DAOFactory.getInstance();
        MailDAO mailDAO = daoFactory.getMailDAO();

        mailDAO.deleteMail(messageId);
        List<Mail> mails = mailDAO.getAllMails(currentUser);

        HttpSession session = request.getSession();
        session.setAttribute("mails", mails);

        return "controller?action=main";
    }
}


