package model.services.command;

import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.entity.User;
import model.services.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

import static model.services.command.CommandConstants.*;
import static model.services.command.CommandConstants.NOT_ADD_ATTRIBUTE;

public class RegisterCommand implements Command {

        @Override
        public String execute(HttpServletRequest request) {
            Validation validation = new Validation();

            String email = request.getParameter(EMAIL);
            String password = request.getParameter(PASSWORD);
            String firstName = request.getParameter(FIRST_NAME);
            String lastName = request.getParameter(LAST_NAME);

            String resultPage = "register.jsp";

            if(!validation.isLoginValid(email)){
                request.setAttribute(INVALID_LOGIN_ATTRIBUTE, true);
            }else if(!validation.isPasswordValid(password)){
                request.setAttribute(INVALID_PASSWORD_ATTRIBUTE, true);
            }else if(!validation.isNameValid(firstName)){
                request.setAttribute(INVALID_FIRST_NAME_ATTRIBUTE, true);
            }else if (!validation.isNameValid(lastName)){
                request.setAttribute(INVALID_LAST_NAME_ATTRIBUTE, true);
            } else {
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setFirstName(firstName);
                user.setLastName(lastName);

                DAOFactory factory = DAOFactory.getInstance();
                UserDAO userDAO = factory.getUserDAO();

                Set<String> emails = userDAO.getAllEmails();

                if(!emails.contains(email)){
                    userDAO.addUser(user);
                    HttpSession session = request.getSession();
                  //  session.setAttribute(USER, user);
                    resultPage = "controller?action=main";
                }else {
                    request.setAttribute(NOT_ADD_ATTRIBUTE, true);
                }
            }
            return resultPage;
        }
}


