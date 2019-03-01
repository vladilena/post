package model.services;

import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        User user = userDAO.getUser(email, password);
        String resultPage = (user == null) ? "login.jsp" : "controller?action=main";

        if (user == null) {
            request.setAttribute("notExists", "This user not exists");
        } else {
            request.getSession().setAttribute("user", user);
        }

        return resultPage;

    }
}
