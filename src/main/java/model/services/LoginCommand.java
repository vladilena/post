package model.services;

import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Validation validation = new Validation();
        String resultPage = null;

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (validation.isLoginValid(email) && validation.isPasswordValid(password)) {
            DAOFactory factory = DAOFactory.getInstance();
            UserDAO userDAO = factory.getUserDAO();
            User user = userDAO.getUser(email, password);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                resultPage = "controller?action=main";
            } else {
                request.setAttribute("notExists", "Sorry, there are no such user in database. Try again");
                resultPage = "login.jsp";
            }
        } else {
            request.setAttribute("wrongInput", "Incorrect data. Try again");
            resultPage = "login.jsp";
        }
        return resultPage;
    }
}
