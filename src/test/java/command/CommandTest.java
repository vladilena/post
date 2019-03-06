package command;

import model.dao.impl.CustomerCategoryDAO;
import model.dao.impl.DAOFactory;
import model.dao.impl.UserDAO;
import model.entity.CustomerCategory;
import model.entity.User;
import model.services.command.Command;
import model.services.command.impl.CreateCategoryCommand;
import model.services.command.impl.LoginCommand;
import model.services.command.impl.LogoutCommand;
import model.services.command.impl.RegisterCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;
import java.util.Set;

public class CommandTest {

    RequestWrapper wrapper;

    @Before
    public void init() {
        wrapper = new RequestWrapper();
    }

    @Test
    public void testRegisterCommand() {
        wrapper.addParam("email", "abricos@ukr.net");
        wrapper.addParam("password", "abricos");
        wrapper.addParam("first_name", "Мар'яна");
        wrapper.addParam("last_name", "Лещенко");

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        Set<User> users = userDAO.getAllUsers();
        int currentSize = users.size();

        Command command = new RegisterCommand();
        String page = command.execute(wrapper);

        users = userDAO.getAllUsers();
        int newSize = users.size();

        System.out.println(page);

        Assert.assertEquals(currentSize + 1, newSize);
    }

    @Test
    public void testLogin() {
        wrapper.addParam("email", "admin@ukr.net");
        wrapper.addParam("password", "admin");

        Command command = new LoginCommand();
        String errorPage = "login.jsp";
        String page = command.execute(wrapper);

        HttpSession session = wrapper.getSession();
        User user = (User) session.getAttribute("user");

        Assert.assertNotNull(user);
        Assert.assertNotEquals(page, errorPage);
    }

    @Test
    public void testLogout() {
        HttpSession session = wrapper.getSession();
        session.setAttribute("user", new User());

        Command command = new LogoutCommand();
        command.execute(wrapper);

        User user = (User) session.getAttribute("user");
        Assert.assertNull(user);
    }

    @Test
    public void testCreateCategory() {
        User user = new User();
        user.setId(1);
        wrapper.getSession().setAttribute("newcategory", "something123");
        wrapper.getSession().setAttribute("user", user);

        Command command = new CreateCategoryCommand();

        DAOFactory factory = DAOFactory.getInstance();
        CustomerCategoryDAO customerCategoryDAO = factory.getCustomerCategoryDAO();
        Set<CustomerCategory> categories = customerCategoryDAO.getAllCustomerCategories(user);
        int oldSize = categories.size();

        command.execute(wrapper);

        categories = customerCategoryDAO.getAllCustomerCategories(user);
        int newSize = categories.size();

        Assert.assertEquals(oldSize + 1, newSize);
    }

}


