package model.command.impl.find;

import controller.validation.DefaultValidation;
import model.entity.Mail;
import model.entity.User;
import model.command.Command;
import model.services.MailService;
import model.services.impl.DefaultMailService;
import controller.validation.Validation;
import model.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static model.command.CommandConstants.*;

public class FindMailByTagCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindMailByTagCommand.class);

    private static MailService mailService;
    private static Validation validation;

    public FindMailByTagCommand() {
        mailService = DefaultMailService.getInstance();
        validation = DefaultValidation.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER);
        String tag = request.getParameter(TAG);

        if (inputChecked(tag)) {
            logger.info("Tag is valid");
            List<Mail> mails = mailService.getMailByTag(tag, currentUser);
            if (mails != null) {
                request.setAttribute(MAILS_ATTRIBUTE, mails);
            } else {
                logger.debug("No mail with such attributes");
                request.setAttribute(NOINFO_ATTRIBUTE, true);
            }
        } else {
            logger.debug("Tag is invalid");
            request.setAttribute(WRONG_INPUT_ATTRIBUTE, true);
        }
        return PathManager.getProperty("path.page.show.category");
    }

    private boolean inputChecked(String tag) {
        return tag != null && validation.isTagValid(tag);
    }
}

