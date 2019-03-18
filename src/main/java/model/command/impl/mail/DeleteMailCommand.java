package model.command.impl.mail;


import model.entity.Mail;
import model.entity.User;
import model.command.Command;
import model.services.MailService;
import model.services.impl.DefaultMailService;
import model.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static model.command.CommandConstants.*;

public class DeleteMailCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteMailCommand.class);

    private static MailService mailService;

    public DeleteMailCommand() {
        mailService = DefaultMailService.getInstance();
    }


    @Override
    public String execute(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER);
        long mailId = Long.parseLong(request.getParameter(ID));

        if (deleteMail(mailId)) {
            logger.info("Mail was deleted");
            List<Mail> mails = mailService.getAllUserMails(currentUser);
            request.setAttribute(MAILS_ATTRIBUTE, mails);
        } else {
            logger.debug("Mail wasn't deleted");
            request.setAttribute(DELETE_PROBLEM, true);
        }
        return PathManager.getProperty("path.page.redirect.main");
    }

    private boolean deleteMail(long mailId) {
        return mailService.deleteMail(mailId);
    }
}
