package model.command.impl.mail;

import model.dao.factory.DAOFactory;
import model.dao.impl.MailDAO;
import model.entity.Mail;
import model.entity.User;
import model.command.Command;
import model.services.MailService;
import model.services.impl.DefaultMailService;
import model.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static model.command.CommandConstants.*;

public class ReportedSpamCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ReportedSpamCommand.class);

    private static MailService mailService;

    public ReportedSpamCommand() {
        mailService = DefaultMailService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER);
        long messageId = Long.parseLong(request.getParameter(ID));

        if(mailService.reportedSpam(messageId)) {
            logger.info("Spam was reported");
            List<Mail> mails = mailService.getAllUserMails(currentUser);
            request.setAttribute(MAILS_ATTRIBUTE, mails);
        }else {
            logger.debug("Spam wasn't reported");
            request.setAttribute(PROBLEM_WITH_REPORTED_SPAM,true);
        }
        return PathManager.getProperty("path.page.redirect.main");
    }
}

