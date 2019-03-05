package model.services;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ReportedSpamCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("user");
        int messageId = Integer.valueOf(request.getParameter("id"));
        String categoryForChange = "spam";

        DAOFactory daoFactory = DAOFactory.getInstance();
        MailDAO mailDAO = daoFactory.getMailDAO();
        mailDAO.changeCategory(messageId, categoryForChange);
        List<Mail> mails = mailDAO.getAllMails(currentUser);

        HttpSession session = request.getSession();
        session.setAttribute("mails", mails);

        return "controller?action=main";
    }
}




