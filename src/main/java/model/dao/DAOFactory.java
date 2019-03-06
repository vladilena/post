package model.dao;

import static model.dao.DAOConstants.DataBaseConstants.DRIVER;

public class DAOFactory {

    private static DAOFactory factory;

    private DAOFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DAOFactory getInstance() {
        if (factory == null) {
            factory = new DAOFactory();
        }
        return factory;
    }

    public UserDAO getUserDAO() {
        return UserDAO.getInstance();
    }

    public MailDAO getMailDAO() {
        return MailDAO.getInstance();
    }

    public CategoryDAO getCategoryDAO() {
        return CategoryDAO.getInstance();
    }

    public CustomerCategoryDAO getCustomerCategoryDAO() {
        return CustomerCategoryDAO.getInstance();
    }
}