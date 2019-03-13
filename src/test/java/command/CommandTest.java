package command;

import model.entity.CustomerCategory;
import model.entity.User;
import model.command.Command;
import model.command.impl.category.CreateCategoryCommand;
import model.command.impl.auth.LoginCommand;
import model.command.impl.auth.LogoutCommand;
import model.command.impl.auth.RegisterCommand;
import model.services.CustomerCategoryService;
import model.services.UserService;
import model.services.impl.DefaultCustomerCategoryService;
import model.services.impl.DefaultUserService;
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

        UserService userService = DefaultUserService.getInstance();
        Set<User> users = userService.getAllUsers();
        int currentSize = users.size();

        Command command = new RegisterCommand();
        String page = command.execute(wrapper);

        users = userService.getAllUsers();
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

        CustomerCategoryService customerCategoryService = DefaultCustomerCategoryService.getInstance();
        Set<CustomerCategory> categories = customerCategoryService.getAllCustomerCategories(user);
        int oldSize = categories.size();

        command.execute(wrapper);

        categories = customerCategoryService.getAllCustomerCategories(user);
        int newSize = categories.size();

        Assert.assertEquals(oldSize + 1, newSize);
    }

}


