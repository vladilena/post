package model.services.command.impl;

import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.entity.User;
import model.services.command.Command;
import model.services.validation.Validation;

import javax.servlet.http.HttpServletRequest;

import static model.services.command.CommandConstants.*;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Validation validation = new Validation();
        String resultPage = null;

        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        if (validation.isLoginValid(email) && validation.isPasswordValid(password)) {
            DAOFactory factory = DAOFactory.getInstance();
            UserDAO userDAO = factory.getUserDAO();
            User user = userDAO.getUser(email, password);
            if (user != null) {
                request.getSession().setAttribute(USER, user);
                resultPage = "controller?action=main";
            } else {
                request.setAttribute(NOT_EXISTS_ATTRIBUTE, true);
                resultPage = "login.jsp";
            }
        } else {
            request.setAttribute(WRONG_INPUT_ATTRIBUTE, true);
            resultPage = "login.jsp";
        }
        return resultPage;
    }
}
