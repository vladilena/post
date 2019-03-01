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

        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        Set<User> users = userDAO.getAllUsers();
        int add = 0;

        if (!users.contains(user)) {
            add = userDAO.addUser(user);
            System.out.println(add + " size " + userDAO.getAllUsers().size());
        }

        if (add == 0) {
            request.setAttribute("notAdd", "This user exists");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }

        String result = (add == 0) ? "register.jsp" : "controller?action=main";
        return result;
    }
}
