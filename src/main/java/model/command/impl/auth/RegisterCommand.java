package model.command.impl.auth;

import controller.validation.DefaultValidation;
import model.entity.User;
import model.command.Command;
import model.services.UserService;
import model.services.impl.DefaultUserService;
import controller.validation.Validation;
import model.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static model.command.CommandConstants.*;
import static model.command.CommandConstants.NOT_ADD_ATTRIBUTE;

public class RegisterCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegisterCommand.class);

    private static UserService userService;
    private static Validation validation;

    public RegisterCommand() {
        userService = DefaultUserService.getInstance();
        validation = DefaultValidation.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String resultPage = PathManager.getProperty("path.page.registration");

        User user = getUserFromRequest(request);


        if (inputChecked(user)) {
            logger.info("User are valid");
            if (createUser(user)) {
                logger.info("User was added to a database");
                resultPage = PathManager.getProperty("path.page.redirect.main");
                request.getSession().setAttribute(SUCCESS_REGISTRATION_ATTRIBUTE, true);
            } else {
                logger.debug("User wasn't added to a database");
                request.getSession().setAttribute(NOT_ADD_ATTRIBUTE, true);
            }
        } else {
            logger.debug("User are invalid");
            request.getSession().setAttribute(WRONG_INPUT_ATTRIBUTE, true);
        }
        return resultPage;
    }

    private User getUserFromRequest(HttpServletRequest request){
        User user = new User();
        user.setEmail(request.getParameter(EMAIL));
        user.setPassword(request.getParameter(PASSWORD));
        user.setFirstName(request.getParameter(FIRST_NAME));
        user.setLastName(request.getParameter(LAST_NAME));
return user;
    }
    private boolean inputChecked(User user) {
        return user.getEmail() != null && user.getPassword() != null &&
                user.getFirstName() != null &&
                user.getLastName() != null &&
                validation.isLoginValid(user.getEmail()) &&
                validation.isPasswordValid(user.getPassword()) &&
                validation.isNameValid(user.getFirstName()) &&
                validation.isNameValid(user.getLastName());
    }

    private boolean createUser(User user) {
        boolean result = false;
        Set<String> emails = userService.getAllUsersEmails();
        if (!emails.contains(user.getEmail())) {
            result = userService.create(user);
        }
        return result;
    }
}
