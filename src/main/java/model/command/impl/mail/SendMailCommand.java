package model.command.impl.mail;


import controller.MainServlet;
import controller.validation.DefaultValidation;
import model.entity.Mail;
import model.entity.User;
import model.command.Command;
import model.services.MailService;
import model.services.impl.DefaultMailService;
import controller.validation.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;


import static model.command.CommandConstants.*;

public class SendMailCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SendMailCommand.class);

    private static MailService mailService;
    private static Validation validation;

    public SendMailCommand() {
        mailService = DefaultMailService.getInstance();
        validation = DefaultValidation.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String resultPage = "sendmail.jsp";


        Mail mail = getMailFromRequest(request);

        if (inputChecked(mail)) {
            logger.info("Mail is valid");
            if (mailService.sendMail(mail)) {
                logger.info("Mail was sent");
                resultPage = "successful.jsp";
            } else {
                logger.debug("Mail wasn't sent");
                request.setAttribute(PROBLEM_ATTRIBUTE, true);
            }
        } else {
            logger.debug("Mail is invalid");
            request.setAttribute(WRONG_INPUT_ATTRIBUTE, true);
        }
        return resultPage;
    }

    private Mail getMailFromRequest (HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(USER);
        Mail mail = new Mail();
        mail.setSender(user.getEmail());
        mail.setRecipient(request.getParameter(RECIPIENT));
        mail.setTitle(request.getParameter(TITLE));
        mail.setTags(new ArrayList<>(Arrays.asList(request.getParameter(TAGS).split("\\s*,\\s*"))));
        mail.setMessage(request.getParameter(MESSAGE));
        mail.setRelatedUser(user);
        return mail;}

    private boolean inputChecked(Mail mail) {
        return mail.getRecipient() != null && mail.getTitle() != null && mail.getTags() != null && mail.getMessage() != null &&
                validation.isLoginValid(mail.getRecipient()) &&
                validation.isTitleValid(mail.getTitle()) &&
                validation.isTagsValid(mail.getTags().toString()) &&
                validation.isMessageValid(mail.getMessage());
    }

}


