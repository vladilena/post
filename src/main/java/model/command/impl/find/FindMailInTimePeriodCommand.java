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

public class FindMailInTimePeriodCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindMailInTimePeriodCommand.class);

    private static MailService mailService;
    private static Validation validation;

    public FindMailInTimePeriodCommand() {
        mailService = DefaultMailService.getInstance();
        validation = DefaultValidation.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER);
        String from = request.getParameter(START);
        String to = request.getParameter(FINISH);

        if (inputChecked(from, to)){
            logger.info("Time parameters are valid");
            List<Mail>mails = mailService.getMailByTimePeriod(from, to, currentUser);
            if (mails!=null){
                request.setAttribute(MAILS_ATTRIBUTE, mails);

            }else {
                logger.debug("No mail with such attributes");
                request.setAttribute(NOINFO_ATTRIBUTE, true);
            }
        }else {
            logger.debug("Time parameters are invalid");
            request.setAttribute(WRONG_INPUT_ATTRIBUTE, true);
        }
        return PathManager.getProperty("path.page.show.category");
    }
    private boolean inputChecked (String from, String to) {
        return from!=null && to!=null & validation.isDateTimeValid(from) && validation.isDateTimeValid(to);
    }
}
