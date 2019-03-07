package model.services.command.impl;


import model.dao.impl.CategoryDAO;
import model.dao.impl.DAOFactory;
import model.dao.impl.MailDAO;
import model.entity.Mail;
import model.entity.User;
import model.services.command.Command;
import model.services.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static model.services.command.CommandConstants.*;

public class SendMailCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
       Validation validation = new Validation();
        User user = (User) request.getSession().getAttribute(USER);
        String resultPage = "sendmail.jsp";

        String sender = user.getEmail();
        String recipient = request.getParameter(RECIPIENT);
        String title = request.getParameter(TITLE);
        String tags = request.getParameter(TAGS);
        String message = request.getParameter(MESSAGE);

        if (!validation.isRecipientEmailValid(recipient)) {
            request.setAttribute(INVALID_RECIPIENT_ATTRIBUTE, true);
        } else if (!validation.isTitleValid(title)) {
            request.setAttribute(INVALID_TITLE_ATTRIBUTE, true);
        } else if (!validation.isTagsValid(tags)) {
            request.setAttribute(INVALID_TAGS_ATTRIBUTE, true);
        } else if (!validation.isMessageValid(message)) {
            request.setAttribute(INVALID_MESSAGE_ATTRIBUTE, true);
        }else {
            Mail mail = new Mail();

            mail.setSender(sender);
            mail.setRecipient(recipient);
            mail.setTitle(title);
            mail.setTags(new ArrayList<>(Arrays.asList(tags.split("\\s*,\\s*"))));
            mail.setMessage(message);
            mail.setRelatedUser(user);

            DAOFactory factory = DAOFactory.getInstance();
            MailDAO mailDAO = factory.getMailDAO();

            List<Mail> mails = mailDAO.getAllMailsWithoutParams();
            if (!mails.contains(mail)) {
                int add = mailDAO.sendMail(mail);}
            else {
                request.setAttribute(PROBLEM_ATTRIBUTE, true);
            }
            resultPage = "successful.jsp";
        }
        return resultPage;
    }
}


