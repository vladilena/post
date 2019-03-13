package model.command.impl.find;

import model.entity.Mail;
import model.entity.User;
import model.command.Command;
import model.services.MailService;
import model.services.impl.DefaultMailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static model.command.CommandConstants.*;
import static model.command.CommandConstants.USER;

public class FindMailByCategoryCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindMailByCategoryCommand.class);

    private static MailService mailService;

    public FindMailByCategoryCommand (){
        mailService = DefaultMailService.getInstance();
    }


    @Override
    public String execute(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER);
        String category = request.getParameter(CATEGORY_NAME);
        List<Mail> mails = getMailByCategory(category, currentUser);
        if (mails!=null){
            request.setAttribute(MAILS_ATTRIBUTE, mails);
        }else {
            logger.info("No mail with such attributes");
            request.setAttribute(NOINFO_ATTRIBUTE,true);
        }

       return  "showcategory.jsp";
    }
    private List<Mail> getMailByCategory (String category, User user){
        return mailService.getMailByCategory(category,user);
    }
}
