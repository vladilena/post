package model.services.impl;

import model.dao.factory.DAOFactory;
import model.dao.impl.UserDAO;
import model.entity.User;
import model.services.LoginService;
import org.apache.logging.log4j.*;

public class DefaultLoginService implements LoginService {
    private static final Logger logger = LogManager.getLogger(DefaultLoginService.class);

    private static volatile LoginService loginService;
    private static UserDAO userDAO;

    private DefaultLoginService() {
        userDAO = DAOFactory.getInstance().getUserDAO();
    }

    public static LoginService getInstance() {
        LoginService localInstance = loginService;
        if (localInstance == null) {
            synchronized (DefaultLoginService.class) {
                localInstance = loginService;
                if (localInstance == null) {
                    loginService = new DefaultLoginService();
                    logger.debug("Create DefaultLoginService instance");
                }
            }
        }
       logger.debug("Return DefaultLoginService instance");
        return loginService;
    }


    @Override
    public User login(String login, String password) {
        User user = userDAO.getUserByEmail(login);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}


