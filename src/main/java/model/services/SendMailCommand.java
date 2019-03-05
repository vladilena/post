package model.services;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SendMailCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Validation validation = new Validation();
        User user = (User) request.getSession().getAttribute("user");
        String resultPage = "sendmail.jsp";

        String sender = user.getEmail();
        String recipient = request.getParameter("recipient");
        String title = request.getParameter("title");
        String tags = request.getParameter("tags");
        String message = request.getParameter("message");
        int relatedUser = user.getId();

        if (!validation.isRecipientEmailValid(recipient)) {
            request.setAttribute("invalidRecipient", "Incorrect recipient data. Try again");
        } else if (!validation.isTitleValid(title)) {
            request.setAttribute("invalidTitle", "Incorrect title. Try again");
        } else if (!validation.isTagsValid(tags)) {
            request.setAttribute("invalidTags", "Incorrect tags. Try again");
        } else if (!validation.isMessageValid(message)) {
            request.setAttribute("invalidMessage", "Incorrect message. Try again");
        }else {
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

            List<Mail> mails = mailDAO.getAllMailsWithoutParams();
            if (!mails.contains(mail)) {
                int add = mailDAO.sendMail(mail);}
            else {
                request.setAttribute("problem", "Message hasn't been send. Try later");
            }
            resultPage = "successful.jsp";
        }
        return resultPage;
    }
}


