package model.services.impl;

import model.dao.factory.DAOFactory;
import model.dao.impl.UserDAO;
import model.entity.User;
import model.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class DefaultUserService implements UserService {
    private static final Logger logger = LogManager.getLogger("DefaultUserService");

    private static volatile UserService userService;
    private static UserDAO userDAO;

    private DefaultUserService() {
        userDAO = DAOFactory.getInstance().getUserDAO();
    }

    public static UserService getInstance() {
        UserService localInstance = userService;
        if (localInstance == null) {
            synchronized (DefaultUserService.class) {
                localInstance = userService;
                if (localInstance == null) {
                    userService = new DefaultUserService();
                    logger.debug("Create DefaultUserService instance");
                }
            }
        }
        logger.debug("Return DefaultUserService instance");
        return userService;
    }

    @Override
    public boolean create(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public Set<String> getAllUsersEmails() {
        return userDAO.getAllEmails();
    }

    @Override
    public Set<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return userDAO.getUser(email, password);
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }
}


