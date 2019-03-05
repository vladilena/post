package model.services;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChangeCategory implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Validation validation = new Validation();
        String categoryForChange = request.getParameter("categoryForChange");

        int result = 0;

        User currentUser = (User) request.getSession().getAttribute("user");
        int messageId = Integer.valueOf(request.getParameter("id"));

        DAOFactory daoFactory = DAOFactory.getInstance();
        MailDAO mailDAO = daoFactory.getMailDAO();

        result = mailDAO.changeCategory(messageId, categoryForChange);

        List<Mail> mails = mailDAO.getAllMails(currentUser);

        HttpSession session = request.getSession();
        session.setAttribute("mails", mails);

        return "controller?action=main";
    }
}


