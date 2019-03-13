package model.services;

import model.entity.Mail;
import model.entity.User;

import java.util.List;

public interface MailService {

    /**
     * Method to add {@link Mail} to a DB
     * @param mail is a {@link Mail} which will be stored in DB
     *
     * @return return {@code true} if {@link Mail} stored successful
     * else return {@code false}
     */
    boolean sendMail (Mail mail);

    /**
     * Method to get all mails which are related to {@link User}
     * @param user related user
     *
     * @return return {@link List} of {@link Mail}
     */
    List <Mail> getAllUserMails (User user);

    /**
     * Method to get all mails from DB
     *
     * @return return {@link List} of {@link Mail}
     */
    List <Mail> getAllMailsWithoutParams ();

    /**
     * Method to get all mails which are related to {@link User} and
     * {@link model.entity.CustomerCategory}
     * @param user related user
     * @param category category name
     *
     * @return return {@link List} of {@link Mail}
     */
    List<Mail> getMailByCustomCategory(String category, User user);

    /**
     * Method to get all mails which are related to {@link User} and
     * {@link model.entity.Category}
     * @param user related user
     * @param category category name
     *
     * @return return {@link List} of {@link Mail}
     */
    List<Mail> getMailByCategory(String category, User user);

    /**
     * Method to get all mails which are related to {@link User} and
     * was send in time period from {@code from} to {@code to}
     * @param user related user
     * @param from start time
     * @param to end time
     *
     * @return return {@link List} of {@link Mail}
     */
    List<Mail> getMailByTimePeriod(String from, String to, User user);
    /**
     * Method to remove mail from DB
     * @param mailId mail id to remove
     *
     * @return return {@link true} if delete was successful and else {@link false}
     */
    boolean deleteMail (long mailId);

    /**
     * Method to change {@link java.util.Locale.Category} to spam
     * @param mailId mail id to change category
     *
     * @return return {@link true} if changing was successful and else {@link false}
     */
    boolean reportedSpam (long mailId);

    /**
     * Method to change {@link model.entity.CustomerCategory} to customer
     * @param mailId mail id to change category
     * @param category category name for changing
     * @param user related user
     *
     * @return return {@link true} if changing was successful and else {@link false}
     */
    boolean changeCategory (long mailId, String category, User user);

    /**
     * Method to get all mails which are related to {@link User} and
     * {@code title}
     * @param user related user
     * @param title title name
     *
     * @return return {@link List} of {@link Mail}
     */
    List<Mail> getMailByTitle (String title, User user);

    /**
     * Method to get all mails which are related to {@link User} and
     * {@code tag}
     * @param user related user
     * @param tag tag name
     *
     * @return return {@link List} of {@link Mail}
     */
    List<Mail> getMailByTag(String tag, User user);

    /**
     * Method to get all mails which are related to {@link User} and
     * {@code title}
     * @param user related user
     * @param email sender or recipient email
     *
     * @return return {@link List} of {@link Mail}
     */
    List<Mail> getMailBySenderOrRecipient(String email, User user);




}
