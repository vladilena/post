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


public class FindMailByTitleCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindMailByTitleCommand.class);

    private static MailService mailService;
    private static Validation validation;

    public FindMailByTitleCommand() {
        mailService = DefaultMailService.getInstance();
        validation = DefaultValidation.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER);
        String title = request.getParameter(TITLE);

        if (inputChecked(title)) {
            logger.info("Title is valid");
            List<Mail> mails = mailService.getMailByTitle(title, currentUser);
            if (mails != null) {

                request.setAttribute(MAILS_ATTRIBUTE, mails);
            } else {
                logger.debug("No mail with such attributes");
                request.setAttribute(NOINFO_ATTRIBUTE, true);
            }
        } else {
            logger.debug("Title is invalid");
            request.setAttribute(WRONG_INPUT_ATTRIBUTE, true);
        }

        return "showcategory.jsp";
    }

    private boolean inputChecked(String title) {
        return title != null && validation.isTitleValid(title);
    }
}

