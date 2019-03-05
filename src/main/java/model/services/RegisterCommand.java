package model.services;

import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

import static model.dao.GlobalConstants.Columns.*;

public class RegisterCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Validation validation = new Validation();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        String resultPage = "register.jsp";

        if(!validation.isLoginValid(email)){
            request.setAttribute("invalidlogin", "Wrong email format. Try again");
        }else if(!validation.isPasswordValid(password)){
            request.setAttribute("invalidPassword", "Wrong password format. Try again");
        }else if(!validation.isNameValid(firstName)){
            request.setAttribute("invalidFirstName", "Wrong first name format. Try again");
        }else if (!validation.isNameValid(lastName)){
            request.setAttribute("invalidLastName", "Wrong last name format. Try again");
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
                session.setAttribute("user", user);
                resultPage = "controller?action=main";
            }else {
                request.setAttribute("notAdd", "User with this email is already exists");
            }
        }
        return resultPage;
    }
}
