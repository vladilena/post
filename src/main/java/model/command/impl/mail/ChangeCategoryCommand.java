package model.command.impl.mail;

import model.entity.Mail;
import model.entity.User;
import model.command.Command;
import model.services.MailService;
import model.services.impl.DefaultMailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.util.List;

import static model.command.CommandConstants.*;

public class ChangeCategoryCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeCategoryCommand.class);

    private static MailService mailService;

    public ChangeCategoryCommand() {
        mailService = DefaultMailService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String categoryForChange = request.getParameter(CATEGORY_FOR_CHANGE);
        User currentUser = (User) request.getSession().getAttribute(USER);
        long mailId = Long.parseLong(request.getParameter(ID));

        if (mailService.changeCategory(mailId, categoryForChange, currentUser)) {
            logger.info("Category was changed");
            List<Mail> mails = mailService.getAllUserMails(currentUser);
            request.setAttribute(MAILS_ATTRIBUTE, mails);
        } else {
            logger.debug("Category wasn't changed");
            request.setAttribute(PROBLEM_WITH_CATEGORY_CHANGE, true);
        }
        return "controller?action=main";
    }
}
