package model.services;

import model.dao.DAOFactory;
import model.dao.MailDAO;
import model.entity.Mail;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetMailByCategoryCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        User currentUser = (User) request.getSession().getAttribute("user");
        DAOFactory daoFactory = DAOFactory.getInstance();
        MailDAO mailDAO = daoFactory.getMailDAO();

        String category = request.getParameter("categoryName");
        List <Mail> mails = mailDAO.getMailByCategory(category, currentUser);

//        HttpSession session = request.getSession();
//        session.setAttribute("mails", mails);
//        session.setAttribute("user", currentUser);

        request.setAttribute("mails", mails);
        request.setAttribute("user", currentUser);

     //   return "controller?action=main";
        return "showcategory.jsp";
    }
}


