package model.services;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

public class SendMailCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        String sender = user.getEmail();
        String recipient = request.getParameter("recipient");
        String title = request.getParameter("title");
        String tags = request.getParameter("tags");
        String message = request.getParameter("message");
        int relatedUser = user.getId();

        Mail mail = new Mail();

        mail.setSender(sender);
        mail.setRecipient(recipient);
        mail.setTitle(title);
        mail.setTags(tags);
        mail.setCategory("outgoing");
        mail.setMessage(message);
        mail.setRelatedUser(relatedUser);


        DAOFactory factory = DAOFactory.getInstance();
        MailDAO mailDAO = factory.getMailDAO();

        List <Mail> mails = mailDAO.getAllMailsWithoutParams();
        int add = 0;


        if (!mails.contains(mail)) {
            add = mailDAO.sendMail(mail);
            System.out.println(add + " size " + mailDAO.getAllMailsWithoutParams().size());
        }

            HttpSession session = request.getSession();
            session.setAttribute("nonAdd", "wrong parameters");


        String result = (add == 0) ? "sendmail.jsp" : "successful.jsp";
        return result;
    }
}


