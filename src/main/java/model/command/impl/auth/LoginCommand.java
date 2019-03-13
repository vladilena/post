package model.command.impl.auth;

import controller.validation.DefaultValidation;
import model.entity.User;
import model.command.Command;
import model.services.LoginService;
import model.services.impl.DefaultLoginService;
import controller.validation.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static model.command.CommandConstants.*;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    private static LoginService loginService;
    private static Validation validation;

    public LoginCommand() {
        loginService = DefaultLoginService.getInstance();
        validation = DefaultValidation.getInstance();
    }


    @Override
    public String execute(HttpServletRequest request) {
        String resultPage = "login.jsp";

        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        User user = getUser(email, password);
        if (user != null) {
            logger.info("user exists");
            request.getSession().setAttribute(USER, user);
            resultPage = "controller?action=main";
        } else {
            logger.info("user not exists");
            request.setAttribute(NOT_EXISTS_ATTRIBUTE, true);
        }
    return resultPage;
    }

    private User getUser(String email, String password) {
        User user = null;
        if (checkLoginAndPassword(email, password)) {
            logger.info("Login and password are valid");
            user = loginService.login(email, password);
        }
        return user;
    }

    private boolean checkLoginAndPassword(String email, String password) {
        return email != null && password != null && validation.isUserValid(email, password);
    }
}
