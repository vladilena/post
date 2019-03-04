package model.services;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

public class ReportedSpamCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String categoryForChange = request.getParameter("spam");
        int result = 0;

        User currentUser = (User) request.getSession().getAttribute("user");
        int messageId = Integer.valueOf(request.getParameter("id"));
        String sender = request.getParameter("sender");
        String recipient = request.getParameter("recipient");
        Timestamp dateTime = Timestamp.valueOf(request.getParameter("dateTime"));
        String title = request.getParameter("title");
        String tags = request.getParameter("tags");
        String category = request.getParameter("category");
        String message = request.getParameter("message");
        int relatedUser = Integer.valueOf(request.getParameter("related_user"));

        Mail mail = new Mail();
        mail.setId(messageId);
        mail.setSender(sender);
        mail.setRecipient(recipient);
        mail.setDateTime(dateTime);
        mail.setTitle(title);
        mail.setTags(tags);
        mail.setCategory(category);
        mail.setMessage(message);
        mail.setRelatedUser(relatedUser);

        DAOFactory daoFactory = DAOFactory.getInstance();
        MailDAO mailDAO = daoFactory.getMailDAO();

        List<Mail> mails = mailDAO.getAllMails(currentUser);

        result = mailDAO.changeCategory (mail, categoryForChange);

        if (result == 0){
            request.setAttribute("notAdd", "Not such category");}
        else {
            HttpSession session = request.getSession();
            session.setAttribute("mails", mails);
        }

        return "controller?action=main";
    }
}




