package model.dao.impl;

import model.dao.InterfaceDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static model.dao.DAOConstants.DataBaseConstants.*;

public class AbstractDAO implements InterfaceDAO {

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }
}
