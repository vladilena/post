package model.command.impl.find;

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
import java.util.List;

import static model.command.CommandConstants.*;

public class FindMailBySenderOrRecipient implements Command {
    private static final Logger logger = LogManager.getLogger(FindMailBySenderOrRecipient.class);

    private static MailService mailService;
    private static Validation validation;

    public FindMailBySenderOrRecipient() {
        mailService = DefaultMailService.getInstance();
        validation = DefaultValidation.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER);
        String email = request.getParameter(EMAIL);
        if (inputChecked(email)) {
            logger.info("Email is valid");
            List<Mail> mails = mailService.getMailBySenderOrRecipient(email, currentUser);
            if (mails != null) {
                request.setAttribute(MAILS_ATTRIBUTE, mails);
            } else {
                logger.debug("No mail with such attributes");
                request.setAttribute(NOINFO_ATTRIBUTE, true);
            }
        } else {
            logger.debug("Email is invalid");
            request.setAttribute(WRONG_INPUT_ATTRIBUTE, true);
        }
        return "showcategory.jsp";
    }

    private boolean inputChecked(String email) {
        return email != null && validation.isLoginValid(email);
    }
}
