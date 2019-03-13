package model.services.impl;

import model.dao.factory.DAOFactory;
import model.dao.impl.CategoryDAO;
import model.dao.impl.CustomerCategoryDAO;
import model.dao.impl.MailDAO;
import model.entity.Mail;
import model.entity.User;
import model.services.MailService;
import org.apache.logging.log4j.*;

import java.sql.Timestamp;
import java.util.List;

import static model.command.CommandConstants.OUTGOING;
import static model.command.CommandConstants.SPAM;

public class DefaultMailService implements MailService {
    private static final Logger logger = LogManager.getLogger(DefaultMailService.class);

    private static volatile MailService mailService;
    private static MailDAO mailDAO;
    private static CustomerCategoryDAO customerCategoryDAO;
    private static CategoryDAO categoryDAO;

    private DefaultMailService() {
        mailDAO = DAOFactory.getInstance().getMailDAO();
        customerCategoryDAO = DAOFactory.getInstance().getCustomerCategoryDAO();
        categoryDAO = DAOFactory.getInstance().getCategoryDAO();
    }

    public static MailService getInstance() {
        MailService localInstance = mailService;
        if (localInstance == null) {
            synchronized (DefaultUserService.class) {
                localInstance = mailService;
                if (localInstance == null) {
                    mailService = new DefaultMailService();
                    logger.debug("Create DefaultMailService instance");
                }
            }
        }
        logger.debug("Return DefaultMailService instance");
        return mailService;
    }


    @Override
    public boolean sendMail(Mail mail) {
        long categoryId = categoryDAO.getCategoryIdByName(OUTGOING);
        return mailDAO.sendMail(mail, categoryId);
    }

    @Override
    public List<Mail> getAllUserMails(User user) {
        return mailDAO.getAllMails(user.getId());
    }

    @Override
    public List<Mail> getAllMailsWithoutParams() {
        return mailDAO.getAllMailsWithoutParams();
    }

    @Override
    public List<Mail> getMailByCustomCategory(String category, User user) {
        long categoryId = customerCategoryDAO.getCustomerCategoryIdByNameAndUserId(category, user.getId());
        return mailDAO.getMailByCustomCategory(categoryId, user.getId());
    }

    @Override
    public List<Mail> getMailByCategory(String category, User user) {
        long categoryId = categoryDAO.getCategoryIdByName(category);
        return mailDAO.getMailByCategory(categoryId, user.getId());
    }

    @Override
    public List<Mail> getMailByTimePeriod(String from, String to, User user) {
        return mailDAO.getMailByTimePeriod(timeConverter(from),timeConverter(to),user.getId());
    }

    @Override
    public boolean deleteMail(long mailId) {
        return mailDAO.deleteMail(mailId);
    }

    @Override
    public boolean reportedSpam(long mailId) {
        long categoryId = categoryDAO.getCategoryIdByName(SPAM);
        return mailDAO.reportedSpam(mailId, categoryId);
    }

    @Override
    public boolean changeCategory(long mailId, String category, User user) {
        long categoryId = customerCategoryDAO.getCustomerCategoryIdByNameAndUserId(category, user.getId());
        return mailDAO.changeCategory(mailId,categoryId,user.getId());
    }

    @Override
    public List<Mail> getMailByTitle(String title, User user) {
        return mailDAO.getMailByTitle(title, user.getId());
    }

    @Override
    public List<Mail> getMailByTag(String tag, User user) {
        return mailDAO.getMailByTag(tag, user.getId());
    }

    @Override
    public List<Mail> getMailBySenderOrRecipient(String email, User user) {
        return mailDAO.getMailBySenderOrRecipient(email, user.getId());
    }

    private Timestamp timeConverter (String before){
        return Timestamp.valueOf(before.replace("T", " ") + ":00");
    }
}


