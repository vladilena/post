package model.services;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class SendMailCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String sender = request.getParameter("sender");
        String recipient = request.getParameter("recipient");
        String title = request.getParameter("title");
        String tags = request.getParameter("tags");
        String message = request.getParameter("message");

        Mail mail = new Mail();
        //need to be changed!!!!
        mail.setSender(1);
        mail.setRecipient(1);
        ///////////////////
        mail.setTitle(title);
        mail.setTags(tags);
        mail.setCategory(2);//outgoing
        mail.setMessage(message);

        DAOFactory factory = DAOFactory.getInstance();
        MailDAO mailDAO = factory.getMailDAO();

        List <Mail> mails = mailDAO.getAllMails();
        int add = 0;

/////////////////////CONTINUE HERE
        if (!mails.contains(mail)) {
            add = mailDAO.sendMail(mail);
            System.out.println(add + " size " + mailDAO.getAllMails().size());
        }

            HttpSession session = request.getSession();
            session.setAttribute("mail", mail);


        String result = (add == 0) ? "sendmail.jsp" : "successful.jsp";
        return result;
    }
}


